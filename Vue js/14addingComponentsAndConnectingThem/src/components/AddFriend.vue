<template>
  <from @submit.prevent="handleSubmit">
    <div>
      <label for="name"> Name</label>
      <input type="text" id="name" v-model="enteredName" />
    </div>
    <div>
      <label for="phone"> Phone</label>
      <input type="number" id="phone" v-model="enteredPhone" />
    </div>
    <div>
      <label for="email"> Email</label>
      <input type="email" id="email" v-model="enteredEmail" />
    </div>
    <button>Submit</button>
  </from>
</template>

<script>
export default {
  emits: ["add-friends"],
  data() {
    return {
      enteredName: "",
      enteredPhone: "",
      enteredEmail: "",
    };
  },
  methods: {
    handleSubmit() {
      this.$emit(
        "add-friends",
        this.enteredEmail,
        this.enteredName,
        this.enteredPhone
      );
    },
  },
};
</script>
