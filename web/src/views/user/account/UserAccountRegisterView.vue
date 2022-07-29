<template>
  <ContentField>
    <div class="row justify-content-md-center">
      <div class="col-3">
        <form @submit.prevent="register">
          <div class="mb-3">
            <label for="username" class="form-label">用户名</label>
            <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">密码</label>
            <input v-model="password" class="form-control" id="password" type="password" placeholder="请输入密码">
          </div>
          <div class="mb-3">
            <label for="confrimPassword" class="form-label">确认密码</label>
            <input v-model="confirmPassword" class="form-control" id="confirmPassword" type="password" placeholder="请输入密码">
          </div>
          <div class="error_message">{{ error_message }}</div>
          <button type="submit" class="btn btn-primary">提交</button>
        </form>
      </div>
    </div>
  </ContentField>
</template>
<script>
import ContentField from "@/components/ContentField";
import { ref } from "vue";
import { useStore } from "vuex";
import router from "../../../router";

export default {
  name: "UserAccountRegisterView",
  components: {
    ContentField,
  },
  setup() {
    const store = useStore();
    let username = ref('');
    let password = ref('');
    let confirmPassword = ref('');
    let error_message = ref('');

    const register = () => {
      error_message.value = "";
      store.dispatch("register", {
        username: username.value,
        password: password.value,
        confirmPassword: confirmPassword.value,
        success() {
          router.push({ name: 'user_account_login' });
        },
        error(resp) {
          error_message.value = resp.error_message;
        }
      })
    }

    return {
      username,
      password,
      confirmPassword,
      register,
      error_message,
    }
  }
}
</script>

<style scoped>
.error_message{
  color: red;
  font-size: 14px;
}
</style>