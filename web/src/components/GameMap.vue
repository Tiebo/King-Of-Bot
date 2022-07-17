<template>
  <div ref="parent" class="gamemap">
    <canvas ref="canvas"></canvas>
  </div>
</template>

<script>
import {GameMap} from "@/assets/scripts/GameMap";
import {onMounted, ref} from 'vue'

export default {
  setup() {
    let parent = ref(null);
    let canvas = ref(null);
    //等待parent和canvas都挂载（与标签获得联系）结束以后，再调用onMounted里的函数
    onMounted(() => {
      new GameMap(canvas.value.getContext('2d'), parent.value)
    });

    return {
      parent,
      canvas
    }
  }
}
</script>

<style scoped>
div.gamemap {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center; /*水平居中*/
  align-items: center; /*数值居中*/
}
</style>