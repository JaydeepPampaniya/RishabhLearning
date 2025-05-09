<template>
  <h3>{{ counter }}</h3>
  <h3>{{ finalCounter }}</h3>
</template>

<script>
export default {
  computed: {
    counter() {
      return this.$store.getters.finalCounter;
    },
    finalCounter() {
      return this.$store.getters.normalizedCounter;
    },
  },
};
</script>
