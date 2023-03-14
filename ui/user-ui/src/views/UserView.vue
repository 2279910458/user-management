<template>
  <div>
    <el-card style="margin-bottom: 5px">
      <el-input
          style="width: 300px; margin-right: 10px"
          placeholder="请输入用户名"
          suffix-icon="el-icon-search"
          v-model="filter.name"
      ></el-input>
      <el-input
          style="width: 300px; margin-right: 10px"
          placeholder="请输入联系电话"
          suffix-icon="el-icon-search"
          v-model="filter.phone"
      ></el-input>
      <el-input
          style="width: 300px; margin-right: 10px"
          placeholder="请输入详细地址"
          suffix-icon="el-icon-search"
          v-model="filter.address"
      >
      </el-input>
      <el-button type="primary" @click="onSubmit">搜索</el-button>
      <el-button type="primary" @click="reset">清除</el-button>
<!--      style="margin-left: -1100px;"-->
    </el-card>
    <el-card>
      <el-button type="primary" @click="createUser" >添加用户</el-button>
      <el-button type="primary" @click="batchDelete">批量删除</el-button>
      <el-table
          ref="multipleTable"
          :data="tableData"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column prop="name" label="姓名" width="120">
          <template slot-scope="scope">{{ scope.row.name }}</template>
        </el-table-column>
        <el-table-column prop="address" label="年龄" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.age }}</template>
        </el-table-column>
        <el-table-column prop="address" label="性别" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.gender }}</template>
        </el-table-column>
        <el-table-column prop="address" label="联系电话" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.phone }}</template>
        </el-table-column>
        <el-table-column prop="address" label="详细地址" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.address }}</template>
        </el-table-column>
        <el-table-column prop="address" label="操作" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-button size="mini" type="danger" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-popconfirm title="确定删除">
              <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5, 10, 15, 20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            background
        >
        </el-pagination>
      </div>
    </el-card>
    <el-card>
      <el-dialog title="新建用户" :visible.sync="dialogFormVisible">
        <el-form :model="user">
          <el-form-item label="姓名" :label-width="formLabelWidth">
            <el-input v-model="user.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="性别" :label-width="formLabelWidth">
            <el-radio v-model="user.gender" label="0">男</el-radio>
            <el-radio v-model="user.gender" label="1">女</el-radio>
          </el-form-item>
          <el-form-item label="年龄" :label-width="formLabelWidth">
            <el-input v-model="user.age" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" :label-width="formLabelWidth">
            <el-input v-model="user.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="详细地址" :label-width="formLabelWidth">
            <el-cascader
                size="large"
                :options="sendaddresss"
                v-model="user.provincialAndCivicRegion"
                @change="handleChange" style="width:217px;"/>
            <el-input v-model="user.detailAddress" style="margin-left: 20px; width: 260px;" autocomplete="on"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel()">取 消</el-button>
          <el-button type="primary" @click="updateUser">确 定</el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
import {regionData, CodeToText} from 'element-china-area-data'

export default {
  name: 'UserView',
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      filter: {
        name: "",
        phone: "",
        address: ""
      },
      dialogFormVisible: false,
      user: {
        name: "",
        gender: null,
        age: null,
        phone: "",
        provincialAndCivicRegion: [],
        detailAddress: ""
      },
      formLabelWidth: "120px",
      sendaddresss: regionData,
      multipleSelection: []
    };
  },
  created() {
    this.getUser();
  },
  methods: {
    getUser() {
      let param = {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        filter: this.filter
      }
      this.request({
        method: "post",
        url: "/user/page",
        data: JSON.stringify(param)
      }).then((res) => {
        let data = res.data.data
        this.tableData = data
        this.total = data.length
      });
    },
    createUser() {
      this.dialogFormVisible = true
      this.request({
        method: "post",
        url: "/user/save",
        data: JSON.stringify(this.user)
      }).then(res => {
        if (res.code === 200) {
          this.$message.success("删除成功");
          this.dialogFormVisible = false
          this.user = {}
          this.getUser()
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    updateUser() {
      let id = this.user.id
      this.request({
        url: `/user/${id}/update`,
        data: JSON.stringify(this.user),
        method: "post"
      }).then(res => {
        if (res.code === 200) {
          this.$message.success("修改成功");
          this.dialogFormVisible = false;
          this.user = {}
        } else {
          this.$message.error(res.msg);
        }
      })
    },
    handleChange(value) {
      let sendreturnprovince = CodeToText[value[0]]
      let sendreturncity
      if (CodeToText[value[1]] == '市辖区') {
        sendreturncity = ""
      } else {
        sendreturncity = CodeToText[value[1]]
      }
      let sendreturnregion = CodeToText[value[2]]
      this.user.provincialAndCivicRegion = sendreturnprovince + sendreturncity + sendreturnregion
    },
    cancel() {
      this.dialogFormVisible = false
      this.user = {}
    },
    batchDelete(){
      let ids = this.multipleSelection.map(value => value.id);
      this.request.post(`/user/batch/delete`,ids).then(res => {
        if (res.code === 200) {
          this.$message.success("批量删除成功");
        } else {
          this.$message.error(res.msg);
        }
      })
    },
    toggleSelection(rows) {
      if (rows) {
        rows.forEach((row) => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleSizeChange(pageSize) {
      console.log(pageSize);
      this.pageSize = pageSize;
      this.getUser();
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.getUser()
    },
    handleEdit(index, row) {
      this.user = row
      this.dialogFormVisible = true
    },
    handleDelete(index, row) {
      this.user = row
      let id = this.user.id
      this.request({
        url: `/user/${id}/delete`,
        data: JSON.stringify(row),
        method: "post"
      }).then(res => {
        if (res.code === 200) {
          this.$message.success("删除成功");
          this.dialogFormVisible = false
          this.user = {}
        } else {
          this.$message.error(res.msg);
        }
      })
    },
    onSubmit() {
      this.getUser();
    },
    reset() {
      this.filter.name = "";
      this.filter.phone = "";
      this.filter.address = "";
    },
  }
}
</script>
