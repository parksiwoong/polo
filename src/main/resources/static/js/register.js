// if (buttonLogin !== null) {
//     buttonLogin.addEventListener("click", function () {
//         let inputLoginEmail = window.document.body.querySelector("#input-login-email");
//         let inputLoginPassword = window.document.body.querySelector("#input-login-password");
//         // regexlib.com
//         let emailRegex = new RegExp("\"^(?=.{4,100}.$)([0-9a-zA-Z][0-9a-zA-Z\\\\-_.]*[0-9a-zA-Z])@([0-9a-z][0-9a-z\\\\-]*[0-9a-z]\\\\.)?([0-9a-z][0-9a-z\\\\-]*[0-9a-z])\\\\.([a-z]{2,15})(\\\\.[a-z]{2})?$\"");
//         let passwordRegex = new RegExp("^([0-9a-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:'\",<.>/?]{4,100})$");
//         // 자바 : "문자열".matches(정규식) <boolean>
//         // JS : [정규식].test([문자열]) <boolean>
//         if (!emailRegex.test(inputLoginEmail.value)) {
//             alert("올바른 이메일을 입력해주세요.");
//             inputLoginEmail.focus();
//         } else if (!passwordRegex.test(inputLoginPassword.value)) {
//             alert("올바른 패스워드를 입력해주세요.");
//             inputLoginPassword.focus();
//         } else {
//             function callback(responseText) {
//                 if (responseText === "NORMALIZATION_FAILURE") {
//                     alert("잘못된 입력이 있습니다.");
//                 } else if (responseText === "FAILURE") {
//                     alert("로그인 실패");
//                 } else if (responseText === "LIMIT_EXCEEDED") {
//                     alert("잠시 후 다시 시도해 주세요.");
//                 } else if (responseText === "IP_BLOCKED") {
//                     alert("해당 IP는 차단된 상태입니다. 잠시 후 다시 시도해 주세요.");
//                 } else if (responseText === "SUCCESS") {
//                     alert("로그인 성공");
//                     window.location.reload();
//                 }
//
//             }
//             function fallback() {
//                 alert("로그인 도중 예기치 못한 오류가 발생하였습니다. 잠시 후 다시 시도해 주시기 바랍니다.");
//             }
//
//             let formData = new FormData();
//             formData.append("email", inputLoginEmail.value);
//             formData.append("password", inputLoginPassword.value);
//             xhr("POST", "/admin/apis/login", callback, fallback, formData);
//         }
//     });
//     }


let registerForm = window.document.body.querySelector("#register-form");
registerForm.onsubmit = function () {
    let emailRegex = new RegExp("^(?=.{4,100}.$)([0-9a-zA-Z][0-9a-zA-Z\\-_.]*[0-9a-zA-Z])@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$");
    let passwordRegex = new RegExp("^([0-9a-zA-Z`~!@#$%^&*(\\-_=+)]{4,100})$");

    if (registerForm.elements["email"].value === "") {
        alert("이메일을 입력해주세요.");
        registerForm.elements["email"].focus();
        return false;
    }else if(!emailRegex.test(registerForm.elements["email"].value)){
        alert("이메일형식이 틀립니다.");
        registerForm.elements["email"].focus();
        return false;
    } else if (registerForm.elements["password"].value === "") {
        alert("비밀번호를 입력해주세요.");
        registerForm.elements["password"].focus();
        return false;
    }else if(!passwordRegex.test(registerForm.elements["password"].value)){
        alert("비밀번호형식이 틀립니다.");
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