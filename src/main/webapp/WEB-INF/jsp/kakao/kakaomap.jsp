<%@ page import="popor.com.vos.users.UserVo" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/jsp/main/top.jsp" %>
<div class="wrap" style="padding-top: 2%;     background: #f5f5f5;" >


<div class="map">
<div><h2>찾아오는길</h2></div>

<div id="map" ></div>
</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0e7db3a737cbd84734c3d59e62e1f667"></script>
<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(35.869348,128.595969), // 지도의 중심좌표
            level: 3, // 지도의 확대 레벨
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
