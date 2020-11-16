<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <meta charset="utf-8">
    <title>다음 지도 API</title>
    <link rel="stylesheet" href="/css/index.css">
    <link rel="stylesheet" href="/css/bottom.css">
    <link rel="stylesheet" href="/css/mapapp.css">

</head>
<body>
<%@ include file="../main/top.jsp" %>

<div class="map">
<div><h2>찾아오는길</h2></div>

<div id="map" ></div>
</div>
<script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=2aead2328a32590720ca062b80e5af02"></script>
<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(35.869348,128.595969), // 지도의 중심좌표
            level: 1, // 지도의 확대 레벨
            mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
        };

    // 지도를 생성한다
    var map = new kakao.maps.Map(mapContainer, mapOption);
    //지도마커
    var marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(35.869348, 128.595969),
        map: map

    });
    var map = new kakao.maps.Map(container, options);

    container.style.width = '120px';
    container.style.height = '100px';

    map.relayout();
    // 지형도 타일 이미지 추가
    map.addOverlayMapTypeId(kakao.maps.MapTypeId.TERRAIN);

</script>
<%@ include file = "../main/bottom.jsp" %>
</body>
</html>