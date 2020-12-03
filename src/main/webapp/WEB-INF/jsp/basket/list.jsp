<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="popor.com.vos.users.UserVo" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/jsp/main/top.jsp" %>
<div class="wrap" style="padding-top: 14%;     background: #f5f5f5;" >
    <script>
        function basketDelete($deleteBtn){
            let basketIndex = $deleteBtn.getAttribute("data-basket-index");
            let url = '/basket/delete?basketIndex=' + basketIndex;
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
                alert('로그인이 필요합니다.');
            } else {
                alert("삭제성공하였습니다.");
                location.reload();
            }
        }

        function fallback() {
            alert('삭제 실패');
        }
        jQuery(function($){
            $('table.shop').each(function(){
                var $table = $(this);
                var $totalPrice = $('#totalPrice');
                var calc = function(){
                    var totalPrice = 0;

                    $table.find('tr').each(function(){
                        var checked = $(this).find(':checkbox').prop('checked');

                        if(checked !== true)
                            return;

                        var $count = $(this).find('input[type="number"]');
                        var count = $count.val();

                        if(count < 0)
                            count = 0;

                        var price = $count.data('price') * 1;

                        if(price < 0)
                            price = 0;

                        totalPrice += price * count;
                    });

                    $totalPrice.text((totalPrice + '').replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
                }

                $table.on('change keyup', 'input', calc);
                calc();
            });
        });
    </script>
<style>
    .table-form{table-layout: fixed; width: 100%; border-collapse: collapse;}
    .table-form th{width: 30%}
    .table-form th,
    .table-form td{padding: 4px 6px; border: 1px solid #888}
</style>
    <table class="table-form" style="padding-top: 10%;">
        <tr>
            <th>아이디</th>
            <td>${userVo.email}</td>
        </tr>
        <tr>
            <th>전화번호</th>
            <td><input type="text" name="contact" value="${userVo.contact}"></td>
        </tr>
        <tr>
            <th>주소</th>
            <td><input type="text" name="address" value=""></td>
        </tr>
        <!-- 다음 주소 API -->
    </table>
    <table class="shop">
        <thead>
        <tr>
            <th>&nbsp;</th>
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
            <c:when test="${empty basketListVoList}">
                <tr>
                    <td colspan="8" style="text-align: center; color: #ff0000;">
                        장바구니에 담긴 상품이 없습니다.
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="basketListVo" items="${basketListVoList}">
                    <c:set var="itemVo" value="${basketListVo.itemVo}" />
                    <tr>
                        <td><input type="checkbox" name="item_index" value="${itemVo.index}" checked /></td>
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
                        <td><input type="number" name="count-${itemVo.index}" value="${basketListVo.count}" data-price="${itemVo.price}" min="0" style="width: 40px; text-align: right;"></td>
                        <td><button type="button" data-basket-index="${basketListVo.index}" onclick="return basketDelete(this);">삭제</button></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <div style="margin: 12px auto; text-align: center;">
        <span>총액:
            <span id="totalPrice"></span>
        </span>
        <button type="button">결제</button>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/main/bottom.jsp" %>
