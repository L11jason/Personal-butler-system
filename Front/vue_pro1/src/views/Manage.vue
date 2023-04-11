<template>
    <el-container style="min-height: 100vh">

      <!-- 导航栏 -->
        <Aside :isCollapse="isCollapse" :logTextShow="logTextShow" :sideWidth="sideWidth"/>
      
        
      <el-container>

        <!-- 页头 -->
        <el-header style="border-bottom: 1px solid #ccc;">
        <Header :collapseBtnClass="collapseBtnClass" :collapse="collapse"/>
        </el-header>
        
        <el-main>
            <!-- 表当前页面的子路由会在router-view里面展示 -->
            <router-view />
            

        </el-main>
      </el-container>
      
    </el-container>

</template>


<script>

import Aside from "@/components/Aside.vue"
import Header  from "@/components/Header"


export default {
  name: 'HomeView',
  data(){
    return{
      collapseBtnClass:'el-icon-s-fold',
      isCollapse:false,
      sideWidth:200,
      logTextShow:true,

      tableData:[],
      total:0,
      pageNum:1,
      pageSize:5,
      vcStudentName:"",
      vcStudentCode:"",
      nGrade:"",
      dialogFormVisible:false,
      form: {},
      multipleSelection :[],
      headerBg:'headerBg'
    }
  },
  // created(){
  //   //请求分页查询
  //   this.load()
  // },
  components:{
    Aside,
    Header,
},
  methods:{
    collapse(){//点击收缩按钮触发
      this.isCollapse=!this.isCollapse
      if(this.isCollapse){
        this.sideWidth=64
        this.collapseBtnClass='el-icon-s-unfold'
        this.logTextShow=false
      }else{
        this.sideWidth=200
        this.collapseBtnClass='el-icon-s-fold'
        this.logTextShow=true
      }
    },
    
  }
}
</script>

<style>

</style>
