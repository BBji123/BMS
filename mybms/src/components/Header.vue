<template>
  <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #ccc;display: flex">
    <div style="width: 200px; padding-left: 30px;font-weight: bold; color: dodgerblue">后台管理</div>
    <div style="flex: 1;"></div>
    <div style="width: 100px;">
      <el-dropdown style="padding-top: 15px">
        <span class="el-dropdown-link" >
          {{username}}
          <el-icon class="el-icon--right">
            <arrow-down/>
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>个人信息</el-dropdown-item>
            <el-dropdown-item @click="logout">退出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import request from "../utils/request";

export default {
  name: "Header",
  data(){
    return{
      username: localStorage.getItem('username')
    }
  },
  methods: {
    //注销功能呢
    logout: function (){
      //跳转回登录页
      this.$router.push({path: './Login'});
      //删除本地存储的token
      localStorage.removeItem('token');
      //请求删除服务器端的授权信息
      request.get("mybms/user/logout").then(res=>{
      })
    }
  }
}
</script>

<style scoped>

</style>