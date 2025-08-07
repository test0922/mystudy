<!--
 * @Descripttion: 
 * @version: 
 * @Author: sueRimn
 * @Date: 2025-07-25 10:18:40
 * @LastEditors: sueRimn
 * @LastEditTime: 2025-08-07 14:50:29
-->
<template>
  <div>
    <el-switch
      v-model="value1"
      active-text="關閉拖曳"
      inactive-text="開啟拖曳">
    </el-switch>
    <el-tree style="max-width: 600px" show-checkbox node-key="catId" :data="menus" :props="defaultProps"
      @node-click="handleNodeClick" :default-expanded-keys="expandedKeys" draggable :allow-drop="allowDrop"
      :allow-drag="allowDrag" @node-drop="handleDrop">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button v-if="node.level <= 2" type="text" size="mini" @click="append(data)">
            Append
          </el-button>
          <el-button type="text" size="mini" @click="edit(data)">
            Edit
          </el-button>
          <el-button v-if="node.childNodes.length == 0" type="text" size="mini" @click="remove(node, data)">
            Delete
          </el-button>
        </span>
      </span>
    </el-tree>
    <el-dialog :title="dialogType" :visible.sync="dialogVisible" width="30%" :close-on-click-modal="false">
      <el-form :model="category">
        <el-form-item label="分類名稱">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="圖標">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="計量單位">
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitData">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
/* eslint-disable */
export default {
  data() {
    return {
      value1:false,
      updateNodes:[],
      menus: [],
      expandedKeys: [],
      dialogVisible: false,
      category: {
        name: "",
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        sort: 0,
        productUnit: "",
        icon: "",
        catId: null
      },
      defaultProps: {
        children: "children",
        label: "name",
      },
      dialogType: '' //add,edit紀錄由什麼功能觸發彈窗
    };
  },

  methods: {
    handleNodeClick(data) {
       console.log(data);
    },
    // 获取数据列表
    getMenus() {
      this.$http({
        url: this.$http.adornUrl("/product/category/list/tree"),
        method: "get",
      }).then(({ data }) => {
        // console.log(data.data);

        this.menus = data.data;
      });
    },
    handleDrop(draggingNode, dropNode, dropType, ev) {
      let pCid;
      let pLevel;
      this.updateNodes = [];
      if (dropType == 'before' || dropType == 'after'){
        // console.log(draggingNode,dropNode,dropType,dropNode.parent.data.catId);
        //找出父id
        pCid = dropNode.parent.data.catId == undefined? 0: dropNode.parent.data.catId;
        pLevel = dropNode.parent.level
        //排序與修改自身層級與父id
        for(let i = 0; i < dropNode.parent.childNodes.length; i++){
          let updateNode ={
            catId : dropNode.parent.childNodes[i].data.catId,
            parentCid : pCid,
            catLevel : pLevel + 1,
            sort : i
          }
          this.updateNodes.push(updateNode);
        }
 
      }else if(dropType == 'inner') {
        // console.logdraggingNode,(dropNode,dropType);
        //找出父id
        pCid = dropNode.data.catId;
        pLevel = dropNode.level
        //排序與修改自身層級與父id
        for(let i = 0; i < dropNode.childNodes.length; i++){
          let updateNode ={
            catId : dropNode.childNodes[i].data.catId,
            parentCid : pCid,
            catLevel : pLevel + 1,
            sort : i
          }
          this.updateNodes.push(updateNode);
        }
       
      }
      // console.log(draggingNode.childNodes[0].data);
      //修改子元素層級
      if (draggingNode.childNodes.length > 0 && draggingNode.level - pLevel > 0){
        for(let i = 0; i < draggingNode.childNodes.length; i++){
          let updateNode ={
            catId : draggingNode.childNodes[i].data.catId,
            parentCid : draggingNode.childNodes[i].data.parentCid,
            level : pLevel + 2,
            sort : i
          }
          this.updateNodes.push(updateNode);
        }
      }
      // console.log(this.updateNodes);
      this.$http({
        url: this.$http.adornUrl("/product/category/update/move"),
        method: "post",
        data: this.$http.adornData(this.updateNodes, false), //屬姓名相同可以簡寫
      }).then(({ data }) => {
        this.$message({
          type: 'success',
          message: '拖曳修改成功!'
        });
        this.getMenus();
        this.expandedKeys = [pCid];
      });
    },
    allowDrag(draggingNode) {
        return this.value1;
    },
    layerCount(node){
      let count = 0;
      if(node.children != null && node.children.length > 0){
        count = count + 1;
        for(let i = 0; i < node.children.length; i++){
          count = count + this.layerCount(node.children[i]);
          // console.log(i,node.children[i], this.layerCount(node.children[i]));
          if (count == 2) break;
        }
      }
      return count;
    },
    allowDrop(draggingNode, dropNode, type) {
      // console.log(draggingNode, dropNode, type);
      let allow = true;
      if (type == 'inner'){
        allow = dropNode.data.catLevel + this.layerCount(draggingNode.data)  <= 2;
        // console.log(allow,'allowDrop',dropNode, this.layerCount(draggingNode.data), type)
      }
      return allow;
    },
    submitData() {
      if (this.dialogType == 'add') {
        this.addCategory();
      } else if (this.dialogType == 'edit') {
        this.editCategory();
      }
    },
    edit(data) {
      this.dialogType = 'edit';
      this.dialogVisible = true;
      //獲取最新數據
      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: "get",
      }).then(({ data }) => {
        // console.log(data.data);
        this.category.name = data.data.name;
        this.category.catId = data.data.catId;
        this.category.icon = data.data.icon;
        this.category.productUnit = data.data.productUnit;
        this.category.parentCid = data.data.parentCid;
      });

    },
    append(data) {
      // console.log(data);
      this.dialogType = 'add';
      this.dialogVisible = true;
      this.category.parentCid = data.catId;
      this.category.catLevel = data.catLevel * 1 + 1;
      this.category.name = '';
      this.category.catId = null;
      this.category.icon = '';
      this.category.productUnit = '';
      this.category.sort = 0;
      this.category.showStatus = 1;


    },
    addCategory() {
      console.log("添加", this.category);
      this.$http({
        url: this.$http.adornUrl("/product/category/save"),
        method: "post",
        data: this.$http.adornData(this.category, false),
      }).then(({ data }) => {
        this.$message({
          type: 'success',
          message: '添加成功!'
        });
        this.getMenus();
        this.expandedKeys = [this.category.parentCid];
        this.dialogVisible = false;
      });
    },
    editCategory() {
      console.log("修改", this.category);
      var { catId, name, icon, productUnit } = this.category;//解構 宣告取出物件中的4個屬性
      this.$http({
        url: this.$http.adornUrl("/product/category/update"),
        method: "post",
        data: this.$http.adornData({ catId, name, icon, productUnit }, false), //屬姓名相同可以簡寫
      }).then(({ data }) => {
        this.$message({
          type: 'success',
          message: '修改成功!'
        });
        this.getMenus();
        this.expandedKeys = [this.category.parentCid];
        this.dialogVisible = false;
      });
    },
    remove(node, data) {
      let ids = [data.catId];
      //  console.log(node, data);
      this.$confirm(`是否刪除${data.name}菜單?`, '提示', {
        confirmButtonText: '確定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl("/product/category/delete"),
          method: "post",
          data: this.$http.adornData(ids, false),
        }).then(({ data }) => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          this.getMenus();
          this.expandedKeys = [node.parent.data.catId];
        });

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });

    },
  },
  //生命週期 - 創建完成
  created() {
    // console.log("創建完成");
    this.getMenus();
  },
};
</script>
