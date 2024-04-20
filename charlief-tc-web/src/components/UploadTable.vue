<template>
  <el-table :data="tableData" border stripe show-header style="width: 100%">
    <el-table-column prop="fileName" label="文件名" />
    <el-table-column prop="fileType" label="类型" />
    <el-table-column prop="fileSize" label="大小(KB)" />
    <el-table-column prop="uploadStatus" label="状态">
      <template #="{ row }">
        <el-tag type="info">{{ row.uploadStatus }}</el-tag>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template #="{ row }">
        <el-button type="primary">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <div class="flex gap-2 mt-2">
    <el-tag type="primary">文件数量: {{ tableData.length }}</el-tag>
    <el-tag type="primary">总大小: {{ calcTotalSize() }}KB</el-tag>
  </div>
  <div class="mt-2">
    <el-button type="primary" @click="uploadAllFile">开始上传</el-button>
  </div>
  <div>
    <ul>
      <li v-for="res in result">
        <el-avatar shape="square" :size="32" :src="res" />
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import { reactive, getCurrentInstance } from 'vue'
import axios from 'axios'
import upload from '@/utils/upload'
import { ElMessage } from 'element-plus'

const vc = getCurrentInstance()
interface Row {
  fileName: string
  fileType: string
  fileSize: number
  uploadStatus: string
  file: File
}
const tableData = reactive<Row[]>([])

vc?.proxy?.$mitt.on('get-file', (file) => {
  tableData.push({
    fileName: (file as File).name,
    fileType: (file as File).type,
    fileSize: (file as File).size,
    uploadStatus: '待上传',
    file: file as File
  })
})

const calcTotalSize = () => {
  if (tableData.length === 0) return 0
  return tableData.map((row) => row.fileSize).reduce((a, b) => a + b)
}

const result = reactive<any[]>([])
const uploadAllFile = async () => {
  for await (const item of tableData) {
    axios
      .get('charlief/oss/upload' + `?bucketName=charlief&objectName=${item.fileName}`)
      .then(({ data: res }) => {
        upload(res.uploadUrl, item.file).then(() => {
          item.uploadStatus = '已上传'
          result.push(res.downloadUrl)
        })
      })
  }
  ElMessage.success('全部上传完毕')
}
</script>
