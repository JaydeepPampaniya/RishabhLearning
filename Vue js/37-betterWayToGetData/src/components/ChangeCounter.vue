<template>
  <button @click="addOne">add</button>
  <div>
    <input type="number" v-model="inputValue" />
    <button @click="addNumberByValue">add value by {{ inputValue }}</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      inputValue: 0,
    };
  },
  methods: {
    addOne() {
      this.$store.commit('increment');
    },
    addNumberByValue() {
      //   this.$store.commit('addValueByInput', { value: this.inputValue });
      this.$store.commit({ type: 'addValueByInput', value: this.inputValue });
    },
  },
};
</script>
