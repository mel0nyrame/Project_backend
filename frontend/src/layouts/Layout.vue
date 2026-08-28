<script setup>
  import Button from "primevue/button"
  import Toast from 'primevue/toast';
  import { useRouter } from 'vue-router'
  import 'primeicons/primeicons.css'
  import { useToast } from 'primevue';


  const router = useRouter()
  const toast = useToast();

  const showStudent = () => {
      toast.add({ severity: 'success', summary: 'Success', detail: '切换到学生管理', life: 2000 });
  };

  const showCourse = () => {
      toast.add({ severity: 'success', summary: 'Success', detail: '切换到课程管理', life: 2000 });
  };

  const showScore = () => {
      toast.add({ severity: 'success', summary: 'Success', detail: '切换到成绩管理', life: 2000 });
  };

  function goStudent() {
    showStudent()
    router.push("/student")
  }

  function goCourse() {
    showCourse()
    router.push("/course")
  }

  function goScore() {
    showScore()
    router.push("/score")
  }


</script>

<template>
  <Toast />
  <Button class="button-spacing" @click="goStudent" label="学生" icon="pi pi-user"></Button>
  <Button class="button-spacing" @click="goCourse" label="课程" icon="pi pi-bars"></Button>
  <Button @click="goScore" label="成绩" icon="pi pi-check-square"></Button>

  <router-view />

</template>

<style scoped>
  .button-spacing {
    margin-right: 10px;
  }
</style>