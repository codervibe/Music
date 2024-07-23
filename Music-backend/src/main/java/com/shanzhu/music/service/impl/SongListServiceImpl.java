package com.shanzhu.music.service.impl;

import com.shanzhu.music.entity.po.Song;
import com.shanzhu.music.entity.po.SongList;
import com.shanzhu.music.mapper.SongListMapper;
import com.shanzhu.music.mapper.SongMapper;
import com.shanzhu.music.service.SongListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 歌单 服务层实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SongListServiceImpl implements SongListService {

    private final SongListMapper songListMapper;
    private final SongMapper songMapper;

    /**
     * 增加歌单
     *
     * @param songList 歌单信息
     * @return 结果
     */
    @Override
    public Boolean insert(SongList songList) {
        return songListMapper.insert(songList) > 0;
    }

    /**
     * 修改歌单
     *
     * @param songList 歌单信息
     * @return 结果
     */
    @Override
    public Boolean update(SongList songList) {
        return songListMapper.update(songList) > 0;
    }

    /**
     * 删除歌单
     *
     * @param id 歌单id
     * @return 删除结果
     */
    @Override
    public Boolean delete(Integer id) {
        return songListMapper.delete(id) > 0;
    }

    /**
     * 查询歌单
     *
     * @param id 歌单id
     * @return 歌单
     */
    @Override
    public SongList selectByPrimaryKey(Integer id) {
        return songListMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌单
     *
     * @return 歌单列表
     */
    @Override
    public List<SongList> allSongList() {
        return songListMapper.allSongList();
    }

    /**
     * 根据标题精确查询歌单列表
     *
     * @param title 标题
     * @return 歌单列表
     */
    @Override
    public List<SongList> songListOfTitle(String title) {
        return songListMapper.songListOfTitle(title);
    }

    /**
     * 根据标题模糊查询歌单列表
     *
     * @param title 标题
     * @return 歌单列表
     */
    @Override
    public List<SongList> likeTitle(String title) {
        return songListMapper.likeTitle(title);
    }

    /**
     * 根据风格模糊查询歌单列表
     *
     * @param style 歌单风格
     * @return 歌单列表
     */
    @Override
    public List<SongList> likeStyle(String style) {
        return songListMapper.likeStyle(style);
    }

    @Override
    public List<Song> queryRecommendedSongById(String id) {
        int ids = Integer.parseInt(id);
        log.info("ids : {}",ids);
        List<Integer> similarUserIds = songListMapper.findSimilarUsers(ids);
        log.info("similarUserIds = {}", similarUserIds.toString());
        List<Song> songsByUserIdNotInOtherUser = new ArrayList<>();

        for (Integer similarUserId : similarUserIds) {
             songsByUserIdNotInOtherUser = songMapper.getSongsByUserIdNotInOtherUser(ids, similarUserId);
        }

        return songsByUserIdNotInOtherUser;
    }

}
