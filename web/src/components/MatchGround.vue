<template>
    <div class="row matching">
        <div class="col-6" style="text-align:center;">
            <img :src="$store.state.user.photo" alt="">
            <div class="username">{{ $store.state.user.username }}</div>
        </div>

        <div class="col-6" style="text-align:center;">
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
export default {
    name: " MatchGround ",
    components: {
    },
    setup() {
        const store = useStore();

        let nowPkStatus = ref('开始匹配');
        const ToPlaying = () => {
            if (nowPkStatus.value === "开始匹配") {
                nowPkStatus.value = '取消';
                store.state.pk.socket.send(JSON.stringify({
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
        return {
            ToPlaying,
            nowPkStatus
        }
    },

}
</script>


<style scoped>
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