<template>
  <NavBar />
  <router-view />
</template>

<script>
import NavBar from "./components/NavBar.vue";

export default {
  name: "App",
  components: {
    NavBar,
  },
  setup() {
  },
};
</script>

<style>
body {
  background: url("./assets/images/background.png") no-repeat;
  background-size: cover;
  position: relative;
}
</style>
