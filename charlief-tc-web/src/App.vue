<script setup lang="ts">
import UploadBox from './components/UploadBox.vue'
import UploadTable from './components/UploadTable.vue'
</script>

<template>
  <div class="p-4">
    <UploadBox />
    <div class="my-2">
      <el-button type="primary">选择文件</el-button>
      <el-button type="primary">选择文件夹</el-button>
    </div>
    <UploadTable />
  </div>
</template>
