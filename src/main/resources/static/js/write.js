let writeForm = window.document.body.querySelector('#write-form');
ClassicEditor.create(writeForm.querySelector('#textarea2'), {
    ckfinder: {
        uploadUrl: '/upload_image'
    }
});
writeForm.onsubmit = function () {
    if (writeForm.elements["title"].value === "") {
        alert("글 제목을 입력해주세요.");
        writeForm.elements["title"].focus();
        return false;
    } else if (writeForm.elements["text"].value === "") {
        alert("글 내용을 입력해주세요.");
        writeForm.elements["text"].focus();
        return false;
    } else {
        return true;
    }
}