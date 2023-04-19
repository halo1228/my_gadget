<template>
  <!--封装的默认抽屉 可以定制抽屉内容   -->
    <el-drawer
            :title="title"
            ref="drawer"
            :destroy-on-close="true"
            :visible.sync="formVisible"
            direction="rtl"
            :before-close="handleClose"
            :size="size">
        <slot name="content">一个抽屉</slot>
    </el-drawer>
</template>

<script>

import Bus from "@/bus";

export default {
    name: "DrawerForm",
    data() {
        return {
            visible: false,
        }
    },
    props: {
        formVisible: {
            type: Boolean,
            default: false,
            required: true,
        },
        size: {
            type: String,
            default: '30%',
            required: false,
        },
        title: {
            type: String,
            default: 'title',
            required: false,
        },
    },
    methods: {
        handleClose(done) {//关闭抽屉
            //重置显示属性为false
            this.visible = false;
            //调用父组件抽屉关闭方法 重置抽屉属性
            this.$emit('closeFormDrawer');
        },
    }, watch: {
        formVisible(newVal, oldVal) {
            if (newVal) {
                this.visible = newVal;
            }
        }
    }, created() {
        Bus.$on('closeDefaultDrawer', target => {
            this.$refs.drawer.closeDrawer()
        });
    }
}
</script>

<style scoped>
.el-drawer__header{
    font-weight: 700!important;
}
</style>
