<template>
  <h3>{{ normalizedCounter }}</h3>
  <p>We do more...</p>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
  computed: {
    ...mapGetters(['normalizedCounter']),
    // counter() {
    //   return this.$store.getters.normalizedCounter;
    // },
  },
};
</script>
