<%@ page import="popor.com.vos.bbs.BoardResponseVo" %>
<%@ page import="popor.com.enums.bbs.BoardResponseResult" %>
<%@ page import="popor.com.vos.bbs.ArticleVo" %>
<%@ page import="popor.com.vos.users.UserVo" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%
    Object boardResponseVoObject = request.getAttribute("BoardResponseVo");
    BoardResponseVo boardResponseVo = null;
    if (boardResponseVoObject instanceof BoardResponseVo) {
        boardResponseVo = (BoardResponseVo) boardResponseVoObject;
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
    <script defer src="js/board.js"></script>
    <link rel="stylesheet" href="/css/board.css">
    <link rel="stylesheet" href="/css/index.css">
    <link rel="stylesheet" href="/css/bottom.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

<header>

    <script type="text/javascript">

        $(document).ready(function(){
            $('.inb a') .on ('click', function(){
                $(this).addClass('on');
                $(this).siblings().removeClass('on');
            });
        });
    </script>

    <link rel="stylesheet" href="">
    <nav class="clearfix" >
        <div class="left">
            <p>위치:</p>

            <select>
                <option>KOREA 한국어</option></select>

        </div>

        <div class="logo"><a href="/polo"><img src="/img/logo.webp" alt=""></a>

        </div>

        </div>
        <%  Object userVoObject = session.getAttribute("UserVo");
            UserVo userVo = null;
            if(userVoObject instanceof UserVo){
                userVo = (UserVo)userVoObject;
            }if(userVo == null) {

        %>

        <div class="right" style="margin: 10px 0; " >
            <a href="/login" > <input class="button" type="button" value="l o g i n"></a>
            <a href="/register" > <input class="button" type="button" value="Sign Up"></a>
        </div>
        <%
        } else{ // 로그인vo 가 있냐없냐 널이아니면 로그인한상태
            //로그아웃 구현할때는 세션에 있는 로그인 vo를 널ㄹ로 지정해주면 된다.
        %>
        <div class="right" style="margin: 10px 0;">
            <a href="#" > <input class="button" type="button" value="cart"></a>
            <a href="/logout" > <input class="button" type="button" value="logout"></a>
        </div>
        <% } %>
    </nav>

    <div class="inb">

        <a href="/polo" ><span>promotion</span> </a>
        <a href="/list/"><span>store</span> </a>
        <a href="/kakaomap"><span>location</span> </a>
        <a href="/board?id=qna"class="on"><span> QNA </span></a>
        <span></span>
    </div>
    </div>

</header>


<%
    String id = request.getParameter("id");
    String pageNum = request.getParameter("page") == null ? "1" : request.getParameter("page");
    assert boardResponseVo != null; // boardResponseVo 는 Null 이 아니다! 라는 프로그램적 설정
    if (boardResponseVo.getBoardResponseResult() == BoardResponseResult.NO_MATCHING_ID) {
        out.print("<script>alert(\"존재하지 않는 게시판입니다.\"); window.location.href=\"/polo\"</script>");
    } else if (boardResponseVo.getBoardResponseResult() == BoardResponseResult.NOT_AUTHORIZED) {
        out.print("<script>alert(\"해당 게시판을 읽을 권한이 없습니다. 로그인하지 않았다면 로그인 후 시도해주세요.\"); window.location.href=\"/polo\"</script>");
    } else if (boardResponseVo.getBoardResponseResult() == BoardResponseResult.OKAY) {
        String keyword = request.getParameter("keyword");
        String what = request.getParameter("what");
        out.println("<div class=\"baram\">");
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr><th>번호</th><th>제목</th><th>작성자</th><th>작성 일자</th><th>조회수</th></tr>");
        out.println("</thead>");
        out.println("<tbody>");
        for (ArticleVo article : boardResponseVo.getArticles()) {
            String title = boardResponseVo.isSearchResult() ?
                    String.format("<a href=\"read?article_id=%d&id=%s&page=%s&what=%s&keyword=%s\">%s</a>",
                            article.getArticleId(),
                            id,
                            pageNum,
                            what,
                            keyword,
                            article.getTitle()) :
                    String.format("<a href=\"read?article_id=%d&id=%s&page=%s\">%s</a>",
                            article.getArticleId(),
                            id,
                            pageNum,
                            article.getTitle());
            out.println(String.format("<tr><td>%d</td><td>%s [%d]</td><td>%s</td><td>%s</td><td>%d</td></tr>",
                    article.getArticleId(),
                    title,
                    article.getCommentCount(),
                    article.getWriter(),
                    article.getWrittenAt(),
                    article.getHit()));
        }
        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
        out.println("<div class=\"mid\">");
        if (boardResponseVo.getRequestPage() > 1) {
            if (boardResponseVo.isSearchResult()) {
                out.println("<span><a href=\"search?id=" + id + "&page=1&keyword=" + keyword + "&what=" + what + "\">처음</a></span>");
            } else {
                out.println("<span><a href=\"board?id=" + id + "&page=1\">처음</a></span>");
            }
        }
        for (int i = boardResponseVo.getStartPage(); i <= boardResponseVo.getEndPage(); i++) {
            if (i == boardResponseVo.getRequestPage()) {
                out.println("<span><strong>" + i + "</strong></span>");
            } else {
                if (boardResponseVo.isSearchResult()) {
                    out.println("<span><a href=\"search?id=" + id + "&page=" + i + "&keyword=" + keyword + "&what=" + what + "\">" + i + "</a></span>");
                } else {
                    out.println("<span><a href=\"board?id=" + id + "&page=" + i + "\">" + i + "</a></span>");
                    // <span><a href="board?id=fre&page=1">1</a></span>
                    // <span><a href="board?id=fre&page=2">2</a></span>
                    // <span><a href="board?id=fre&page=3">3</a></span>
                    // <span><a href="board?id=fre&page=4">4</a></span>
                    // <span><a href="board?id=fre&page=5">5</a></span>
                }
                //out.println(String.format("<span><a href=\"board?id=%s&page=%d\">%d</a></span>", id, i, i));
            }
        }
        if (boardResponseVo.getMaxPage() > boardResponseVo.getRequestPage()) {
            if (boardResponseVo.isSearchResult()) {
                out.println("<span><a href=\"search?id=" + id + "&page=" + boardResponseVo.getMaxPage() + "&keyword=" + keyword + "&what=" + what + "\">끝</a></span>");
            } else {
                out.println("<span><a href=\"board?id=" + id + "&page=" + boardResponseVo.getMaxPage() + "\">끝</a></span>");
            }
        }
        out.println("</div>");
        if (boardResponseVo.isSearchResult()) {
            out.println("<div class=\"mid\" ><a  href=\"board?id=" + request.getParameter("id") + "\">검색 초기화</a></div>");
        }
    }
%>
<form id="search-form" class="mid" action="/search" method="get">
    <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
    <select name="what">
        <option value="title" selected>제목</option>
        <option value="title_content">제목 + 내용</option>
        <option value="nickname">작성자</option>
    </select>
    <input type="text" name="keyword" maxlength="50" placeholder="검색" autofocus>
    <input type="submit" value="검색">
</form>
<div class="mid" style="height: 23vh;"><a href="write?id=<%= id %>&page=<%= pageNum %>">글 작성</a></div>
<%@ include file = "../main/bottom.jsp" %>
