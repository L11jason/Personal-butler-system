<template>
    <div>

        <!-- 条件查询 -->
        <div style="margin:10px 0">
            <el-input style="width: 200px;" suffix-icon="el-icon-search" placeholder="请输入名称" v-model="vcStudentName"></el-input>
            <el-input style="width: 200px;" suffix-icon="el-icon-search" placeholder="请输入学号" v-model="vcStudentCode"></el-input>
            <el-input style="width: 200px;" suffix-icon="el-icon-search" placeholder="请输入年级" v-model="nGrade"></el-input>
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
            <el-table-column prop="nStudentId" label="ID" width="100">
            </el-table-column>
            <el-table-column prop="vcStudentName" label="姓名" width="140">
            </el-table-column>
            <el-table-column prop="vcStudentCode" label="学号" width="140">
            </el-table-column>
            <el-table-column prop="nSex" label="性别" width="100">
            </el-table-column>
            <el-table-column prop="nMajor" label="专业" width="100">
            </el-table-column>
            <el-table-column prop="nGrade" label="年级" width="100">
            </el-table-column>
            <el-table-column prop="vcDetail" label="详细">
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
                    @confirm="handledel(scope.row.nStudentId)">
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
        <el-dialog title="学生信息" :visible.sync="dialogFormVisible" width="40%">
            <el-form :model="form" label-width="50px" size="small">
            <el-form-item label="姓名" >
            <el-input v-model="form.vcStudentName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="学号" >
            <el-input v-model="form.vcStudentCode" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="性别">
            <el-select v-model="form.nSex" placeholder="请选择性别">
            <el-option label="男" value="0"></el-option>
            <el-option label="女" value="1"></el-option>
            </el-select>
            </el-form-item>
            <el-form-item label="年级" >
            <el-input v-model="form.nGrade" autocomplete="off"></el-input>
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
  name: 'Student',
  data(){
    return{
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
  created(){
    //请求分页查询
    this.load()
  },

  methods:{
    load(){
      this.request.get("/student/page",{
        params:{
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          vcStudentName:this.vcStudentName,
          vcStudentCode:this.vcStudentCode,
          nGrade:this.nGrade,
        }
      }).then(res=>{
      //pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&VC_STUDENT_NAME="+this.VC_STUDENT_NAME).then(res=>{
        console.log(res)
        this.tableData=res.records
        this.total=res.total
      }) 
    },
    save(){
      this.request.post("/student",this.form).then(res=>{
        if(res){
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
    handledel(nStudentId){
      this.request.delete("/student/"+nStudentId).then(res=>{
        if(res){
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
      let ids=this.multipleSelection.map(v=>v.nStudentId) //对象数组变为id数组
      this.request.delete("/student/delBatch",{data: ids}).then(res=>{
        if(res){
          this.$message.success("删除成功")
          this.load()
        } else{
          this.$message.error("删除失败")
        }
      })
    },
    reset(){
      this.vcStudentName= ""
      this.vcStudentCode= ""
      this.nGrade=""
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