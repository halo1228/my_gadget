<template>
  <section class="app-main">
    <transition name="fade-transform" mode="out-in">
      <keep-alive :include="cachedViews">  <!--这个是新增的-->
        <router-view :key="key" ></router-view>
      </keep-alive>
<!--      <router-view :key="key" />-->
    </transition>
  </section>
</template>

<script>
export default {
  name: 'AppMain',
  computed: {
    key() {
      return this.$route.path
    },
    cachedViews () {
      return this.$store.state.tagsView.cachedViews
    }
  }
}
</script>

<style scoped>
.app-main {
  /*50 = navbar  */
  min-height: calc(100vh - 50px);
  width: 100%;
  position: relative;
  overflow: hidden;
  background-color: #F0F2F5;
}
.fixed-header+.app-main {
  padding-top: 84px;
}
</style>

<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 15px;
  }
}
</style>
