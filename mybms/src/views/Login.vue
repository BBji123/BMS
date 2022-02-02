<template>
  <div id="login-container">
    <div style="text-align: center;height: 50px">
      登录
    </div>
    <el-form
        :model="ruleForm"
        status-icon :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
        @keyup.enter.native="submitForm"
    >
      <el-form-item label="账号" prop="user">
        <el-input type="text" v-model="ruleForm.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="pass">
        <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
// import axios from 'axios'
import request from "../utils/request";

export default {
  name: "login",
  data() {

    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入账户'));
      } else {
        if (this.ruleForm.pass !== '') {
          this.$refs.ruleForm.validateField('pass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };
    return {
      token: "",
      ruleForm: {
        name: '',
        password: '',
      },
      rules: {
        user: [
          { validator: validatePass, trigger: 'blur' }
        ],
        pass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
      },
      isLogin:false
    };
  },
  methods: {
    //提交按钮功能
    submitForm(formName) {
      localStorage.removeItem("token");
      request.post("/mybms/user/login",this.ruleForm).then(res=>{
        //根据状态码跳转
        if(res.code===401){
          this.$message.success("密码错误");
        }
        else if(res.code === 200){
          //将token保存到本地
          localStorage.setItem('token',res.data.token);
          localStorage.setItem('username',res.data.username);
          this.$router.push({path: "/"});
        }
      })

    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      //手动重置表单信息
      this.ruleForm.name = '';
      this.ruleForm.password = '';
    }
  }
}
</script>

<style scoped>
body{
  margin: 0;
}
#login-container{
  width: 400px;
  height: 290px;
  background: #e5e9f2;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-left: -220px;
  margin-top: -170px;
  border-radius: 5px;
  padding-top: 40px;
  padding-right: 40px;
}
</style>