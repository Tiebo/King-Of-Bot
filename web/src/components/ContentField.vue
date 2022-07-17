<template>
  <div class="container">
    <div class="card">
      <div class="card-body">
        <slot></slot>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ContentField"
}
</script>

<style scoped>
.card {
  margin-top: 20px;
}
</style>