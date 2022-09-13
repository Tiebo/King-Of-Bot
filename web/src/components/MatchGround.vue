<template>
    <div class="row matching">
        <div class="col-4" style="text-align:center;">
            <img :src="$store.state.user.photo" alt="">
            <div class="username">{{ $store.state.user.username }}</div>
        </div>
        <div class="col-4">
            <div class="user-select-bot">
                <select v-model="select_bot" class="form-select" aria-label="Default select example">
                    <option value=-1 selected>亲自出马</option>
                    <option v-for="bot in bots" :key="bot.id" :value="bot.id">{{bot.title}}</option>
                </select>
            </div>
        </div>
        <div class="col-4" style="text-align:center;">
            <img :src="$store.state.pk.opponent_photo" alt="">
            <div class="username">{{ $store.state.pk.opponent_username }}</div>
        </div>
        <div class="col-12" style="text-align: center;">
            <button @click="ToPlaying" type="button" class="btn btn-primary btn-lg match">{{ nowPkStatus
            }}</button>
        </div>
    </div>
</template>


<script>
import { ref } from 'vue';
import { useStore } from 'vuex';
import $ from 'jquery'

export default {
    name: " MatchGround ",
    components: {
    },
    setup() {
        const store = useStore();
        let bots = ref([]);
        let nowPkStatus = ref('开始匹配');
        let select_bot = ref(-1);
        const ToPlaying = () => {
            if (nowPkStatus.value === "开始匹配") {
                nowPkStatus.value = '取消';
                store.state.pk.socket.send(JSON.stringify({
                    bot_id: select_bot.value,
                    event: "start-matching"
                }));
            } else {
                nowPkStatus.value = '开始匹配';
                store.state.pk.socket.send(JSON.stringify({
                    event: "stop-matching"
                }));
                store.commit("updateOpponentToInit");
            }
        }
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
                    }
                },
            });
        };
        refresh_bots();

        return {
            refresh_bots,
            ToPlaying,
            nowPkStatus,
            bots,
            select_bot
        }
    },

}
</script>


<style scoped>
div.user-select-bot {
    padding-top: 20vh;
}

div.user-select-bot>select {
    width: 60%;
    margin: 0 auto;
}

.matching {
    background-color: rgba(73, 68, 61, 0.5);
}

div>img {
    width: 20vh;
    margin-top: 5vh;
    border-radius: 50%;
}

.match {
    margin-top: 5vh;
    margin-bottom: 2vh;
}

div>div.username {
    padding-top: 2vh;
    font-size: 24px;
    font-weight: bold;
}
</style>