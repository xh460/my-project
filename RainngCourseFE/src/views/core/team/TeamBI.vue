<template>
  <div>我参与的团队如下:
    <br>
    <br>
    <el-table id="educe-table" ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%"
      :header-cell-style="{ background: '#f4f4f4' }" @selection-change="handleSelectionChange"></el-table>
    <el-button @click="dd = true" type="primary">申请参与小组
    </el-button>
    <el-dialog :visible.sync="dd">
      <el-input v-model="teamid" placeholder="请输入活动id"></el-input>
      <el-button @click="confirmteam1">确定</el-button>
    </el-dialog>

    <el-table id="cc" :data="tableList" v-loading="loading" style="margin-bottom: 30px">
      <el-table-column v-if="false" porp="teamid"></el-table-column>
      <el-table-column prop="userName" label="团长" width="260"></el-table-column>

      <el-table-column prop="beginTime" label="开始时间" width="200"></el-table-column>
      <el-table-column prop="beginPlace" label="开始地点" width="200"></el-table-column>
      <el-table-column prop="content" label="内容" width="200"></el-table-column>
      <el-table-column prop="fund" label="已缴纳" width="200"></el-table-column>
      <el-table-column prop="pay" label="应缴纳" width="200"></el-table-column>
      <el-table-column prop="status" label="状态" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.status === 0 ? "未确认" : "组建完成" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">缴纳
          </el-button>


        </template>
      </el-table-column>
    </el-table>
    <span>所有活动如下:</span>


    <el-table style="margin-top: 20px" :data="tableList1" v-loading="loading1">
      <el-table-column v-if="false" porp="teamid"></el-table-column>
      <el-table-column prop="userName" label="团长" width="260"></el-table-column>
      <el-table-column prop="uid" label="团队id" width="260"></el-table-column>
      <el-table-column prop="beginTime" label="开始时间" width="200"></el-table-column>
      <el-table-column prop="beginPlace" label="开始地点" width="200"></el-table-column>
      <el-table-column prop="content" label="内容" width="200"></el-table-column>
      <el-table-column prop="money" label="AA资金" width="200"></el-table-column>
      <el-table-column prop="status" label="状态" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.status == 0 ? "未确认" : "组建完成" }}</span>
        </template>
      </el-table-column>
    </el-table>


    <el-dialog :visible.sync="fundin">
      <el-input v-model="fundBody.money" placeholder="请输入缴纳资金"></el-input>
      <el-button type="primary" @click="save">提交</el-button>
    </el-dialog>

    <span style="margin-top: 30px">我接受的消息如下:</span>
    <br>
    <br>
    <el-card>
      <div :key="index" v-for="(item, index) in msgData">
        {{ item.msg }}
      </div>
    </el-card>
  </div>
</template>

<script>
import { pureGet, get } from "@/common/ajax";
import FileSaver from "file-saver";
// eslint-disable-next-line no-unused-vars
import XLSX from "xlsx";

export default {
  name: "TeamBI",
  data() {
    return {
      tableList1: [],
      loading1: "",
      teamid: "",
      dd: false,
      tableData: [1, 2, 3],
      msgData: [],
      fundBody: {
        money: "",
        teamid: ""
      },
      fundin: false,
      fundMoney: "",
      tableList: [],
      loading: true
    };
  },

  methods: {
    handleSelectionChange() {

    },
    confirmteam1() {

      let url = "http://localhost:8083/core/team/confirmteam";
      let params = {};
      params.teamid = this.teamid;
      alert(JSON.stringify(params));
      get(url, params).then(res => {
        console.log(res);
        this.$message.success("加入成功");
        this.dd = false;
        this.getList();

      });
    },
    // 表格数据写入excel，并导出为Excel文件
    exportToExcel() {
      const XLSX = require("xlsx");
      console.log("XLSX", XLSX, FileSaver);
      // 使用 this.$nextTick 是在dom元素都渲染完成之后再执行
      this.$nextTick(function () {
        // 设置导出的内容是否只做解析，不进行格式转换     false：要解析， true:不解析
        const xlsxParam = { raw: true };
        const wb = XLSX.utils.table_to_book(document.querySelector("#cc"), xlsxParam);
        // 导出excel文件名
        let fileName = "数据报表" + new Date().getTime() + ".xlsx";

        const wbout = XLSX.write(wb, { bookType: "xlsx", bookSST: true, type: "array" });
        try {
          // 下载保存文件
          FileSaver.saveAs(new Blob([wbout], { type: "application/octet-stream" }), fileName);
        } catch (e) {
          if (typeof console !== "undefined") {
            console.log(e, wbout);
          }
        }
        return wbout;
      });
    },

    // exportExcel() {
    //   // 1.生成Excel工作簿对象
    //   var wb = XLSX.utils.table_to_book(document.querySelector('#educe-table'));
    //   // 2.手机号，身份证号等会默认使用科学计数法表示，则需要这样设置
    //   // var wb = XLSX.utils.table_to_book(
    //   //   document.querySelector("#educe-table"),
    //   //   { raw: true }
    //   // );
    //   // 获取二进制字符串作为输出
    //   var wbout = XLSX.write(wb, {
    //     bookType: 'xlsx',
    //     book: true,
    //     type: 'array',
    //   })
    //   try {
    //     FileSaver.saveAs(
    //       // Blob: 对象表示一个不可变 原始数据的类文件对象,不一定是JS原生格式的数据。
    //       // File: 基于Blob，继承了blob的功能并将其扩展使其支持用户系统上的文件。
    //       new Blob([wbout], { type: 'appliction/octet-stream' }),
    //       // 设置导出的文件名称可随意
    //       'result.xlsx'
    //     )
    //   } catch (e) {
    //     if (typeof console != 'undefined') console.log(e, wbout);
    //   }
    //   // 返回一个新创建的Blob对象，其内容由参数中给定的数组串联组成。
    //   return wbout
    // },
    save() {
      let url = "http://localhost:8083/core/team/payMoney";
      get(url, this.fundBody).then(res => {
        console.log(res);
        this.fundin = false;
        this.$message.success("缴纳成功");
        this.getList();
        // this.getList()
      });
    },
    handleUpdate(row) {
      this.fundin = true;
      this.fundBody.teamid = row.uid;
      // alert(this.fundBody.teamid);
      console.log(row);

    },
    getList1() {
      let url = "http://localhost:8083/core/team/list";
      // alert(11)
      pureGet(url).then(res => {
        this.tableList1 = res;
        this.loading1 = false;
        console.log(res);

        // alert( this.$store.state.status.username)
      });
    },
    /*getNameById(uid){
      let url="http://localhost:8083/core/team/getNameById"
      get(url,{uid:uid}).then(res=>{
        console.log(res.data)
      })

    },*/
    getList() {
      let url = "http://localhost:8083/core/team/getMyTeam";
      let url1 = "http://localhost:8083/t-msg/list";
      pureGet(url1).then(res => {
        console.log(res);
        this.msgData = res;
      });
      pureGet(url).then(res => {
        this.tableList = res;
        this.loading = false;
      });
    }
  },
  created() {
    this.getList();
    this.getList1();
  }
};
</script>

<style scoped></style>
