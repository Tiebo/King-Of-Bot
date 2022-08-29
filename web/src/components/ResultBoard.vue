<template>
    <div class="result-board">
        <div class="result-board-text" v-if="$store.state.pk.loser == 'all'">
            Lose
        </div>
        <div class="result-board-text"
            v-else-if="$store.state.pk.loser == 'A' && $store.state.pk.a_id == $store.state.user.id">
            Lose
        </div>
        <div class="result-board-text"
            v-else-if="$store.state.pk.loser == 'B' && $store.state.pk.b_id == $store.state.user.id">
            Lose
        </div>
        <div class="result-board-text" v-else>
            win
        </div>
        <div class="result-board-btn">
            <button @click="restart" type="button" class="btn btn-primary">再来</button>
        </div>
    </div>
</template>


<script>
import { useStore } from 'vuex';

export default {
    name: " ResultBoard ",
    components: {
    },
    setup() {
        const store = useStore();
        const restart = () => {
            store.commit("updateStatus", "matching");
            store.commit("updateLoser", "none");
            store.commit("updateOpponentToInit");
        };
        return {
            restart
        }
    }
}
</script>


<style scoped>
div.result-board {
    width: 30vw;
    height: 30vh;
    background-color: rgba(50, 50, 50, 0.5);
    position: absolute;
    top: 25vh;
    left: 35vw
}

div.result-board-text {
    text-align: center;
    color: aliceblue;
    font-size: 60px;
    font-weight: bold;
    font-style: italic;
    padding-top: 5vh;
}

div.result-board-btn {
    padding-top: 7vh;
    text-align: center;
}
</style>