<template>
  <div id="treeSelect">
    <h1>{{ `基于Element-UI组件改造的树形选择器` }}:</h1>
    <!-- 调用树形下拉框组件 -->
    <SelectTree
      :props="props"
      :options="treeData"
      :value="valueId"
      :clearable="isClearable"
      :accordion="isAccordion"
      @getValue="getValue($event)"
    />
    ID为：{{ valueId }}
  </div>
</template>

<script>
import SelectTree from "@/components/TreeSelect/index.vue";
import {Tree} from "@/api/sys/dept";

export default {
  name: "app",
  components: {
    SelectTree
  },
  data() {
    return {
      treeData:[],
      isClearable: true, // 可清空（可选）
      isAccordion: true, // 可收起（可选）
      valueId: '1627927125087686658', // 初始ID（可选）
      props: {
        // 配置项（必选）
        value: "id",
        label: "name",
        children: "children"
        // disabled:true
      },
      // 选项列表（必选）
      list: [
        //   { id: 1, parentId: 0, name: "一级菜单A", rank: 1 },
        //   { id: 2, parentId: 0, name: "一级菜单B", rank: 1 },
        //   { id: 3, parentId: 0, name: "一级菜单C", rank: 1 },
        //   { id: 4, parentId: 1, name: "二级菜单A-A", rank: 2 },
        //   { id: 5, parentId: 1, name: "二级菜单A-B", rank: 2 },
        //   { id: 6, parentId: 2, name: "二级菜单B-A", rank: 2 },
        //   { id: 7, parentId: 4, name: "三级菜单A-A-A", rank: 3 },
        //   { id: 8, parentId: 7, name: "四级菜单A-A-A-A", rank: 4 },
        //   { id: 9, parentId: 0, name: "一级菜单C", rank: 1 },
        //   { id: 10, parentId: 0, name: "一级菜单end", rank: 1 }
      ],
    };
  },
  created() {
    this.loadTree();
  },
  methods: {
    // 取值
    getValue(value) {
      this.valueId = value;
      console.log(this.valueId);
    },
    getNodeTree(tree) {
      for (var i in tree) {
        if (typeof tree[i] == 'object') {
          this.getNodeTree(tree[i])
        } else {
          this.list.push(
            {
              "id": tree["id"],
              "parentId": tree["parentId"],
              "name": tree["name"]
            }
          )
        }
      }
    }, loadTree() {
      Tree().then(response => {
        console.log(response);
        this.treeData=response.data
        //this.getNodeTree(response);
        this.$nextTick(e=>{
          this.valueId='1627927125087686658'
        })
      })
    }
  }
};
</script>

<style>
#treeSelect {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
