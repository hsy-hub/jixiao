<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="nickName">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学工号" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入学工号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="年份" prop="year">
        <el-input
          v-model="queryParams.year"
          placeholder="请输入年份"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学期" prop="semester">
        <el-select v-model="queryParams.semester" placeholder="请选择学期" clearable size="small">
          <el-option
            v-for="dict in semesterOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="部门id" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入部门id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="state">
        <el-select v-model="queryParams.state" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in stateOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
      <!--      <el-form-item label="成绩单" prop="score">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.score"-->
      <!--          placeholder="请输入成绩单"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="总成绩" prop="totalScore">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.totalScore"-->
      <!--          placeholder="请输入总成绩"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="说明" prop="details">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.details"-->
      <!--          placeholder="请输入说明"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['kpi:grade:add']"
        >新增
        </el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['kpi:grade:edit']"-->
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
<!--          v-hasPermi="['kpi:grade:remove']"-->
<!--        >删除-->
<!--        </el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['kpi:grade:export']"-->
<!--        >导出-->
<!--        </el-button>-->
<!--      </el-col>-->
<!--      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
    </el-row>

    <el-table v-loading="loading" :data="gradeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="序号" align="center" prop="id"/>
      <el-table-column label="姓名" align="center" prop="nickName"/>
      <el-table-column label="学工号" align="center" prop="userName"/>
      <el-table-column label="年份" align="center" prop="year"/>
      <el-table-column label="学期" align="center" prop="semester" :formatter="semesterFormat"/>
      <el-table-column label="部门" align="center" prop="deptId"/>
<!--      <el-table-column label="成绩单" align="center" prop="score"/>-->
      <el-table-column label="总成绩" align="center" prop="totalScore">
        <template slot-scope="scope">
          <router-link :to="'/grade/type/score/' + scope.row.id" class="link-type">
            <span>{{ scope.row.totalScore }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="state" :formatter="stateFormat"/>
      <el-table-column label="说明" align="center" prop="details"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['kpi:grade:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['kpi:grade:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改绩效成绩对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名" prop="nickName">
          <el-input v-model="form.nickName" placeholder="请输入用户名"/>
        </el-form-item>
        <el-form-item label="学工号" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入学工号"/>
        </el-form-item>
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
        <el-form-item label="部门id" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入部门id"/>
        </el-form-item>
<!--        <el-form-item label="成绩单" prop="score">-->
<!--          <el-input v-model="form.score" placeholder="请输入成绩单"/>-->
<!--        </el-form-item>-->
        <el-form-item label="总成绩" prop="totalScore">
          <el-input v-model="form.totalScore" placeholder="请输入总成绩"/>
        </el-form-item>
        <el-form-item label="状态" prop="state">
          <el-select v-model="form.state" placeholder="请选择状态">
            <el-option
              v-for="dict in stateOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="说明" prop="details">
          <el-input v-model="form.details" placeholder="请输入说明"/>
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
  import {listGrade, getGrade, delGrade, addGrade, updateGrade, exportGrade} from "@/api/kpi/grade";
  import {treeselect} from "@/api/system/dept";

  export default {
    name: "Grade",
    data() {
      return {
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
        // 绩效成绩表格数据
        gradeList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 学期字典
        semesterOptions: [],
        // 状态字典
        stateOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          nickName: null,
          userName: null,
          year: null,
          semester: null,
          deptId: null,
          score: null,
          totalScore: null,
          state: null,
          details: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          nickName: [
            {required: true, message: "用户名不能为空", trigger: "blur"}
          ],
          userName: [
            {required: true, message: "学工号不能为空", trigger: "blur"}
          ],
          year: [
            {required: true, message: "年份不能为空", trigger: "blur"}
          ],
          semester: [
            {required: true, message: "学期不能为空", trigger: "change"}
          ],
          deptId: [
            {required: true, message: "部门id不能为空", trigger: "blur"}
          ],
        }
      };
    },
    created() {
      this.getList();
      this.getTreeselect();
      this.getDicts("kpi_semester").then(response => {
        this.semesterOptions = response.data;
      });
      this.getDicts("kpi_performance").then(response => {
        this.stateOptions = response.data;
      });
    },
    methods: {
      /** 查询绩效成绩列表 */
      getList() {
        this.loading = true;
        listGrade(this.queryParams).then(response => {
          this.gradeList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      /** 查询部门下拉树结构 */
      getTreeselect() {
        treeselect().then(response => {
          this.deptOptions = response.data;
        });
      },
      // 学期字典翻译
      semesterFormat(row, column) {
        return this.selectDictLabel(this.semesterOptions, row.semester);
      },
      // 状态字典翻译
      stateFormat(row, column) {
        return this.selectDictLabel(this.stateOptions, row.state);
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
          nickName: null,
          userName: null,
          year: null,
          semester: null,
          deptId: null,
          score: null,
          totalScore: null,
          state: null,
          createTime: null,
          updateTime: null,
          details: null
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
        this.title = "添加绩效成绩";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getGrade(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改绩效成绩";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateGrade(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addGrade(this.form).then(response => {
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
        this.$confirm('是否确认删除绩效成绩编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delGrade(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有绩效成绩数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return exportGrade(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      }
    }
  };
</script>
