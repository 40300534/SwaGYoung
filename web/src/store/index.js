import Vue from "vue"
import Vuex from "vuex"
import state from './state.js'
import actions from './actions.js'

Vue.use(Vuex)

const store = new Vuex.Store({
	state,
	actions
})

export default store