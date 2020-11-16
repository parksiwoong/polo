let registerForm = window.document.body.querySelector("#register-form");
registerForm.onsubmit = function() {

    if (registerForm.elements["email"].value === "") {
        alert("이메일을 입력해주세요.");
        registerForm.elements["email"].focus();
        return false;
    } else if (registerForm.elements["password"].value === "") {
        alert("비밀번호를 입력해주세요.");
        registerForm.elements["password"].focus();
        return false;
    } else if (registerForm.elements["password-check"].value === "") {
        alert("비밀번호를 다시 한번 입력해주세요.");
        registerForm.elements["password-check"].focus();
        return false;
    } else if (registerForm.elements["password"].value !== registerForm.elements["password-check"].value) {
        alert("비밀번호가 서로 일치하지 않습니다. 다시 한번 확인해주세요.");
        registerForm.elements["password-check"].focus();
        return false;
    } else if (registerForm.elements["name"].value === "") {
        alert("이름을 입력해주세요.");
        registerForm.elements["name"].focus();
        return false;
    } else if (registerForm.elements["nickname"].value === "") {
        alert("닉네임을 입력해주세요.");
        registerForm.elements["nickname"].focus();
        return false;
    } else if (registerForm.elements["contact"].value === "") {
        alert("연락처를 입력해주세요.");
        registerForm.elements["contact"].focus();
        return false;
    } else {
        return true;
    }
}