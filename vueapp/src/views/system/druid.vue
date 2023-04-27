<template>
  <light-frame :src="druidMonitorUrl" />
</template>

<script>
import LightFrame from '@/components/LightFrame'
export default {
  name: 'Druid',
  components: { LightFrame },
  data() {
    return {
      druidMonitorUrl: `${process.env.VUE_APP_BASE_DOMAIN}/druid/index.html`
    }
  }
}
</script>
