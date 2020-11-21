<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>비밀번호 재설정</title>
  <link rel="stylesheet" href="/css/index.css">
  <link rel="stylesheet" href="/css/bottom.css">
  <script defer src="${pageContext.request.contextPath}/js/reset_password.js"></script>
</head>
<body>
<form method="post" id="resets_password">
  <label>
    <span>비밀번호</span>
    <input type="password" name="password" maxlength="100" autofocus>
  </label>
  <label>
    <span>비밀번호 재입력</span>
    <input type="password" maxlength="100">
  </label>
  <input type="submit" value="재설정">
</form>
</body>
</html>