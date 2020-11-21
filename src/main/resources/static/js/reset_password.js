let url = new URL(window.location.href);
//http://127.0.0.1/user/find_password?result=user_not_found

let result = url.searchParams.get('result');
if (result !== null) {
    if (result === 'key_not_found') {
        alert('잘못된 접근이거나 비밀번호 재설정 유효기간이 만료되었습니다.');
        window.history.back();
    } else if (result === 'success') {
        alert('설정완료되였습니다.')
        window.location.href='/polo';
    }
}
