<template>
<!--  <div>-->

<!--    <Aside/>-->
<!--  </div>-->
  <div style="padding: 10px">
    <Header/>
<!--    功能标签-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary" @click="upload">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
<!--    搜索栏-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="Please input" clearable style="width: 20%"/>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>
<!--    表格-->
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="username" label="userName"/>
      <el-table-column prop="sex" label="Sex"/>
      <el-table-column prop="birthday" label="Birthday"/>
      <el-table-column prop="address" label="Address"/>
      <el-table-column prop="email" label="Email"/>
      <el-table-column prop="tel" label="Tel"/>
      <el-table-column label="Operations">
<!--        需改删除按钮-->
        <template #default="scope">
          <el-button type="success" size="small" @click="editClick(scope.row)">编辑</el-button>
          <el-popconfirm title="确定删除?" @confirm="deleteClick(scope.row.id)">
            <template #reference>
              <el-button type="info" size="small">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
<!--    分页插件-->
    <div style="margin: 10px 0">
      <el-pagination
          v-model:currentPage="currentPage4"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
      <!-- 新增/修改信息弹窗 -->
      <el-dialog v-model="dialogVisible" width="30%" title="提示">
        <el-form :model="form" label-width="80px">
          <el-form-item label="姓名">
            <el-input v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="form.sex" label="男">男</el-radio>
            <el-radio v-model="form.sex" label="女">女</el-radio>
          </el-form-item>
          <el-form-item label="生日">
            <el-date-picker v-model="form.birthday" type="date" placeholder="选择你的生日"/>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="form.tel"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="form.address" type="textarea"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="save">确定</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from "../utils/request";
import Header from "../components/Header";
import Aside from "../components/Aside";
import router from "../router";
export default {
  name: "Home",
  components: {
    Header,
    Aside
  },
  data() {
    return {
      username: localStorage.getItem('username'),
      token: '',
      form: {},
      dialogVisible: false,
      currentPage4:1,
      pageSize:10,
      total: 10,
      search: '',
      tableData: [
      ]
    }
  },
  methods: {
    // 页面加载函数
    load: function (){
      request.get("mybms/user",{
        params: {
          pageNum: this.currentPage4,
          pageSize: this.pageSize,
          key: this.search
        }
      }).then(res =>{
        this.tableData = res.data.records;
        this.total = res.data.total;
      })
    },
    //添加数据
    add: function (){
      this.dialogVisible = true;
      this.form = {};
    },
    //保存数据
    save: function (){

      //当前id不为空即为修改
      if(this.form.id==null){
        this.dialogVisible = false;
        request.post("/mybms/user",this.form).then(res =>{
          this.$message.success("修改成功");
        })
      }
      //否则执行新增
      else{
        this.dialogVisible = false;
        request.put("/mybms/user", this.form).then(res =>{
          this.$message.success("增加成功");
        })
      }
      this.load();

    },
    //删除
    deleteClick: function (id) {
      //this.form = JSON.parse(JSON.stringify(row));
      request.delete("/mybms/user/"+id).then(res=>{
        this.$message.success("删除成功");
      })
      this.load()
    },
    //编辑
    editClick: function (row) {
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible = true;
    },
    //改变每页显示条数
    handleSizeChange: function (pageSize){
      this.pageSize = pageSize;
      this.load();
    },
    //改变当前页数
    handleCurrentChange: function (pageNum){
      this.currentPage4 = pageNum;
      this.load();
    },
    upload: function (){

    }
  },
  created() {
    this.load()
  }
}

</script>
