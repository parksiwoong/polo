<%@ page import="popor.com.vos.bbs.BoardReadResponseVo" %>
<%@ page import="popor.com.vos.users.UserVo" %>
<%@ page import="popor.com.utility.Variable" %>
<%@ page import="popor.com.vos.CommentItemVo" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%
    Object boardReadResponseVoObject = request.getAttribute("BoardReadResponseVo");
    BoardReadResponseVo boardReadResponseVo = null;
    if (boardReadResponseVoObject instanceof BoardReadResponseVo) {
        boardReadResponseVo = (BoardReadResponseVo) boardReadResponseVoObject;
    }

    UserVo userVo = Variable.getUserVo(request);
    if (userVo == null)
        userVo = Variable.getAnonymousUserVo();
%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>글 읽기</title>
    <script defer src="js/read.js"></script>
</head>
<body>
<%
    assert boardReadResponseVo != null; // 아무 의미 없다. 사용 지양
    switch (boardReadResponseVo.getBoardReadResult()) {
        case NO_MATCHING_ARTICLE_ID:
            out.print("<script>alert('해당 게시글을 찾을 수 없습니다.'); window.history.back();</script>");
            return;
        case NO_MATCHING_ID:
            out.print("<script>alert('존재하지 않는 게시판입니다.'); window.history.back();</script>");
            return;
        case NOT_ALLOWED:
            out.print("<script>alert('해당 게시글을 읽을 권한이 없습니다.'); window.history.back();</script>");
            return;
    }
%>
<div>제목 : <%= boardReadResponseVo.getTitle() %>
</div>
<div>작성자 : <%= boardReadResponseVo.getWriter() %>
</div>
<div>내용 : <%= boardReadResponseVo.getText() %>
</div>
<form id="comment-form" method="post">
    <input type="text" name="text" maxlength="100" placeholder="댓글" autofocus>
    <input type="submit" value="작성">
</form>
<div>
    <div>댓글 : <%= boardReadResponseVo.getComments().size() %>개</div>
    <%
        for (CommentItemVo comment : boardReadResponseVo.getComments()) {
            out.println(String.format("<div><span>%s</span><span>%s</span><span>%s</span></div>",
                    comment.getWriter(),
                    comment.getText(),
                    comment.getWrittenAt()));
        }
    %>
</div>
<%
    String id = request.getParameter("id");
    String pageNum = request.getParameter("page");
    String what = request.getParameter("what");
    String keyword = request.getParameter("keyword");
    if (what == null || keyword == null) {
        out.print("<a href=\"/board?id=" + id + "&page=" + pageNum + "\">목록으로</a>");
    } else {
        out.print("<a href=\"/search?id=" + id + "&page=" + pageNum + "&what=" + what + "&keyword=" + keyword + "\">목록으로</a>");
    }

    boolean delete;
    if (userVo.getLevel() == 1) {
        delete = true;
    } else if (userVo.getLevel() < 10 && userVo.getNickname().equals(boardReadResponseVo.getWriter())) {
        // TODO : 닉네임 말고 유저 ID로 비교하게 해주세요.
        delete = true;
    } else {
        delete = false;
    }
    if (delete) {
        out.println("<form id=\"delete-form\" method=\"post\">");
        out.println("<input type=\"hidden\" name=\"_method\" value=\"DELETE\">");
        out.println("<input type=\"submit\" value=\"삭제\">");
        out.println("</form>");
    }
%>
</body>
</html>











