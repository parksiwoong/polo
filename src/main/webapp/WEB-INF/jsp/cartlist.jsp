<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/WEB-INF/jsp/main/top.jsp"%>
<script src="https://use.fontawesome.com/c560c025cf.js"></script>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<style>
    .quantity {
        float: left;
        margin-right: 15px;
        background-color: #eee;
        position: relative;
        width: 80px;
        overflow: hidden
    }

    .quantity input {
        margin: 0;
        text-align: center;
        width: 15px;
        height: 15px;
        padding: 0;
        float: right;
        color: #000;
        font-size: 20px;
        border: 0;
        outline: 0;
        background-color: #F6F6F6
    }

    .quantity input.cnt {
        position: relative;
        border: 0;
        width: 100%;
        height: 40px;
        padding: 0;
        text-align: center;
        font-weight: 400;
        font-size: 15px;
        border-radius: 0;
        background-clip: padding-box
    }

    .quantity .minus, .quantity .plus {
        line-height: 0;
        background-clip: padding-box;
        -webkit-border-radius: 0;
        -moz-border-radius: 0;
        border-radius: 0;
        -webkit-background-size: 6px 30px;
        -moz-background-size: 6px 30px;
        color: #bbb;
        font-size: 20px;
        position: absolute;
        height: 50%;
        border: 0;
        right: 0;
        padding: 0;
        width: 25px;
        z-index: 3
    }

    .quantity .minus:hover, .quantity .plus:hover {
        background-color: #dad8da
    }

    .quantity .minus {
        bottom: 0
    }
    .shopping-cart {
        margin-top: 20px;
    }
</style>

<div class="container mb-5" style="margin-top: 75px;">
    <div class="card shopping-cart">
        <div class="card-header bg-dark text-light">
            <i class="fa fa-shopping-cart" aria-hidden="true"></i>  장바구니 목록
        </div>
        <div class="card-body" id="list-body"></div>
        <div class="card-footer">
            <div class="pull-right" style="margin: 10px">
                <a href="/order" class="btn btn-success pull-right" id="btnOrder">주문하기</a>
                <div class="pull-right" style="margin: 5px">
                    전체 금액: <b>0원</b>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/main/bottom.jsp"%>

</body>
</html>

<%--<script>--%>
<%--    cartList();--%>
<%--    function cartList(){--%>
<%--        $.ajax({--%>
<%--            async:false,--%>
<%--            url:"/cart/cartList",--%>
<%--            type:"get",--%>
<%--            dataType:"json",--%>
<%--            success:function(data){--%>
<%--                $(".card-body").html();--%>
<%--                var cartList="";--%>

<%--                if(data.length > 0){--%>
<%--                    for(var datum of data){--%>
<%--                        cartList += "<div name='product"+datum.id+"'><div class='row'><input type='hidden' value='"+datum.id+"' class='cartId' name='id'>";--%>
<%--                        cartList += "<input type='hidden' value='"+datum.productId+"' id='productId' name='productId'>";--%>
<%--                        cartList += "<div class='col-12 col-sm-12 col-md-2 text-center'>";--%>
<%--                        cartList += "<img class='img-responsive' src='"+datum.filePath+"' alt='prewiew' style='max-width: 150px;'><br></div>";--%>
<%--                        cartList += "<div class='col-12 col-sm-12 text-md-center col-md-6'><h5 class='product-name'><strong>";--%>
<%--                        cartList += "<a href='/product/"+datum.productId+"' style='color:black;'>"+datum.productNm+"</a></strong></h5>";--%>
<%--                        cartList += "</div><div class='col-12 col-sm-12 text-sm-center col-md-4 text-md-right row'>";--%>
<%--                        cartList += "<div class='col-5 col-sm-5 col-md-5 text-md-right' style='padding-top: 5px'><h5><strong>";--%>

<%--                        if(datum.saleId>0){--%>
<%--                            var salePrice=0;--%>
<%--                            if(datum.unitCd=='01'){--%>
<%--                                salePrice = datum.price - datum.amount;--%>
<%--                            }else if(datum.unitCd='02'){--%>
<%--                                salePrice = datum.price*(1-(datum.amount/100));--%>
<%--                            }--%>
<%--                            if(salePrice < 0){--%>
<%--                                salePrice = 0;--%>
<%--                            }--%>
<%--                            cartList += number_format(salePrice) + " 원<input type='hidden' value="+salePrice+"' name='price' class='price'></strong></h5></div>";--%>
<%--                        }else{--%>
<%--                            cartList += number_format(datum.price) + " 원<input type='hidden' value="+datum.price+"' name='price' class='price'></strong></h5></div>"   ;--%>
<%--                        }--%>
<%--                        cartList += "<div class='col-5 col-sm-5 col-md-5'><div class='quantity'><input type='number'step='1' max='99' min='1' class='cnt' name='cnt' value='"+datum.cnt+"' size='4'>";--%>
<%--                        cartList += "</div></div><div class='col-2 col-sm-2 col-md-2 text-right'>";--%>
<%--                        cartList += "<button type='button' name='delete' class='btn btn-outline-danger btn-xs'><i class='fa fa-trash' aria-hidden='true'></i>";--%>
<%--                        cartList += "</button> </div></div></div><hr></div>";--%>
<%--                    }--%>
<%--                } else {--%>
<%--                    $("#btnOrder").addClass("disabled");--%>
<%--                    $("#btnOrder").css("opacity", "0.3");--%>
<%--                    cartList += "<div class='col-12 col-sm-12 col-md-12 text-center'>장바구니에 담긴 상품이 없습니다.</div>";--%>
<%--                }--%>

<%--                $("#list-body").html(cartList);--%>
<%--            }--%>
<%--        });--%>
<%--    }--%>

