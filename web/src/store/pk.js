export default {
    state: {
        socket: null,
        status: "matching", // playing - matching
        opponent_username: "你的对手",
        opponent_photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
        gameMap: null,
        gameObject: null,
        a_id: 0,
        a_sx: 0,
        s_sy: 0,
        b_id: 0,
        b_sx: 0,
        b_sy: 0,
    },
    mutations: {
        updateSocket(state, socket) {
            state.socket = socket;
        },
        updateStatus(state, status) {
            state.status = status;
        },
        updateOpponent(state, data) {
            state.opponent_username = data.username;
            state.opponent_photo = data.photo;
        },
        updateOpponentToInit(state) {
            state.opponent_username = "你的对手";
            state.opponent_photo = "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png";
        },
        updateGameMap(state, gameMap) {
            state.gameMap = gameMap;
        },
        updateGameObject(state, gameObject) {
            state.gameObject = gameObject;
        },
        updateGame(state, game) {
            state.gameMap = game.map;
            state.a_id = game.a_id;
            state.a_sx = game.a_sx;
            state.a_sy = game.a_sy;
            state.b_id = game.b_id;
            state.b_sx = game.b_sx;
            state.b_sy = game.b_sy;
        },
    },
    actions: {
    },
    modules: {}
}
