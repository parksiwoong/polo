<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../../../resources/static/css/shop.css">
    <link rel="stylesheet" href="../../../../resources/static/css/index.css">
    <link rel="stylesheet" href="../../../../resources/static/css/bottom.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script defer src="/static/js/total.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>shop</title>
</head>
<body>
<%@ include file="../main/top.jsp" %>
<div style="height: 20vh;"></div>
<div class="bonce">
    <ul class="card">
        <c:forEach var="ProductVo" items="${list}">
        <form>
            <li> ${ProductVo.url}
                <div class="top-section">
                    <img onclick="change_img(this)" id="image-container" src="../../../../resources/static/img/man/m1.jfif" alt="">
                    <div class="nav">
                        <img onclick="change_img(this)" name="m2" src="../../../../resources/static/img/man/m2.jfif" alt="">
                        <img onclick="change_img(this)" src="/img/man/m3.jfif" alt="">
                        <img onclick="change_img(this)" src="../../../../resources/static/img/man/m2.jfif" alt="">
                        <img onclick="change_img(this)" src="../../../../resources/static/img/man/m3.jfif" alt="">
                        <img onclick="change_img(this)" src="../../../../resources/static/img/man/m4.jfif" alt="">
                        <img onclick="change_img(this)" src="../../../../resources/static/img/man/m4.jfif" alt="">
                    </div>
                    <div><span class="price_1">$80</span></div>
                    <%-- quantity --%>
                </div>
                <div class="product-info">
                    <div class="name">polo man T-shirt</div>
                    <div class="dis">size: free
                        <div class="quantity" data-unitprice="80">
                            <span class="plus">+</span>
                            <input type="text" readonly value="1">
                            <span class="minus">-</span>
                        </div>
                    </div>
                    <div class="total_price">total
                        <span class="price">80$</span>
                    </div>
                </div>
                <a href="#"><input class="btn" type="submit" value="Add to Card"></a>
            </li>
        </form>
        </c:forEach>
        <form>
            <li>
                <div class="top-section">
                    <img onclick="change_img_1(this)" id="image-container_1" src="../../../../resources/static/img/girl/g1.jfif" alt="">
                    <div class="nav">
                        <img onclick="change_img_2(this)" src="../../../../resources/static/img/girl/g2.jfif" alt="">
                        <img onclick="change_img_2(this)" src="../../../../resources/static/img/girl/g3.jfif" alt="">
                        <img onclick="change_img_2(this)" src="../../../../resources/static/img/girl/g4.jfif" alt="">
                        <img onclick="change_img_2(this)" src="../../../../resources/static/img/girl/g5.jfif" alt="">
                        <img onclick="change_img_2(this)" src="../../../../resources/static/img/girl/g6.jfif" alt="">
                        <img onclick="change_img_2(this)" src="../../../../resources/static/img/girl/g7.jfif" alt="">
                    </div>
                    <div class="price_1">$70</div>
                </div>
                <div class="product-info">
                    <div class="name">polo girl T-shirt</div>
                    <div class="dis">size: free
                        <div class="quantity" data-unitprice="70">
                            <span class="plus">+</span>
                            <input type="text" readonly value="1">
                            <span class="minus">-</span>
                        </div>
                    </div>
                    <div class="total_price">total
                        <span class="price">70$</span>
                    </div>
                </div>
                <a href="#"><input class="btn" type="submit" value="Add to Card"></a>
            </li>
        </form>


        <form>
            <li>
                <c:forEach items="${BoardResponseVo.articles }" var="aticle">
                <div class="top-section">
                    <img onclick="change_img_2(this)" id="image-container_2" src="../../../../resources/static/img/baby/b1.jfif" alt="">
                    <div class="nav">
                        <img onclick="change_img_2(this)" src="../../../../resources/static/img/baby/b2.jfif" alt="">
                        <img onclick="change_img_2(this)" src="../../../../resources/static/img/baby/b3.jfif" alt="">
                        <img onclick="change_img_2(this)" src="../../../../resources/static/img/baby/b4.jfif" alt="">
                        <img onclick="change_img_2(this)" src="../../../../resources/static/img/baby/b5.jfif" alt="">
                        <img onclick="change_img_2(this)" src="../../../../resources/static/img/baby/b6.jfif" alt="">
                        <img onclick="change_img_2(this)" src="../../../../resources/static/img/baby/b7.jfif" alt="">
                    </div>
                    <div class="price_1">$30</div>
                </div>
                <div class="product-info">
                    <div class="name">polo baby T-shirt</div>
                    <div class="dis">size: free
                        <div class="quantity" data-unitprice="30">
                            <span class="plus">+</span>
                            <input type="text" readonly value="1">
                            <span class="minus">-</span>
                        </div>
                    </div>
                    <div class="total_price">total
                        <span class="price">30$</span>
                    </div>
                </div>
                <a href="#"><input class="btn" type="submit" value="Add to Card"></a>
            </li>
        </form>
    </ul>
</div>
<script type="text/javascript">
    let tops=window.document.body.querySelectorAll('div.top-section');
    console.log(tops.length);
    for(let i=0; i<tops.length; i++ ){
        let imgs=tops[i].querySelectorAll('img');
        for (let j=1;j<imgs.length;j++){
            imgs[j].addEventListener('click',function (){
                imgs[0].src = imgs[j].src;
            })
        }
    }
</script>

<%@ include file="../main/bottom.jsp" %>
</body>
</html>