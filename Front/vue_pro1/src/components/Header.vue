<template>
  <!-- <el-header style="border-bottom: 1px solid #ccc;"> -->
    <div style="font-size: 12px; line-height:60px; display: flex;">
      <div style="flex:1;">
        <span :class="collapseBtnClass" style="cursor: pointer;font-size:18px" @click="collapse"></span>

        <!-- 页签 -->
        <el-breadcrumb separator="/"  style="display:inline-block; margin-left: 10px;">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>{{currentPathName}}</el-breadcrumb-item>
        </el-breadcrumb>
        
      </div>
      <el-dropdown style="width: 140px; cursor: pointer; text-align: center;">
        <div style="display: inline-block;">
          <img :src="user.avatarUrl" alt=""
              style="width: 36px; border-radius: 50%; position: relative; top:10px; right:5px">
          <span style="display:inline-block;width:90px;font-weight:bold;">{{ user.nickname }}<i class="el-icon-arrow-down" style="margin-left:5px"></i></span>
        </div>
        <el-dropdown-menu slot="dropdown" style=" text-align: center;">
          <router-link to="/person" style="text-decoration: none"><el-dropdown-item>个人信息</el-dropdown-item></router-link>
          <!-- <router-link to="/login" style="text-decoration: none" @click="Logout"> -->
          <span style="text-decoration: none" @click="Logout">
            <el-dropdown-item>退出</el-dropdown-item>
          </span>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  <!-- </el-header> -->

</template>



<script>
import router from '@/router';

export default {
    name: "Header",
    data(){
      return{
        user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")) :{}
      }
    },
    methods:{
      Logout(){
        this.$router.push("/login")
        localStorage.removeItem("user")//退出清除浏览器储存的user信息
        this.$message.success("退出成功")
      }
    },
    props: {
        collapseBtnClass: String,
        collapse: function () { },
    },
    computed: {
        currentPathName() {
            return this.$store.state.currentPathName; //需要监听的数据
        }
    },
    watch: {
        currentPathName(newVal, oldVal) {
            console.log(newVal);
        }
    },
    components: { router }
}
</script>

<style>

</style>