package com.shanzhu.music.mapper;

import com.shanzhu.music.entity.po.Rank;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价 持久层
 */
@Mapper
public interface RankMapper {

    /**
     * 新增评价
     *
     * @param rank 评价信息
     * @return 结果
     */
    Integer insert(Rank rank);

    /**
     * 查总分
     *
     * @param songListId 歌单
     * @return 总分
     */
    Integer selectScoreSum(Integer songListId);

    /**
     * 查总评分人数
     *
     * @param songListId 歌单
     * @return 查总评分人数
     */
    Integer selectRankNum(Integer songListId);
}
