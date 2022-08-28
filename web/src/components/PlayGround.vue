<template>
  <div class="playground">
    <GameMap v-if="$store.state.pk.status === 'playing'" />
    <MatchGround v-if="$store.state.pk.status === 'matching'" />
  </div>
</template>

<script>
import GameMap from "@/components/GameMap";
import MatchGround from "./MatchGround.vue";

export default {
  name: "PlayGround",
  components: {
    GameMap,
    MatchGround
  }
}
</script>

<style scoped>
.playground {
  width: 60vw;
  height: 70vh;
  margin: 40px auto;
}
</style>

