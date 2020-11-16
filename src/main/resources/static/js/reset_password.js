let url_1 = new URL(window.location.href);
//http://127.0.0.1/user/find_password?result=user_not_found

let result = url_1.searchParams.get('result');
if (result !== null) {
    if (result === 'key_not_found') {
        alert('잘못된 접근이거나 비밀번호 재설정 유효기간이 만료되었습니다.');
        window.history.back();
    } else if (result === 'success') {
        window.location.href='/users_members/reset_success';
    }
}
