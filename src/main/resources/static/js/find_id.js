let url = new URL(window.location.href);
let result = url.searchParams.get("result");
if (result !== null) {
    if (result === "no_matching_user") {
        alert("일치하는 회원을 찾을 수 없습니다.");
        window.location.href = '/users_members/find_id';
    }
}

let selectform = window.document.body.querySelector("#find_id");
if (selectform !== null) {
    selectform.onsubmit = function () {
        if (selectform.elements["name"].value === "") {
            alert("이름을 입력해주세요");
            selectform.elements["name"].focus();
            return false;
        } else {
            return true;
        }
    }
}

