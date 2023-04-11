<template>
  <div>
    <el-carousel indicator-position="outside" height="400px">
    <el-carousel-item v-for="item in 8" :key="item">
      <!-- <h3>{{ item }}</h3> -->
    </el-carousel-item>
    </el-carousel>
    <h1 style="text-align: center;">同城交友，众多美女等你来撩</h1>
  </div>
</template>


<script>
export default {
  name: 'FrontHome',
  data(){
    return{}
  },
  create(){

  },
  methods:{
    
  }
}
</script>

<style>
  
  .el-carousel__item:nth-child(4n) {
    /* background-color: #99a9bf; */
    background-image: url("https://tse2-mm.cn.bing.net/th/id/OIP-C.OiMTsKsF_clbAvRx5AB-iAHaEo?pid=ImgDet&rs=1");
  }
  
  .el-carousel__item:nth-child(4n+1) {
    background-image: url("https://tse1-mm.cn.bing.net/th/id/OIP-C.mykBM8E8L_jFOgtwWSCBzQHaEo?pid=ImgDet&rs=1");
  }

  .el-carousel__item:nth-child(4n+2){
    background-image: url("https://tse2-mm.cn.bing.net/th/id/OIP-C.4x3BejrXEByNVcQTetghKQHaEo?w=297&h=186&c=7&r=0&o=5&dpr=1.3&pid=1.7")
  }
  .el-carousel__item:nth-child(4n+3){
    background-image: url("https://ts1.cn.mm.bing.net/th/id/R-C.eb9dcbbff969857d48e99a51c528202c?rik=hZSDFufHPOA4gA&riu=http%3a%2f%2fpic.qqbizhi.com%2fallimg%2fbpic%2f27%2f4027.jpg&ehk=T7b4uZ9p9qwodzUglLfllgOmajZi6SP2yuYSDIbInCQ%3d&risl=&pid=ImgRaw&r=0")
  }
</style>