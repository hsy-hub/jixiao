<template>
  <div class="app-container">
<!--    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">-->
<!--      <el-form-item label="年份" prop="year">-->
<!--        <el-input-->
<!--          v-model="queryParams.year"-->
<!--          placeholder="请输入年份"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="学期" prop="semester">-->
<!--        <el-select v-model="queryParams.semester" placeholder="请选择学期" clearable size="small">-->
<!--          <el-option-->
<!--            v-for="dict in semesterOptions"-->
<!--            :key="dict.dictValue"-->
<!--            :label="dict.dictLabel"-->
<!--            :value="dict.dictValue"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
<!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->

<!--    <el-row :gutter="10" class="mb8">-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['kpi:standard:add']"-->
<!--        >新增-->
<!--        </el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['kpi:standard:edit']"-->
<!--        >修改-->
<!--        </el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['kpi:standard:remove']"-->
<!--        >删除-->
<!--        </el-button>-->
<!--      </el-col>-->
<!--      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
<!--    </el-row>-->
 
    <el-table v-loading="loading" :data="standardList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="id序号" align="center" prop="id"/>
      <el-table-column label="年份" align="center" prop="year"/>
      <el-table-column label="学期" align="center" prop="semester" :formatter="semesterFormat"/>
      <el-table-column label="一级指标" align="center" prop="oneIndicator"/>
      <el-table-column label="二级指标" align="center" prop="twoIndicator"/>
      <el-table-column label="三级指标" align="center" prop="threeIndicator"/>
      <el-table-column label="分数" align="center" prop="grade"/>
      <el-table-column label="实际得分" align="center" prop="score">
        <el-input v-model="input" placeholder="请输入内容"></el-input>
      </el-table-column>
      <el-table-column label="说明" align="center" prop="details"/>
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['kpi:standard:edit']"-->
<!--          >修改-->
<!--          </el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['kpi:standard:remove']"-->
<!--          >删除-->
<!--          </el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改绩效评价体系对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="年份" prop="year">
          <el-input v-model="form.year" placeholder="请输入年份"/>
        </el-form-item>
        <el-form-item label="学期" prop="semester">
          <el-select v-model="form.semester" placeholder="请选择学期">
            <el-option
              v-for="dict in semesterOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="一级指标" prop="oneIndicator">
          <el-input v-model="form.oneIndicator" placeholder="请输入一级指标"/>
        </el-form-item>
        <el-form-item label="二级指标" prop="twoIndicator">
          <el-input v-model="form.twoIndicator" placeholder="请输入二级指标"/>
        </el-form-item>
        <el-form-item label="三级指标" prop="threeIndicator">
          <el-input v-model="form.threeIndicator" placeholder="请输入三级指标"/>
        </el-form-item>
        <el-form-item label="成绩" prop="grade">
          <el-input v-model="form.grade" placeholder="请输入成绩"/>
        </el-form-item>
        <el-form-item label="详情" prop="details">
          <el-input v-model="form.details" placeholder="请输入详情"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
    addStandard,
    delStandard,
    exportStandard,
    getStandard,
    listStandard,
    updateStandard
  } from "@/api/kpi/standard";

  export default {
    name: "Standard",
    data() {
      return {
        input: '',
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 绩效评价体系表格数据
        standardList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 学期(0为第一学期，1为0)字典
        semesterOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          year: null,
          semester: null,
          oneIndicator: null,
          twoIndicator: null,
          threeIndicator: null,
          grade: null,
          details: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          year: [
            {required: true, message: "年份不能为空", trigger: "blur"}
          ],
          semester: [
            {required: true, message: "学期不能为空", trigger: "change"}
          ],
        }
      };
    },
    created() {
      this.getList();
      this.getDicts("kpi_semester").then(response => {
        this.semesterOptions = response.data;
      });
    },
    methods: {
      /** 查询绩效评价体系列表 */
      getList() {
        this.loading = true;
        listStandard(this.queryParams).then(response => {
          this.standardList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 学期(0为第一学期，1为0)字典翻译
      semesterFormat(row, column) {
        return this.selectDictLabel(this.semesterOptions, row.semester);
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          year: null,
          semester: null,
          oneIndicator: null,
          twoIndicator: null,
          threeIndicator: null,
          grade: null,
          details: null,
          createTime: null,
          updateTime: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加绩效评价体系";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getStandard(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改绩效评价体系";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateStandard(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addStandard(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$confirm('是否确认删除绩效评价体系编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delStandard(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有绩效评价体系数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return exportStandard(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      }
    }
  };
</script>
