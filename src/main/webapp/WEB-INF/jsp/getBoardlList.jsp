<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
<%--         pageEncoding="UTF-8"%>--%>
<%--&lt;%&ndash;<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>&ndash;%&gt;--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>게시판 글목록</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div align="center">--%>

<%--    <form action="getBoardList.jsp" method="post">--%>
<%--        <table border="1" cellpadding="0" cellspacing="0" width="700">--%>
<%--            <tr>--%>
<%--                <td align="right">--%>
<%--                    <select name="serchCondition">--%>
<%--                        <option value="TITLE">제목--%>
<%--                        <option value="CONTENT">내용--%>
<%--                    </select>--%>
<%--                    <input name="searchKeyword" type="text" />--%>
<%--                    <input type="submit" value="검색" />--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--    </form>--%>

<%--    <table border="1" cellpadding="0" cellspacing="0" width="700">--%>
<%--        <tr>--%>
<%--            <th bgcolor="orange" width="100">번호</th>--%>
<%--            <th bgcolor="orange" width="200">제목</th>--%>
<%--            <th bgcolor="orange" width="150">작성자</th>--%>
<%--            <th bgcolor="orange" width="150">등록일</th>--%>
<%--            <th bgcolor="orange" width="100">조회수</th>--%>
<%--        </tr>--%>

<%--        <c:forEach items="${boardList}" var="board">--%>
<%--            <tr>--%>
<%--                <td>${board.seq }</td>--%>
<%--                <td><a href="getBoard.do?seq=${board.seq }">${board.title }</a></td>--%>
<%--                <td>${board.writer }</td>--%>
<%--                <td>${board.regDate }</td>--%>
<%--                <td>${board.cnt }--%>
<%--            </tr>--%>

<%--        </c:forEach>--%>
<%--    </table>--%>
<%--    <br>--%>
<%--    <a href="insertBoard.jsp">글등록</a>&nbsp;&nbsp;&nbsp;--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>