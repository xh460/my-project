<template>
  <div class="head-bar">
    <div class="header-ico">
      <i class="el-icon-s-home"></i>
    </div>
    <div class="logo">团队活动</div>
    <div style="float: right;margin-top: 15px;">
      <el-button type="primary" @click="cc">退出登录</el-button>
    </div>
  </div>
</template>
<script>


export default {
  name: "Header",
  data() {
    return {
      fullscreen: false
    };
  },
  computed: {
    username() {
      return this.$store.state.status.username;
    }
  },
  methods: {
    cc() {
      this.$router.push("/login")
    }
  }
};
</script>
<style scoped>
.head-bar {
  position: relative;
  box-sizing: border-box;
  width: 100%;
  height: 70px;
  font-size: 22px;
  color: #fff;
  background-color: #242f42;
}

.header-ico {
  float: left;
  padding: 0 21px;
  line-height: 70px;
}

.head-bar .logo {
  float: left;
  width: 250px;
  line-height: 70px;
}

.head-right {
  float: right;
  padding-right: 50px;
}

.head-user-con {
  display: flex;
  height: 70px;
  align-items: center;
}

.btn-fullscreen {
  transform: rotate(45deg);
  margin-right: 5px;
  font-size: 24px;
}

.btn-fullscreen {
  position: relative;
  width: 30px;
  height: 30px;
  text-align: center;
  border-radius: 15px;
  cursor: pointer;
}

.btn-bell .el-icon-bell {
  color: #fff;
}

.user-name {
  margin-left: 10px;
}

.user-avatar {
  margin-left: 20px;
}

.user-avatar img {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}

.el-dropdown-link {
  color: #fff;
  cursor: pointer;
}

.el-dropdown-menu__item {
  text-align: center;
}
</style>
