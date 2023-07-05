<!--
<template>
  <div class="login-wrap">注册

    <el-form>
      <el-input placeholder="用户名" v-model="register.userName"></el-input>
      <el-input type="password" placeholder="密码" v-model="register.passWord"></el-input>
      <el-button type="primary" @click="tijiao">提交</el-button>
    </el-form>
  </div>
</template>
-->


<template>
  <div class="login-wrap">
    <div class="login-form">
      <div class="form-title">注册</div>
      <el-form :model="register" class="form-content" label-width="0px" ref="form">
        <el-form-item prop="username">
          <el-input placeholder="用户名" v-model="register.username">
            <span slot="prepend"><i class="el-icon-user"></i></span>
          </el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input @keyup.enter.native="submit()" placeholder="密码" type="password" v-model="register.password">
            <span slot="prepend"><i class="el-icon-edit"></i></span>
          </el-input>
        </el-form-item>


        <div style="display: flex;justify-content: center;" v-loading="this.$store.state.loading">
          <el-button type="primary" @click="tijiao">提交</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import { post } from "@/common/ajax";

export default {
  name: "register",
  data() {
    return {
      register: {
        userName: "",
        passWord: ""
      }
    };
  },
  methods: {
    tijiao() {
      if (this.register.userName == "" || this.register.userName == "") {
        this.$message.error("用户名和密码不能为空或");
        return;
      }
      let url = "http://localhost:8083/auth/register";
      post(url, this.register).then(res => {
        console.log(res);
        this.$router.push("/login")
      });
    }

  }
};
</script>

<style scoped>
.login-wrap {
  position: relative;
  width: 100%;
  height: 100%;
  background-image: url("../assets/login-background.jpg");
  background-size: 100% 100%;
}

.form-title {
  width: 100%;
  line-height: 50px;
  text-align: center;
  font-size: 20px;
  color: #fff;
  border-bottom: 1px solid #ddd;
}

.login-form {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 350px;
  margin: -190px 0 0 -175px;
  border-radius: 5px;
  background: rgba(0, 0, 0, 0.6);
  overflow: hidden;
}

.form-content {
  padding: 30px 30px;
}

.login-btn {
  text-align: center;
}

.login-btn button {
  width: 100%;
  height: 36px;
}

.el-radio {
  color: #fff;
}
</style>
