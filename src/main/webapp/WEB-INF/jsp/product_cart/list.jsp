<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/jsp/main/top.jsp" %>
<div class="wrap" style="padding-top: 9%;    background: #f5f5f5;" >
    <script>
        function addToCart($addToCartBtn){
            let index = $addToCartBtn.getAttribute("data-index");
            let countFormName = $addToCartBtn.getAttribute("data-count-form-name");
            let count = document.body.querySelector('input[name="' + countFormName + '"]').value;
            let url = '/basket/add?itemIndex=' + index + '&count=' + count;
            let xhr = new XMLHttpRequest();

            xhr.open('POST', url);
            xhr.onreadystatechange = () => {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status >= 200 && xhr.status < 300) {
                        callback(xhr.responseText);
                    } else {
                        fallback();
                    }
                }
            };
            xhr.send();
        }

        function callback(response) {
            let result = JSON.parse(response);
            if (result['result'] === 'not_allowed') {
                alert('로그인을 해주세요');
            } else {
                alert("장바구니에 담겼습니다.");
            }
        }

        function fallback() {
            alert('장바구니에 담기 실패');
        }
    </script>


    <p style="padding-top: 10%">전체 상품 수: <fmt:formatNumber value="${totalCount}" pattern="#,###" /></p>
    <table class="shop" >
        <thead>
        <tr>
            <th>사진</th>
            <th>상품명</th>
            <th>가격</th>
            <th>색상</th>
            <th>사이즈</th>
            <th>수량</th>
            <th>&nbsp;</th>
        </tr>
        </thead>
        <tbody style="text-align: center; ">
        <c:choose>
            <c:when test="${empty itemVoList}">
                <tr>
                    <td colspan="7" style="text-align: center; color: #ff0000;">
                        등록된 상품이 없습니다.
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="itemVo" items="${itemVoList}">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${empty itemVo.fileName}">
                                    -
                                </c:when>
                                <c:otherwise>
                                    <img src="/itemImage?index=${itemVo.index}" alt="${itemVo.fileName}" width="180" />
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${itemVo.name}</td>
                        <td><fmt:formatNumber value="${itemVo.price}" pattern="#,###" /></td>
                        <td>${itemVo.color}</td>
                        <td>${itemVo.size}</td>
                        <td><input type="number" name="count-${itemVo.index}" value="1" style="width: 40px; text-align: right;"></td>
                        <td><button type="button" data-index="${itemVo.index}" data-count-form-name="count-${itemVo.index}" onclick="return addToCart(this);">담기</button></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <div style="margin: 12px auto; text-align: center;">
        <c:if test="${!empty pageBlockPrev}">
            <a href="?page=${pageBlockPrev}">&lt;</a>
        </c:if>
        <c:forEach var="i" begin="${pageBlockStart}" end="${pageBlockEnd}">
            <a href="?page=${i}">
                <c:choose>
                    <c:when test="${page == i}">
                        <strong>[${i}]</strong>
                    </c:when>
                    <c:otherwise>
                        ${i}
                    </c:otherwise>
                </c:choose>
            </a>
        </c:forEach>
        <c:if test="${!empty pageBlockNext}">
            <a href="?page=${pageBlockNext}">&gt;</a>
        </c:if>
    </div>
    <div class="shop-add">
        <a href="/add"  class="shop-add-pick">상품 등록</a>
        <a href="/basket/list" class="shop-baskat"><img src="/img/cart.png" style="width:67px;"></a>
    </div>

</div>


    <%@ include file="/WEB-INF/jsp/main/bottom.jsp" %>
