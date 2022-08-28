<template>
  <div ref="parent" class="gamemap">
    <canvas ref="canvas" tabindex="0"></canvas>
  </div>
  <div class="point">{{ point }}</div>
</template>

<script>
import { GameMap } from "@/assets/scripts/GameMap";
import { onMounted, ref } from 'vue'
import { useStore } from "vuex";
export default {
  setup() {
    const store = useStore();
    let parent = ref(null);
    let canvas = ref(null);
    let point = ref('');

    if (store.state.pk.a_id == store.state.user.id) {
      point.value = '红色方';
    } else if (store.state.pk.b_id == store.state.user.id) {
      point.value = '蓝色方'
    }
    //等待parent和canvas都挂载（与标签获得联系）结束以后，再调用onMounted里的函数
    onMounted(() => {
      store.commit("updateGameObject", new GameMap(canvas.value.getContext('2d'), parent.value, store));
    });


    return {
      parent,
      canvas,
      point,
    }
  }
}
</script>

<style scoped>
div.gamemap {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  /*水平居中*/
  align-items: center;
  /*数值居中*/
}

div.point {
  text-align: center;
  font-size: 36px;
  color: lightblue;
  font-weight: bold;
}
</style>