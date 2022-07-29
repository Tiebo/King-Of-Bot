import {createStore} from 'vuex'
import MutationsUser from './user';

export default createStore({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    user: MutationsUser
  }
})
