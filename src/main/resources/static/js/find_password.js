let url = new URL(window.location.href);
//http://127.0.0.1/user/find_password?result=user_not_found

let result = url.searchParams.get('result');
if (result !== null) {
    if (result === 'user_not_found') {
        alert('정보가 일치하는 회원을 찾을 수 없습니다.');
        window.history.back();
    } else if (result === 'email_sent') {
        window.location.href='/user_members/email_sent';
    }
}