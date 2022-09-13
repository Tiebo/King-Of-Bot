import { createStore } from 'vuex'
import MutationsUser from './user';
import ModulePk from './pk';
import ModuleRecord from './record';

export default createStore({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    user: MutationsUser,
    pk: ModulePk,
    record: ModuleRecord
  }
})
