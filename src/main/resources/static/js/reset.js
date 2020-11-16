let url = new URL(window.location.href);
let result = url.searchParams.get("result"); // = no_matching_user
if (result !== null) {
    if (result === "no_matching_user") {
        alert("일치하는 회원을 찾을 수 없습니다.");
        window.location.href = "/reset";
    }
    if (result === "code_nono") {
        alert("잘못된 코드 입니다.");
        window.location.href = "/reset?step=2";
    }
}

let resetForm = window.document.body.querySelector("#reset-form");
if (resetForm !== null) {
    resetForm.onsubmit = function () {
        //TODO : 정규화
        if (resetForm.elements["email"].value === "") {
            alert("이메일을 입력해주세요.");
            resetForm.elements["email"].focus();
            return false;
        } else if (resetForm.elements["contact"].value === "") {
            alert("연락처를 입력해주세요.");
            resetForm.elements["contact"].focus();
            return false;
        } else {
            return true;
        }
    }
}
let resetForm_id = window.document.body.querySelector("#id_reset-form");
if (resetForm_id !== null) {
    resetForm_id.onsubmit = function () {
        //TODO : 정규화
        if (resetForm_id.elements["name"].value === "") {
            alert("이름을 입력해주세요.");
            resetForm.elements["email"].focus();
            return false;
        } else if (resetForm.elements["contact"].value === "") {
            alert("연락처를 입력해주세요.");
            resetForm.elements["contact"].focus();
            return false;
        } else {
            return true;
        }
    }


//
// let resetCodeForm = window.document.body.querySelector("#reset-code-form");
// if (resetCodeForm !== null) {
//     resetCodeForm.onsubmit = function () {
//         if (resetCodeForm.elements["code"].value === "") {
//             alert("인증 번호를 입력해주세요.");
//             resetCodeForm.elements["code"].focus();
//             return false;
//         } else {
//             return true;
//         }
//     }
}