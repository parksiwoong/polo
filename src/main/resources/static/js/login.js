let loginForm = window.document.body.querySelector("#login-form");
loginForm.onsubmit = function (){
    let emailInput = loginForm.elements["email"];
    let passwordInput = loginForm.elements["password"];
    if(emailInput.value === ""){
        alert("이메일을 입력해주세요");
        emailInput.focus();
        return false;
    }else if(passwordInput.value === ""){
        alert("비밀번호를 입력해주세요");
        passwordInput.focus();
        return false;
    }return true;
}