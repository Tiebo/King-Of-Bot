<template>
	<PlayGround />
</template>

<script>
import PlayGround from "@/components/PlayGround";
import { onMounted, onUnmounted } from "vue";
import { useStore } from "vuex";

export default {
	name: "PkIndexView",
	components: {
		PlayGround,
	},
	setup() {
		const store = useStore();
		const websocketUrl = `ws://localhost:3000/websocket/${store.state.user.token}`;
		let socket = null;
		localStorage.removeItem("current_page");
		store.commit("updateIsRecord", false);

		onMounted(() => {
			socket = new WebSocket(websocketUrl);
			socket.onopen = () => {
				store.commit("updateSocket", socket);
			}
			socket.onmessage = (msg) => {
				const data = JSON.parse(msg.data);
				if (data.event === "start-match") {
					store.commit("updateOpponent", {
						username: data.opponent_username,
						photo: data.opponent_photo,
					});
					setTimeout(() => {
						store.commit("updateStatus", "playing");
					}, 200);
					store.commit("updateGame", data.game);
				} else if (data.event === "move") {

					const game = store.state.pk.gameObject;
					const [snake0, snake1] = game.snakes;

					snake0.set_direction(data.a_direction);
					snake1.set_direction(data.b_direction);

				} else if (data.event === "result") {

					const game = store.state.pk.gameObject;
					const [snake0, snake1] = game.snakes;

					if (data.loser === "all" || data.loser === "A") {
						snake0.status = "die";
					}
					if (data.loser === "all" || data.loser === "B") {
						snake1.status = "die";
					}
					store.commit("updateLoser", data.loser);
				}
			}
			socket.onclose = () => {
			}
		})

		onUnmounted(() => {
			socket.close();
			store.commit("updateLoser", "none");
			store.commit("updateStatus", "matching");
			store.commit("updateOpponentToInit");
		})
	}

}
</script>

<style scoped>

</style>