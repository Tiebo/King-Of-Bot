<template>
  <div class="container">
    <div class="row">
      <div class="col-3">
        <div class="card" style="margin-top: 20px">
          <div class="card-body">
            <img class="img-responsive center-block" :src="$store.state.user.photo" alt="" />
            <hr>
            <div class="username">{{ $store.state.user.username }}</div>
          </div>
        </div>
      </div>
      <div class="col-9">
        <div class="card" style="margin-top: 20px">
          <div class="card-header">
            <span class="header">我的Bot</span>
            <button type="button" class="btn btn-primary float-end" data-bs-toggle="modal"
              data-bs-target="#add-bot-btn">
              添加Bot
            </button>
            <!-- Modal -->
            <div class="modal fade" id="add-bot-btn" tabindex="-1">
              <div class="modal-dialog modal-xl">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">添加Bot</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <div class="mb-3">
                      <label for="bot-title" class="form-label">Bot标题</label>
                      <input v-model="botAdd.title" type="text" class="form-control" id="bot-title"
                        placeholder="请输入bot的标题" />
                    </div>
                    <div class="mb-3">
                      <label for="bot-description" class="form-label">简介</label>
                      <textarea v-model="botAdd.description" class="form-control" id="bot-description" rows="3"
                        placeholder="请输入bot的简介"></textarea>
                    </div>
                    <div class="mb-3">
                      <label for="bot-code" class="form-label">代码</label>
                      <VAceEditor :options="{ fontSize: 18 }" v-model:value="botAdd.content" lang="c_cpp"
                        theme="textmate" style="height: 300px" />
                    </div>
                  </div>
                  <div class="modal-footer">
                    <span class="error-message">{{ botAdd.error_message }}</span>
                    <button type="button" class="btn btn-primary" @click="add_bot">提交</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                      取消
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="card-body">
            <table class="table table-striped table-hover">
              <thead>
                <tr>
                  <th>名称</th>
                  <th>创建时间</th>
                  <th>修改时间</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="bot in bots" :key="bot.id">
                  <td>{{ bot.title }}</td>
                  <td>{{ bot.createtime }}</td>
                  <td>{{ bot.modifytime }}</td>
                  <td>
                    <!-- update-Modal-btn -->
                    <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal"
                      :data-bs-target="'#update-bot-btn' + bot.id">
                      查看
                    </button>
                    <!-- update-Modal -->
                    <div class="modal fade" :id="'update-bot-btn' + bot.id" tabindex="-1">
                      <div class="modal-dialog modal-xl">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">更新Bot</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                          </div>
                          <div class="modal-body">
                            <div class="mb-3">
                              <label for="bot-title" class="form-label">Bot标题</label>
                              <input v-model="bot.title" type="text" class="form-control" id="bot-title"
                                placeholder="请输入bot的标题" />
                            </div>
                            <div class="mb-3">
                              <label for="rating" class="form-label">用户天梯分</label>
                              <input type="text" readonly id="rating" class="form-control" :value="rating">
                            </div>
                            <div class="mb-3">
                              <label for="bot-description" class="form-label">简介</label>
                              <textarea v-model="bot.description" class="form-control" id="bot-description" rows="3"
                                placeholder="请输入bot的简介"></textarea>
                            </div>
                            <div class="mb-3">
                              <label for="bot-code" class="form-label">代码</label>
                              <VAceEditor :options="{ fontSize: 18 }" v-model:value="bot.content" lang="c_cpp"
                                theme="textmate" style="height: 300px" />
                            </div>

                          </div>
                          <div class="modal-footer">
                            <span class="error-message">{{ bot.error_message }}</span>
                            <button type="button" class="btn btn-primary" @click="update_bot(bot)">提交</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                              取消
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>

                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-danger btn-sm" style="margin-left: 10px" data-bs-toggle="modal"
                      :data-bs-target="'#deleteBot' + bot.id">
                      删除
                    </button>
                    <!-- Modal -->
                    <div class="modal fade" :id="'deleteBot' + bot.id" tabindex="-1">
                      <div class="modal-dialog">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title">提示信息</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                          </div>
                          <div class="modal-body">
                            您确定删除该bot么
                          </div>
                          <div class="modal-footer">
                            <button type="button" @click="remove_bot(bot)" class="btn btn-danger">确认</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>


</template>

<script>
import $ from "jquery";
import { ref, reactive } from "vue";
import { useStore } from "vuex";
import { Modal } from 'bootstrap/dist/js/bootstrap'
import { VAceEditor } from 'vue3-ace-editor';
import ace from 'ace-builds';

export default {
  name: "UserBotIndexView",
  components: { VAceEditor },
  setup() {
    ace.config.set(
      "basePath",
      "https://cdn.jsdelivr.net/npm/ace-builds@" + require('ace-builds').version + "/src-noconflict/");

    const store = useStore();
    let bots = ref([]);
    let rating = ref('');
    let botAdd = reactive({
      title: "",
      description: "",
      content: "",
      error_message: "",
    });


    const refresh_bots = () => {
      $.ajax({
        type: "GET",
        url: "http://localhost:3000/user/bot/getlist/",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            bots.value = resp.bot;
            rating.value = resp.rating;
          }
        },
      });
    };

    const add_bot = () => {
      botAdd.error_message = "";
      $.ajax({
        type: "POST",
        url: "http://localhost:3000/user/bot/add/",
        data: {
          title: botAdd.title,
          description: botAdd.description,
          content: botAdd.content,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            botAdd.title = "";
            botAdd.content = "";
            botAdd.description = "";
            Modal.getInstance("#add-bot-btn").hide();
            refresh_bots();
          } else {
            botAdd.error_message = resp.error_message;
          }
        },
      });
    };

    const remove_bot = (bot) => {
      console.log(bot);
      $.ajax({
        type: "POST",
        url: "http://localhost:3000/user/bot/remove/",
        data: {
          bot_id: bot.id,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            Modal.getInstance("#deleteBot" + bot.id).hide();
            refresh_bots();
          }
        }
      });
    };

    const update_bot = (bot) => {
      botAdd.error_message = "";
      $.ajax({
        type: "POST",
        url: "http://localhost:3000/user/bot/update/",
        data: {
          bot_id: bot.id,
          title: bot.title,
          description: bot.description,
          content: bot.content,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            Modal.getInstance("#update-bot-btn" + bot.id).hide();
            refresh_bots();
          } else {
            bot.error_message = resp.error_message;
          }
        },
      });
    };

    refresh_bots();
    return {
      bots,
      refresh_bots,
      botAdd,
      add_bot,
      remove_bot,
      update_bot,
      rating,
    };
  },
};
</script>

<style scoped>
img {
  max-width: 100%;
  height: auto;
  display: block;
  margin-right: auto;
  margin-left: auto;
}

.error-message {
  color: lightcoral;
}

.header {
  font-size: 20px;
}

.username {
  font-size: 18px;
  font-weight: bold;
  text-align: center;
}
</style>
