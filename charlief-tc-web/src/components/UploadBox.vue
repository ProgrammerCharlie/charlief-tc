<template>
  <div
    class="w-full h-full p-6 text-[#909399] rounded-lg border border-slate-400 border-dashed flex flex-col justify-center items-center gap-4"
    @dragenter="dragenterHandler"
    @dragover="dragoverHandler"
    @drop="dropHandler"
  >
    <div class="flex gap-2">
      <img src="@/assets/images/upload.png" width="32" alt="upload" />
      <div>将目录或多个文件拖拽到此进行扫描</div>
    </div>
    <div>支持的文件类型: jpg, jpeg, png, pdf, doc, docx, gif, mp3</div>
    <div>每个文件允许的最大尺寸: 1MB</div>
  </div>
</template>

<script setup lang="ts">
import { ElMessage } from 'element-plus'
import { getCurrentInstance } from 'vue'

const vc = getCurrentInstance()

const dragenterHandler = (e: DragEvent) => {
  e.preventDefault()
}

const dragoverHandler = (e: DragEvent) => {
  e.preventDefault()
}

const dropHandler = async (e: DragEvent) => {
  e.preventDefault()
  const items = e.dataTransfer?.items!

  for await (const item of items) {
    const entry = item.webkitGetAsEntry()
    // console.log(entry)
    if (entry?.isFile) {
      // 文件
      // @ts-ignore
      entry.file((file) => {
        vc?.proxy?.$mitt.emit('get-file', file)
      })
    } else {
      // 文件夹
      ElMessage.warning('暂不支持文件夹上传')
    }
  }
}
</script>
