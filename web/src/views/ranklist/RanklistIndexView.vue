<template>
  <ContentField>排行榜</ContentField>
</template>

<script>
import ContentField from "@/components/ContentField";
import { useStore } from "vuex";
import $ from 'jquery';

export default {
  name: "RanklistIndexView",
  components: {
    ContentField,
  },
  setup() {
    const store = useStore();
    $.ajax({
      type: "POST",
      url: "http://localhost:3000/user/bot/add/",
      data: {
        title: "这是一个标题",
        description: "这是一个描述",
        content: "hello world",
      },
      headers: {
        Authorization: "Bearer " + store.state.user.token, 
      },
      success(resp) {
          console.log(resp);
      },
      error(resp) {
        console.log(resp);
      }
    });
  }
}
</script>

<style scoped>

</style>