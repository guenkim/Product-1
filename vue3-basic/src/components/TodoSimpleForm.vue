<template>
  <form  @submit.prevent="onSubmit">
    <div class="d-flex">
      <div class="flex-grow-1 mr-2">
        <!-- data binding -->
        <input
            class="form-control"
            type="text"
            v-model="todo"
            placeholder="Type new to-do"
        >
      </div>
      <div>
        <!-- click event -->
        <button class="btn btn-primary" type="submit">
          Add todo
        </button>
      </div>
    </div>
    <div v-if="hasError" style="color:red">
      This Field cannot be empty.
    </div>
  </form>
</template>
<script>
import {getCurrentInstance, ref} from "vue";

 export default {
   emits:['add-todo'],
   setup(){
     const { emit } = getCurrentInstance();
   //setup(props,context){
     const todo = ref("");
     const hasError = ref(false);
     const onSubmit = () => {
       if(todo.value===''){
         hasError.value=true;
       }else{
         //부모 컴포넌트로 data 전달
         //context.emit('add-todo',
         emit('add-todo',
             {
               //id: Date.now(),
               subject: todo.value,
               completed:false
             }
         );
         hasError.value=false;
         todo.value='';
       }
     };
     return {
       todo,
       hasError,
       onSubmit
     }
   }

 }
</script>
<style>

</style>