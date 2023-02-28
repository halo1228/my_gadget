<template>
  <el-cascader class="main-choose-editor"
               collapse-tags
               placeholder="请选择父组织"
               ref="treeList"
               v-model="treeValue"
               :props="optionProps"
               @change="treeChange"
               :show-all-levels="false"
               clearable></el-cascader>
</template>

<script>
import {LazyTree} from "@/api/sys/dept";

export default {
  name: "SelectTree",
  data(){
    return {
      treeValue:'',
      optionProps: {
        lazy: true,
        checkStrictly: true,
        emitPath: true,//false 选中值 只包含value 没有其他
        lazyLoad(node, resolve) {
          const {level} = node;
          let datas = [];
          let code = '0';
          if (level !== 0) {
            code = node.data.value;
          }
          LazyTree({pId: code}).then(response => {
            datas = response.data.map((value, i) => ({
              value: value.id,
              label: value.name,
            }));
            resolve(datas);
          }).catch(e => {
            //请求出错处理
            datas = [];
            resolve(datas);
          })
        },
      },
    }
  },methods:{
    treeChange(value) {
      this.$emit('selectValue', value);
    },
  }
}
</script>

<style scoped>

</style>
