<template>
  <ContentField>
    <table class="table table-striped table-hover" style="text-align: center;">
      <thead class="">
        <th>玩家</th>
        <th>天梯分</th>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id">
          <td>
            <img :src="user.photo" class="user-photo">
            &nbsp;
            <span class="user-username">{{ user.username }}</span>
          </td>
          <td>
            {{ user.rating }}
          </td>
        </tr>
      </tbody>
    </table>
    <nav aria-label="...">
      <ul class="pagination" style="float: right;">
        <li class="page-item" @click="click_page(-2)">
          <a class="page-link" href="#">上一页</a>
        </li>
        <li v-for="page in pages" :key="page.number" :class="'page-item ' + page.is_active"
          @click="click_page(page.number)">
          <a class="page-link" href="#">
            {{ page.number }}
          </a>
        </li>

        <li class="page-item" @click="click_page(-1)">
          <a class="page-link" href="#">下一页</a>
        </li>
      </ul>
    </nav>
  </ContentField>
</template>

<script>
import ContentField from "@/components/ContentField";
import { useStore } from "vuex";
import $ from 'jquery';
import { ref } from "vue";

export default {
  name: "RanklistIndexView",
  components: { ContentField },
  setup() {
    const store = useStore();
    let users = ref([]);
    let current_users_count = 0;
    let current_page = 1;
    let pages = ref([]);

    const click_page = page => {
      if (page === -2) page = current_page - 1;
      else if (page === -1) page = current_page + 1;
      let max_pages = parseInt(Math.ceil(current_users_count / 5));

      if (page >= 1 && page <= max_pages) {
        pull_page(page);
      }
    }

    const update_pages = () => {
      let max_pages = parseInt(Math.ceil(current_users_count / 5));
      let new_pages = [];
      for (let i = current_page - 2; i <= current_page + 2; i++) {
        if (i >= 1 && i <= max_pages) {
          new_pages.push({
            number: i,
            is_active: i == current_page ? "active" : ""
          });
        }
      }
      pages.value = new_pages;
    }

    const pull_page = page => {
      current_page = page;
      $.ajax({
        type: "GET",
        url: "http://127.0.0.1:3000/ranklist/getlist/",
        data: {
          page,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          users.value = resp.users;
          current_users_count = resp.users_count;
          update_pages();
        },
        error(resp) {
          console.log(resp);
        }
      });
    }

    pull_page(current_page);

    return {
      users,
      current_users_count,
      pages,
      click_page
    }
  }
}
</script>

<style scoped>
.user-photo {
  width: 4vh;
  border-radius: 50%;
}
</style>