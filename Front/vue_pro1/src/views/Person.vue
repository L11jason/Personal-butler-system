<template>
    <el-card style="width:500px;border: 1px solid #ccc;">
        <el-form :model="form" label-width="60px" size="small">
            <el-form-item label="用户名" >
            <el-input v-model="form.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="昵称" >
            <el-input v-model="form.nickname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" >
            <el-input v-model="form.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="手机号" >
            <el-input v-model="form.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="地址" >
            <el-input v-model="form.address" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
            <!-- <el-button >取 消</el-button> -->
            <el-button type="primary" @click="save">确 定</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>


<script>
export default {
  name: 'Home',
  data(){
    return{
        form:{},
        user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")) :{},
    }
  },
  created(){
    this.request.get("/user/username/"+this.user.username).then(res=>{
        if(res.code =='200'){
            this.form =res.data
        }
    })
  },
  methods:{
    save(){
      this.request.post("/user",this.form).then(res=>{
        if(res.data){
          this.$message.success("保存成功")
        } else{
          this.$message.error("保存失败")
        }
      })
    },
  }
}
</script>

<style>

</style>