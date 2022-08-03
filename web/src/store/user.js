import $ from 'jquery';

export default {
    state: {
        id: "",
        username: "",
        photo: "",
        token: "",
        description: "",
        is_login: false,
        pulling_info: true,
    },
    mutations: {
        updateUser(state, user) {
            state.id = user.id;
            state.username = user.username;
            state.photo = user.photo;
            state.is_login = user.is_login;
        },
        updateToken(state, token) {
            state.token = token;
        },
        logout(state) {
            state.id = "";
            state.username = "";
            state.photo = "";
            state.token = "";
            state.is_login = false;
        },
        updatePullingInfo(state, pulling_info) {
            state.pulling_info = pulling_info;
        }
    },
    actions: {
        login(context, data) {
            $.ajax({
                url: "http://localhost:3000/user/account/token/",
                type: "POST",
                data: {
                    username: data.username,
                    password: data.password,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        localStorage.setItem("jwt_token", resp.token);
                        context.commit("updateToken", resp.token);
                        data.success(resp);
                    }
                },
                error(resp) {
                    data.error(resp);
                }
            })
        },
        getInfo(context, data) {
            $.ajax({
                url: "http://localhost:3000/user/account/info/",
                type: "GET",
                headers: {
                    authorization: "Bearer " + context.state.token,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        context.commit("updateUser", {
                            ...resp,
                            is_login: true,
                        });
                        data.success(resp);
                    } else {
                        data.error(resp);

                    }
                },
                error(resp) {
                    data.error(resp);
                }
            })
        },
        register(context, data) {
            $.ajax({
                type: "POST",
                url: "http://localhost:3000/user/account/register/",
                data: {
                    username: data.username,
                    password: data.password,
                    confirmPassword: data.confirmPassword,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        data.success(resp);
                    } else {
                        data.error(resp);
                    }
                }
            });
        },
        logout(context) {
            localStorage.removeItem("jwt_token");
            context.commit("logout");
        },
        update_password(context, data) {
            $.ajax({
                type: "POST",
                url: "http://localhost:3000/user/account/update/password/",
                data: {
                    oldPassword: data.old_password,
                    newPassword: data.new_password,
                    confirmPassword: data.confirm_password,
                },
                headers: {
                    authorization: "Bearer " + context.state.token,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        context.commit("updateToken", resp.token);
                        localStorage.setItem("jwt_token", resp.token);
                        data.success(resp);
                    } else {
                        data.error(resp);
                    }
                },
                error(resp) {
                    data.error(resp);
                },
            });
        }

    },
    modules: {}
}
