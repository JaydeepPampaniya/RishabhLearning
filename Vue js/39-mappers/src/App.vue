<template>
  <base-container title="Vuex">
    <the-counter></the-counter>
    <favorite-value></favorite-value>
    <button @click="increment">Add 1</button>
    <button @click="increase({ value: 10 })">Add 10</button>

    <change-counter></change-counter>
  </base-container>
</template>

<script>
import BaseContainer from './components/BaseContainer.vue';
import TheCounter from './components/TheCounter.vue';
import ChangeCounter from './components/ChangeCounter.vue';
import FavoriteValue from './components/FavoriteValue.vue';
import { mapActions } from 'vuex';

export default {
  components: {
    BaseContainer,
    TheCounter,
    ChangeCounter,
    FavoriteValue,
  },
  methods: {
    // addOne() {
    //   // this.$store.dispatch('increase', { value: 10 });
    //   this.$store.dispatch({
    //     type: 'increase',
    //     value: 10
    //   });
    // },
    ...mapActions(['increment', 'increase']),
  },
};
</script>

<style>
* {
  box-sizing: border-box;
}

html {
  font-family: sans-serif;
}

body {
  margin: 0;
}
</style>
