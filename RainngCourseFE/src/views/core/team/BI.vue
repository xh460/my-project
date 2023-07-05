<template>
  <div>
    <el-button type="primary" class="btn-seal" @click="exportToExcel">导出报表</el-button>
    <el-table id="cc"
              :data="tableData" v-loading="loading">
      <el-table-column v-if="false" porp="teamid"></el-table-column>
      <el-table-column prop="teamuid" label="团队id" width="260"></el-table-column>
      <el-table-column prop="beginDate" label="开始时间" width="200"></el-table-column>
      <el-table-column prop="endDate" label="结束时间" width="200"></el-table-column>
      <el-table-column prop="participants" label="参会人数" width="200"></el-table-column>
      <el-table-column prop="fund" label="共缴纳团资金" width="200"></el-table-column>

    </el-table>


  </div>
</template>

<script>
import FileSaver from "file-saver";
// eslint-disable-next-line no-unused-vars
import XLSX from "xlsx";
import { pureGet } from "@/common/ajax";

export default {
  name: "BI",
  data() {
    return {
      tableData: [],
      loading: false
    };
  },
  created() {
    this.getList();
  },
  methods: {

    exportToExcel() {
      const XLSX = require("xlsx");
      console.log("XLSX", XLSX, FileSaver);
      // 使用 this.$nextTick 是在dom元素都渲染完成之后再执行
      this.$nextTick(function() {
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
    getList() {
      let url1 = "http://localhost:8083/t-report/list";
      pureGet(url1).then(res => {
        this.tableData = res;
        console.log(res);
      });
    }
  }
};
</script>

<style scoped>

</style>