<%--    amountTotal();--%>
<%--    function amountTotal(){--%>
<%--        $.ajax({--%>
<%--            async:false,--%>
<%--            url:"/cart/amountTotal",--%>
<%--            type:"get",--%>
<%--            dataType:"json",--%>
<%--            success:function(data){--%>
<%--                var totalPrice=0;--%>

<%--                for(var datum of data){--%>
<%--                    if(datum.saleId>0){--%>
<%--                        var price=0;--%>
<%--                        if(datum.unitCd=='01'){--%>
<%--                            price = (datum.price - datum.amount)*datum.cnt;--%>
<%--                        }else if(datum.unitCd='02'){--%>
<%--                            price = (datum.price*(1-(datum.amount/100)))*datum.cnt;--%>
<%--                        }--%>
<%--                    }else{--%>
<%--                        price = datum.price*datum.cnt;--%>
<%--                    }--%>
<%--                    if(price < 0){--%>
<%--                        price = 0;--%>
<%--                    }--%>
<%--                    totalPrice+=price;--%>
<%--                }--%>
<%--                $(".pull-right>b").html(number_format(totalPrice) + "원");--%>
<%--            }--%>
<%--        });--%>
<%--    }--%>

<%--    $('button[name=delete]').click(function() {--%>
<%--        if(confirm("해당 상품을 삭제하시겠습니까?")){--%>
<%--            var n = $('button[name=delete]').index(this);--%>
<%--            var listId = $(".cartId:eq(" + n + ")").val();--%>
<%--            var id = 'id=' + $(".cartId:eq(" + n + ")").val();--%>
<%--            $.ajax({--%>
<%--                async:false,--%>
<%--                url : "/cart/delete",--%>
<%--                type : "post",--%>
<%--                data : id,--%>
<%--                dataType : "json",--%>
<%--                success : function(data) {--%>
<%--                    $('div[name=product' + listId + ']').remove();--%>
<%--                    amountTotal();--%>
<%--                }--%>
<%--            });--%>
<%--        }--%>
<%--    });--%>

<%--    $(".cnt").change(function(){--%>
<%--        var n = $('input[name=cnt]').index(this);--%>
<%--        var cnt = $(".cnt:eq(" + n + ")").val();--%>
<%--        var id = $(".cartId:eq(" + n + ")").val();--%>
<%--        var dataList={"cnt":cnt,"id":id};--%>
<%--        $.ajax({--%>
<%--            async:false,--%>
<%--            url : "/cart/update",--%>
<%--            type : "post",--%>
<%--            data : dataList,--%>
<%--            dataType : "json",--%>
<%--            success : function(data) {--%>
<%--                amountTotal();--%>
<%--            }--%>
<%--        });--%>
<%--    });--%>
<%--   </script>--%>