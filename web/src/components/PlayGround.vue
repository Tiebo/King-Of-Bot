<template>
  <div class="playground">
    <GameMap v-if="$store.state.pk.status === 'playing'" />
    <MatchGround v-if="$store.state.pk.status === 'matching'" />
    <ResultBoard v-if="$store.state.pk.loser !== 'none'" />
  </div>
</template>

<script>
import GameMap from "@/components/GameMap";
import MatchGround from "./MatchGround.vue";
import ResultBoard from "./ResultBoard.vue";

export default {
  name: "PlayGround",
  components: {
    GameMap,
    MatchGround,
    ResultBoard,
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

