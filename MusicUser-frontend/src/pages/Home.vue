<!--
 * 首页
-->
<template>
  <div class="home">
    <swiper/>
    <div class="section" v-for="(item, index) in songsList" :key="index">
      <div class="section-title"><i :class="item.icon" style="font-size: 34px;"></i>
        <b style="font-size: 32px;">
          {{ item.name }}
        </b>
      </div>
      <content-list :contentList="item.list"></content-list>
    </div>

<!--    <div v-if="loginIn">-->
<!--      &lt;!&ndash; 如果 condition 为真，渲染这个 div &ndash;&gt;-->
<!--      <div class="section" v-for="(item, index) in recommendedsongsList" :key="index">-->
<!--        <div class="section-title"><i :class="item.icon" style="font-size: 34px;"></i>-->
<!--          <b style="font-size: 32px;">-->
<!--            {{ item.name }}-->
<!--          </b>-->
<!--        </div>-->
<!--        <content-list :contentList="item.list"></content-list>-->
<!--      </div>-->
<!--    </div>-->
    <div v-if="loginIn">
      <!-- 如果 condition 为真，渲染这个 div -->
      <div class="section" v-for="(item, index) in recommendedsongsList" :key="index">
        <div class="section-title"><i :class="item.icon" style="font-size: 34px;"></i>
          <b style="font-size: 32px;">
            {{ item.name }}
          </b>

        </div>
        <el-table
          :data="tableData"
          stripe
          style="width: 100%">
          <el-table-column
            prop="name"
            label="歌曲名字"
            width="780">
          </el-table-column>
          <el-table-column
            prop="introduction"
            label="introduction"
            width="180">
          </el-table-column>
        </el-table>
      </div>
    </div>
    </div>

</template>

<script>
import Swiper from "../components/Swiper";
import contentList from "../components/ContentList";
import {getAllSinger, getAllSongList, getRecommendedsongsList} from "../api/index";

export default {
  name: "home",
  components: {
    Swiper,
    contentList,
  },
  data() {
   const loginIn = JSON.parse(window.sessionStorage.getItem('loginIn'))
    return {
      loginIn:loginIn,
      songsList: [
        {name: "歌单", list: [], icon: "iconfont icon-r-list"},
        {name: "歌手", list: [], icon: "iconfont icon-r-love"},
      ],
      recommendedsongsList: [
        {name: "推荐歌曲", list: [], icon: "iconfont icon-r-list"},
      ],
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }]
    };
  },
  created() {
    this.getSongList();
    this.getSinger();
    this.getRecommendedsongsList();

  },
  methods: {
    getSongList() {
      //获取前十条歌单
      getAllSongList()
        .then((res) => {
          this.songsList[0].list = res.slice(0, 10);
        })
        .catch((e) => {
          if (e.data == undefined) {
            if (
              e ==
              "TypeError: Cannot read properties of undefined (reading 'status')"
            ) {
              e = "Network Error";
            }
            this.$message({
              showClose: true,
              message: e,
              type: "error",
              duration: 5000,
            });
          } else {
            this.$message({
              showClose: true,
              message: e.data,
              type: "error",
              duration: 5000,
            });
          }
        });
    },
    getSinger() {
      //获取前十名歌手
      getAllSinger()
        .then((res) => {
          this.songsList[1].list = res.slice(0, 10);
        })
        .catch((e) => {
          if (e.data == undefined) {
            if (
              e ==
              "TypeError: Cannot read properties of undefined (reading 'status')"
            ) {
              e = "Network Error";
            }
            this.$message({
              showClose: true,
              message: e,
              type: "error",
              duration: 5000,
            });
          } else {
            this.$message({
              showClose: true,
              message: e.data,
              type: "error",
              duration: 5000,
            });
          }
        });
    },
    getRecommendedsongsList() {
     const userId = JSON.parse(window.sessionStorage.getItem('userId'))
      console.log('userId : '+ userId)
      //获取前十条歌单
      getRecommendedsongsList(userId)
        .then((res) => {
          // this.recommendedsongsList[0].list = res.data.slice(0, 10);
          this.tableData = res.data;
        })
        .catch((e) => {
          if (e.data == undefined) {
            if (
              e ==
              "TypeError: Cannot read properties of undefined (reading 'status')"
            ) {
              e = "Network Error";
            }
            this.$message({
              showClose: true,
              message: e,
              type: "error",
              duration: 5000,
            });
          } else {
            this.$message({
              showClose: true,
              message: e.data,
              type: "error",
              duration: 5000,
            });
          }
        });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../assets/css/home.scss";
</style>
