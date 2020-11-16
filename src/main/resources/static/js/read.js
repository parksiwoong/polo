let commentForm = window.document.body.querySelector('#comment-form');
commentForm.onsubmit = function() {
    if (commentForm.elements["text"].value === '') {
        alert("댓글을 입력해주세요.");
        commentForm.elements["text"].focus();
        return false;
    } else {
        return true;
    }
};