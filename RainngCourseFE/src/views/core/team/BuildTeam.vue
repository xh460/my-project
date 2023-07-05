<template>
  <div>
    <el-button type="primary" @click="() => editing = true">创建团队</el-button>
    <el-table style="margin-top: 20px" :data="tableList" v-loading="loading">
      <el-table-column v-if="false" porp="teamid"></el-table-column>
      <el-table-column prop="userName" label="团长" width="260"></el-table-column>
      <el-table-column prop="beginTime" label="开始时间" width="200"></el-table-column>
      <el-table-column prop="beginPlace" label="开始地点" width="200"></el-table-column>
      <el-table-column prop="content" label="内容" width="200"></el-table-column>
      <el-table-column prop="money" label="AA资金" width="200"></el-table-column>
      <el-table-column prop="status" label="状态" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.status == 0 ? "未确认" : "组建完成" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">组内详细
          </el-button>

          <el-button type="primary" v-if="scope.row.status == 0" @click="confirm(scope.row)">
            确认成立
          </el-button>
          <el-button @click="doReport(scope.row)" v-if="con(scope.row)" type="primary">结束并生成报表</el-button>

        </template>
      </el-table-column>
    </el-table>


    <el-dialog :visible.sync="editing" title="编辑" width="30%">


      <el-form :model="entityForm" label-width="70px" ref="form">
        <el-form-item label="开始时间">
          <el-date-picker format="yyyy-MM-dd HH:mm" type="datetime" v-model="entityForm.beginTime"
            value-format="yyyy-MM-dd HH:mm">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="内容">
          <el-input v-model="entityForm.content"></el-input>
        </el-form-item>
        <el-form-item label="预算">
          <el-input v-model="entityForm.money"></el-input>
        </el-form-item>

        <el-form-item label="地点">
          <el-input v-model="entityForm.beginPlace"></el-input>
        </el-form-item>
      </el-form>
      <span class="dialog-footer" slot="footer">
        <el-button @click="save" type="primary">确 定</el-button>
        <el-button @click="editing = false">取 消</el-button>
      </span>
    </el-dialog>
    <el-dialog :visible.sync="info" title="详细" width="30%">
      <el-card>
        <div :key="index" v-for="(item, index) in teamInfoList">
          {{ item.userName }} 已缴纳： {{ item.fundC }}
          &nbsp;
          &nbsp;
          <el-button @click="addMP(item)">追加应付金额</el-button>
        </div>
      </el-card>
    </el-dialog>
    <el-dialog :visible.sync="addM" title="详细" width="30%">
      <el-input v-model="addMoney">追加金额</el-input>
      <el-button @click="P()" type="primary"> 确定</el-button>
    </el-dialog>
    <el-dialog :visible.sync="add" title="添加成员" width="30%">
      <el-form v-model="saveTeam">
        <el-form-item>
          <el-input v-model="saveTeam.username"></el-input>
          <el-button @click="addTeam">提交</el-button>
        </el-form-item>
      </el-form>

    </el-dialog>
    <el-dialog :visible.sync="AAdi">
      <el-input v-model="AA" placeholder="请输入AA金额">

      </el-input>
      <el-button @click="AddAA">
        提交
      </el-button>
    </el-dialog>
  </div>
</template>

<script>
import { pureGet, post, get } from "@/common/ajax";

export default {
  name: "BuildTeam",
  data() {
    return {
      AAdi: false,
      AATeam: "",
      AA: "",
      qRow: {},
      addM: false,
      addMoney: "",
      users: [],
      saveTeam: {
        username: ""
      },
      add: false,
      userInfo: "",
      teamInfoList: [],
      info: false,
      tableList: [],
      loading: true,
      entityForm: {
        money: "",
        beginTime: "",
        beginPlace: ""
      },
      editing: false
    };
  },
  created() {
    this.getList();
  },
  methods: {
    con(row) {
      if (row.con == '0')
        return true;
    },
    AddAA() {
      let params = {};
      params.teamid = this.AATeam;
      params.money = this.AA;
      let url1 = "http://localhost:8083/core/team/AA";
      get(url1, params).then(res => {
        console.log(res);
        this.AAdi = false;
        this.getList();
      });

    },
    handleUpdateAA(row) {

      this.AAdi = true;
      this.AATeam = row.uid;
    },
    doReport(row) {
      let url1 = "http://localhost:8083/core/team/doReport";
      let params = {};


      params.teamid = row.uid;
      params.endtime = new Date();
      // alert(JSON.stringify(params));

      get(url1, params).then(res => {
        this.$message.success("报表生成成功");
        this.$router.push("/core/BI");
        console.log(res);
      });
      this.getList()
    },
    confirm(row) {


      var params = {};
      params.teamid = row.uid;
      let url1 = "http://localhost:8083/core/team/confirm";
      get(url1, params).then(res => {
        console.log(res);
        this.$message.success("已通知团队成员");
        this.getList();
      });
    },
    P() {
      this.addM = false;
      let url1 = "http://localhost:8083/core/team/changeMoney";
      let params = {};
      params.teamid = this.saveTeam.teamid;
      params.username = this.qRow.userName;
      params.money = this.addMoney;
      // alert(JSON.stringify(params));
      get(url1, params).then(res => {
        console.log(res);
      });
    },
    addMP(item) {
      this.qRow = item;
      this.addM = true;
      // let url1 = "http://localhost:8083/core/team/changeMoney";
      // let params = {};
      // params.teamid = this.saveTeam.teamid;
      // params.username = item.userName;
      // params.money = this.addMoney;
      // get(url1, params).then(res => {
      //   console.log(res);
      // });
    },
    addTeam() {
      let url1 = "http://localhost:8083/t-user/list";
      pureGet(url1).then(res => {
        this.users = res;
        if (this.users.includes(this.saveTeam.username)) {
          // alert(JSON.stringify(this.saveTeam));
          let url = "http://localhost:8083/core/team/saveTeamUseralone";
          get(url, this.saveTeam).then(res => {
            console.log(res);
            this.$message.success("添加成功");
            this.add = false;
          });
        } else {
          this.$message.error("数据库无此成员");
        }
      });

    },
    // eslint-disable-next-line no-unused-vars
    handleAdd(row) {
      this.add = true;
      this.saveTeam.teamid = row.uid;
    },
    handleUpdate(row) {
      let params = {};
      params.teamid = row.uid;
      this.saveTeam.teamid = row.uid;
      let url = "http://localhost:8083/core/team/getTeamInfo";
      get(url, params).then(res => {
        if (res === null) {
          alert(11);
          this.teamInfoList = [];
        } else
          this.teamInfoList = res;

        console.log(res);
        this.info = true;
      });
      // alert(JSON.stringify(row));
    },
    save() {
      let url = "http://localhost:8083/core/team/add";
      post(url, this.entityForm).then(res => {
        console.log(res);
        this.getList();
        this.editing = false;
      }
      );
      console.log(this.entityForm);
    },
    getList() {
      let url = "http://localhost:8083/core/team/list";
      // alert(11)
      pureGet(url).then(res => {
        this.tableList = res;
        this.loading = false;
        console.log(res);

        // alert( this.$store.state.status.username)
      });
    }
  }
};
</script>

<style scoped></style>
