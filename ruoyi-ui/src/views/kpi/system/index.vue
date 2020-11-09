<template>
  <div class="app-container">
    <span>西安欧亚学院高职学院绩效体系2.0</span>
    <el-table v-loading="loading"
              :data="standardList"
              :span-method="objectSpanMethod"
              border
              style="width: 100%; margin-top: 30px">
      <el-table-column label="一级指标" align="center" prop="oneIndicator"/>
      <el-table-column label="二级指标" align="left" prop="twoIndicator"/>
      <el-table-column label="三级指标" align="left" prop="threeIndicator"/>
      <el-table-column label="分数" align="center" prop="grade"/>
    </el-table>
  </div>
</template>

<script>
  import { listStandard } from '@/api/kpi/standard'

  export default {
    name: 'system',
    data() {
      return {
        //存放每一行记录的合并数
        spanArr0: [],
        spanArr1: [],
        // spanArr0 的索引
        position0: 0,
        position1: 0,
        // 遮罩层
        loading: true,
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 总条数
        total: 0,
        // 绩效体系管理表格数据
        standardList: [],
        // 弹出层标题
        title: '',
        td_time:null,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 20,
          year: null,
          semester: null
        }
      }
    },
    created() {
      this.getList()
      // this.getdatatime()
    },
    methods: {
      objectSpanMethod({ row, column, rowIndex, columnIndex }) {
        //row : 传过来的当前一行数据，也就是每一个item
        //rowIndex : 每一行数据的的索引
        //column : 每一列数据，比如星期一下面的所有数据
        //columnIndex : 每一列数据的索引

        //对第一列 进行合并
        if (columnIndex === 0) {
          if (
            rowIndex > 0 && row.oneIndicator === this.standardList[rowIndex - 1].oneIndicator
          ) {
            return {
              //当前行与上一行内容相同时 返回0      0 意味着合并
              rowspan: 0,
              colspan: 0
            };
          } else {
            let rows = 1;
             // 查询相同的内容有多少行 进行合并（多行合并）
            for (let i = rowIndex; i < this.standardList.length - 1; i++) {
              if (row.oneIndicator === this.standardList[i + 1].oneIndicator) {
                rows++;
              }
            }
            // 返回相同内容的行数
            return {
              rowspan: rows,
              colspan: 1
            };
          }
          // 对第二列 进行合并
          } else if (columnIndex === 1){
          if (
            rowIndex > 0 && row.twoIndicator === this.standardList[rowIndex - 1].twoIndicator
          ) {
            return {
              rowspan: 0,
              colspan: 0
            };
          } else {
            let rows = 1;
            // 查询相同的内容有多少行 进行合并（多行合并）
            for (let i = rowIndex; i < this.standardList.length - 1; i++) {
              if (row.twoIndicator === this.standardList[i + 1].twoIndicator) {
                rows++;
              }
            }
            // 返回相同内容的行数
            return {
              rowspan: rows,
              colspan: 1
            };
          }
        }
      },
      /** 查询绩效体系管理列表 */
      getList() {
        this.loading = true
        // this.td_time = this.getdatatime()
        // console.log(this.td_time)
        listStandard(this.queryParams).then(response => {
          this.standardList = response.rows
          this.total = response.total
          this.loading = false
        })
      },
      getdatatime() {
        this.ct_month= new Date();
        this.ct_month.setTime(this.ct_month.getTime() - 3600 * 1000 * 24 * 30);//获取上个月的日期（这一行去掉就是获取今天计算机上的日期了）

        var now = this.ct_month;

        var year = now.getFullYear(); //年
        // var month = now.getMonth() + 1; //月
        // var day = now.getDate(); //日

//				var hh = now.getHours(); //时
//				var mm = now.getMinutes(); //分

        var clock = year + "-";

        if(month < 10)
          clock += "0";

        clock += month + "-";

        if(day < 10)
          clock += "0";

        clock += day + " ";

//				if(hh < 10)
//					clock += "0";
//
//				clock += hh + ":";
//				if(mm < 10) clock += '0';
//				clock += mm;

        console.log(clock);
      },
    }
  }
</script>
