<template>
    <div>

        <!-- 条件查询 -->
        <div style="margin:10px 0">
            <el-input style="width: 200px;" suffix-icon="el-icon-search" placeholder="请输入用户名" v-model="username"></el-input>
            <el-input style="width: 200px;" suffix-icon="el-icon-search" placeholder="请输入昵称" v-model="nickname"></el-input>
            <el-input style="width: 200px;" suffix-icon="el-icon-search" placeholder="请输入手机号" v-model="phone"></el-input>
            <el-button class="ml-5" type="primary" @click="load" >搜索</el-button>
            <el-button type="warning" @click="reset" >重置</el-button>
        </div>
        
        <!-- 表操作 -->
        <div style="margin:10px 0">
            <el-button type="primary" @click="handleAdd" >新增<i class="el-icon-circle-plus"></i></el-button>
            <el-popconfirm
                    class="ml-5"
                    confirm-button-text='确认'
                    cancel-button-text='取消'
                    icon="el-icon-info"
                    icon-color="red"
                    title="确定删除吗？"
                    @confirm="delBatch">
            <el-button type="danger" slot="reference">批量删除<i class="el-icon-remove"></i></el-button>
            </el-popconfirm>
            <el-button type="primary" class="ml-5">导入<i class="el-icon-caret-bottom"></i></el-button>
            <el-button type="primary">导出<i class="el-icon-caret-top"></i></el-button>
        </div>
        
        <!-- 数据及数据操作 -->
        <el-table :data="tableData"   stripe header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="id" label="ID" width="100">
            </el-table-column>
            <el-table-column prop="username" label="用户名" width="130">
            </el-table-column>
            <el-table-column prop="nickname" label="昵称" width="130">
            </el-table-column>
            <el-table-column prop="email" label="邮箱" width="200">
            </el-table-column>
            <el-table-column prop="phone" label="手机号" width="130">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>

            <el-table-column label="操作">
            <template slot-scope="scope">
                <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
                <el-popconfirm
                    class="ml-5"
                    confirm-button-text='确认'
                    cancel-button-text='取消'
                    icon="el-icon-info"
                    icon-color="red"
                    title="确定删除吗？"
                    @confirm="handledel(scope.row.id)">
                <el-button type="danger" slot="reference">删除<i class="el-icon-remove"></i></el-button>
                </el-popconfirm>
            </template>
            </el-table-column>
        </el-table>


        <!-- 分页 -->
        <div style="padding:10px 0">
            <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[2, 5, 10, 20]"
            :page-size="5"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
            </el-pagination>
        </div>

        <!-- Form -->
        <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="40%">
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
            </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
        </div>
        </el-dialog>
    </div>
</template>


<script>
export default {
  name: 'user',
  data(){
    return{
        tableData:[],
        total:0,
        pageNum:1,
        pageSize:5,
        username:"",
        nickname:"",
        phone:"",
        dialogFormVisible:false,
        form: {},
        multipleSelection :[],
        headerBg:'headerBg'
    }
  },
  created(){
    //请求分页查询
    this.load()
  },

  methods:{
    load(){
      this.request.get("/user/page",{
        params:{
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username:this.username,
          nickname:this.nickname,
          phone:this.phone,
        }
      }).then(res=>{
        console.log(res)
        this.tableData=res.data.records
        this.total=res.data.total
      }) 
    },
    save(){
      this.request.post("/user",this.form).then(res=>{
        if(res.data){
          this.$message.success("保存成功")
          this.dialogFormVisible=false
          this.load()
        } else{
          this.$message.error("保存失败")
        }
      })
    },
    handleAdd(){
      this.dialogFormVisible=true
      this.form ={}
    },
    handleEdit(row){
      this.form =Object.assign({},row)
      this.dialogFormVisible=true
    },
    handledel(id){
      this.request.delete("/user/"+id).then(res=>{
        if(res.data){
          this.$message.success("删除成功")
          this.load()
        } else{
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection=val
    },
    delBatch(){
      let ids=this.multipleSelection.map(v=>v.id) //对象数组变为id数组
      this.request.delete("/user/delBatch",{data: ids}).then(res=>{
        if(res.data){
          this.$message.success("删除成功")
          this.load()
        } else{
          this.$message.error("删除失败")
        }
      })
    },
    reset(){
      this.username= ""
      this.nickname= ""
      this.phone=""
    },
    handleSizeChange(pageSize){
      console.log(pageSize)
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      console.log(pageNum)
      this.pageNum=pageNum
      this.load()
    }

  },


  props:{
  }
}
</script>

<style>
  .headerBg{
    background: #eee !important;
  }
</style>