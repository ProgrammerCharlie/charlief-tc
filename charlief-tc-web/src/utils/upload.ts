export default async (url: string, file: File) => {
  const xhr = new XMLHttpRequest()
  xhr.open('POST', url, true)
  xhr.setRequestHeader('Content-Type', file.type)

  xhr.upload.onprogress = function (e) {}

  xhr.onload = function (e) {
    if (xhr.status === 200) {
      console.log('success')
    } else {
      console.log('err')
    }
  }
  xhr.send(file)
}
