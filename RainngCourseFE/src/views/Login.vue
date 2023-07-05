<template>
  <div class="login-wrap">
    <div class="login-form">
      <div class="form-title">团队活动</div>
      <el-form :model="formData" :rules="rules" class="form-content" label-width="0px" ref="form">
        <el-form-item prop="username">
          <el-input placeholder="用户名" v-model="formData.username">
            <span slot="prepend"><i class="el-icon-user"></i></span>
          </el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input @keyup.enter.native="submit()" placeholder="密码" type="password" v-model="formData.password">
            <span slot="prepend"><i class="el-icon-edit"></i></span>
          </el-input>
        </el-form-item>


        <div style="display: flex;justify-content: space-between;" v-loading="this.$store.state.loading">
          <el-button @click="submit()" type="primary">登录</el-button>
          <el-button @click="submit1()" type="primary">注册</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import { post } from "@/common/ajax";

export default {
  data: function () {
    return {
      formData: {
        username: "",
        password: "",
        userType: "1"
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }]
        // userType: [
        //   { required: true, message: "请选择用户类型", trigger: "blur" }
        // ]
      }
    };
  },
  methods: {
    submit1() {
      this.$router.push("/register")
    },
    submit() {
      this.$refs.form.validate(valid => {
        if (valid) {
          let url1 = "http://localhost:8083/auth/loginUser";
          let params = {};
          params.username = this.formData.username;
          params.password = this.formData.password;
          post(url1, params).then(res => {
            // login(
            //   this.formData.username,
            //   this.formData.password,
            // ).then(res => {
            // Cookies.set("satoken", res.token);
            //
            // console.log(res);
            // setToken(res.data.token)
            this.$store.state.username = params.username
            // alert(this.$store.state.username)
            this.$message.success("登录成功: " + res.userName);
            // this.$store.commit("login", res);


            // let items = [];
            // for (let i = 0; i < SideBarItem.items.length; i++) {
            //   let item = SideBarItem.items[i];
            //   if (
            //     item.userType === res.userType &&
            //     (item.permission & res.permission) === item.permission
            //   ) {
            //     items.push(item);
            //   }
            // }


            this.$router.push({ name: "container" });
          });
        }
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
