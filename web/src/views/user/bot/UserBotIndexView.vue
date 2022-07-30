<template>
  <ContentField>我的Bot</ContentField>
</template>

<script>
import ContentField from "@/components/ContentField";
// import $ from 'jquery'
// import { useStore } from "vuex";

export default {
  name: "UserBotIndexView",
  components: { ContentField },
  setup() {
    // const store = useStore();
    // $.ajax({
    //   type: "GET",
    //   url: "http://localhost:3000/user/bot/getlist/",
    //   headers: {
    //     Authorization: "Bearer " + store.state.user.token,
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   }
    // });
  }
}
</script>

<style scoped>

</style>