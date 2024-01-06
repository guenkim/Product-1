<template>
  <div class="form-group">
    <label>{{ label }}</label>
    <input
        :value="subject"
        @input="onInput"
        type="text"
        class="form-control"
    >
    <div
        v-if="error"
        class="text-red"
    >
      {{ error }}
    </div>
  </div>
</template>
<script>
//useContext는 vue 3.4버젼 부터 deprecated 되었음.
//import { useContext } from 'vue';
import { getCurrentInstance } from "vue";
export default {
  props: {
    label: {
      type: String,
      required: true
    },
    error: {
      type: String,
      required: true
    },
    subject: {
      type: String,
      required: true
    }
  },
  setup() {
    //useContext는 vue 3.4버젼 부터 deprecated 되었음.
    const { emit } = getCurrentInstance();
    const onInput = (e) => {
      emit('update:subject', e.target.value);
    };

    return {
      onInput
    }
  }
}
</script>

<style scoped>
.text-red {
  color: red;
}
</style>