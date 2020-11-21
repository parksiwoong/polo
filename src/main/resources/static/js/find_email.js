let url = new URL(window.location.href);
let result = url.searchParams.get("result");
if (result !== null) {
    if (result === "no_matching_user") {
        alert("일치하는 회원을 찾을 수 없습니다.");
        window.location.href = '/users_members/find_email';
    }
}

let selectform = window.document.body.querySelector("#id_reset-form");
selectform.onsubmit = function () {

    return false;
}

