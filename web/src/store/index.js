import { createStore } from 'vuex'
import MutationsUser from './user';
import ModulePk from './pk';

export default createStore({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    user: MutationsUser,
    pk: ModulePk
  }
})
