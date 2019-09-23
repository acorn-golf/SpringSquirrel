<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
#mapwrap{position:relative;overflow:hidden;}
.category, .category *{margin:0;padding:0;color:#000;}   
.category {position:absolute;overflow:hidden;top:10px;left:10px;width:150px;height:50px;z-index:10;border:1px solid black;font-family:'Malgun Gothic','맑은 고딕',sans-serif;font-size:12px;text-align:center;background-color:#fff;}
.category .menu_selected {background:#FF5F4A;color:#fff;border-left:1px solid #915B2F;border-right:1px solid #915B2F;margin:0 -1px;} 
.category li{list-style:none;float:left;width:50px;height:45px;padding-top:5px;cursor:pointer;} 
.category .ico_comm {display:block;margin:0 auto 2px;width:22px;height:26px;background:url('http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/category.png') no-repeat;} 
.category .ico_coffee {background-position:-10px 0;}  
.category .ico_store {background-position:-10px -36px;}   
.category .ico_carpark {background-position:-10px -72px;} 

</style>
<title>Insert title here</title>


</head>
<body>
<!-- 이미지 지도를 표시할 div 입니다 -->
<h3 style="margin-left: 200px">전국날씨</h3>
<div id="staticMap" style="width:400px;height:650px;margin-left: 200px;z-index: 0" ></div>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d6e5cf48ae116f09fd297be33a5bc535"></script>
<script>
var needData;

var lon = new Array();
var lat = new Array();
var curTemp = new Array();
var minTemp = new Array();
var maxTemp = new Array();
var icon = new Array();

	var locs = {
		gyeonggi: 1835848,
		gangwon: 1845136,
		chungcheong: 1835235,
		jeolla: 1841811,
		gyeongsang: 1838524,
		jeju: 1846266
	};
	var locsId = [locs.gyeonggi, locs.gangwon, locs.chungcheong, locs.jeolla, locs.gyeongsang, locs.jeju];
	var uriLocId = "";
	for(var i=0;i<locsId.length;i++){
		if(i == locsId.length-1){
			uriLocId+=locsId[i];
		}else{
			uriLocId+=locsId[i]+",";
		}
	}
	var apiURI = "https://api.openweathermap.org/data/2.5/group?id="+uriLocId+"&units=metric&appid=e06fdce2987442d583f1b414ff4f406a";
    $.ajax({
        url: apiURI,
        dataType: "json",
        type: "GET",
        async: false,
        success: function(resp) {
            for(var i=0; i<resp.list.length;i++){
            	lon[i]=resp.list[i].coord.lon;
            	lat[i]=resp.list[i].coord.lat;
            	curTemp[i]=resp.list[i].main.temp;
            	minTemp[i]=resp.list[i].main.temp_min;
            	maxTemp[i]=resp.list[i].main.temp_max;
            	icon[i]="http://openweathermap.org/img/wn/"+resp.list[i].weather[0].icon+".png";
            }
            needData = {
            		weatherLon : lon, // 경도
            		weatherLat : lat, // 위도
            		weatherCurTemp : curTemp, // 현재온도
            		weatherMinTemp : minTemp, // 최저온도
            		weatherMaxTemp : maxTemp, // 최고온도
            		weatherIcon : icon // 날씨 이미지
            	};
        }
    })
    
    var mapContainer  = document.getElementById('staticMap'), // 이미지 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(35.8, 127.798378), // 이미지 지도의 중심좌표
        level: 13 // 이미지 지도의 확대 레벨
    };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
    map.addOverlayMapTypeId(kakao.maps.MapTypeId.ROADVIEW);
    //changeMaptype = kakao.maps.MapTypeId.TERRAIN; 
    
    function setDraggable(draggable) {
        // 마우스 드래그로 지도 이동 가능여부를 설정합니다
        map.setDraggable(draggable);    
    }
    setDraggable(false);
    function setZoomable(zoomable) {
        // 마우스 휠로 지도 확대,축소 가능여부를 설정합니다
        map.setZoomable(zoomable);    
    }
    setZoomable(false);
    
 // 마커를 표시할 위치와 title 객체 배열입니다 
    var positions = [
        {
            title: '경기', 
            content: '<div><font style="color:blue">현재온도 : '+needData.weatherCurTemp[0]+'</font><br>최고온도 : '+needData.weatherMaxTemp[0]+'<br>최저온도 : '+needData.weatherMinTemp[0]+'</div>',
            latlng: new kakao.maps.LatLng(needData.weatherLat[0], needData.weatherLon[0])
        },
        {
            title: '강원', 
            content: '<div><font style="color:blue">현재온도 : '+needData.weatherCurTemp[1]+'</font><br>최고온도 : '+needData.weatherMaxTemp[1]+'<br>최저온도 : '+needData.weatherMinTemp[1]+'</div>',
            latlng: new kakao.maps.LatLng(needData.weatherLat[1], needData.weatherLon[1])
        },
        {
            title: '충청', 
            content: '<div><font style="color:blue">현재온도 : '+needData.weatherCurTemp[2]+'</font><br>최고온도 : '+needData.weatherMaxTemp[2]+'<br>최저온도 : '+needData.weatherMinTemp[2]+'</div>',
            latlng: new kakao.maps.LatLng(needData.weatherLat[2], needData.weatherLon[2])
        },
        {
            title: '전라',
            content: '<div><font style="color:blue">현재온도 : '+needData.weatherCurTemp[3]+'</font><br>최고온도 : '+needData.weatherMaxTemp[3]+'<br>최저온도 : '+needData.weatherMinTemp[3]+'</div>',
            latlng: new kakao.maps.LatLng(needData.weatherLat[3], needData.weatherLon[3])
        },
        {
            title: '경상',
            content: '<div><font style="color:blue">현재온도 : '+needData.weatherCurTemp[4]+'</font><br>최고온도 : '+needData.weatherMaxTemp[4]+'<br>최저온도 : '+needData.weatherMinTemp[4]+'</div>',
            latlng: new kakao.maps.LatLng(needData.weatherLat[4], needData.weatherLon[4])
        },
        {
            title: '제주',
            content: '<div><font style="color:blue">현재온도 : '+needData.weatherCurTemp[5]+'</font><br>최고온도 : '+needData.weatherMaxTemp[5]+'<br>최저온도 : '+needData.weatherMinTemp[5]+'</div>',
            latlng: new kakao.maps.LatLng(needData.weatherLat[5], needData.weatherLon[5])
        }
    ];

    // 마커 이미지의 이미지 주소입니다
    //var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
    
    var imageSrc = {
    	gyeonggi:needData.weatherIcon[0],
    	gangwon:needData.weatherIcon[1],
    	chungcheong:needData.weatherIcon[2],
    	jeolla:needData.weatherIcon[3],
    	gyeongsang:needData.weatherIcon[4],
    	jeju:needData.weatherIcon[5],
    	
    };
        
    for (var i = 0; i < positions.length; i ++) {
        
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(50, 50); 
        
        // 마커 이미지를 생성합니다    
        var markerImage = [new kakao.maps.MarkerImage(imageSrc.gyeonggi, imageSize),
        	new kakao.maps.MarkerImage(imageSrc.gangwon, imageSize),
        	new kakao.maps.MarkerImage(imageSrc.chungcheong, imageSize),
        	new kakao.maps.MarkerImage(imageSrc.jeolla, imageSize),
        	new kakao.maps.MarkerImage(imageSrc.gyeongsang, imageSize),
        	new kakao.maps.MarkerImage(imageSrc.jeju, imageSize)]; 
        
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image : markerImage[i], // 마커 이미지  
            //clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
        });
        
     // 마커에 표시할 인포윈도우를 생성합니다 
        var infowindow = new kakao.maps.InfoWindow({
            content: positions[i].content // 인포윈도우에 표시할 내용
        });

        // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
        // 이벤트 리스너로는 클로저를 만들어 등록합니다 
        // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
        kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
    }
    
 // 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
    function makeOverListener(map, marker, infowindow) {
        return function() {
            infowindow.open(map, marker);
        };
    }

 // 인포윈도우를 닫는 클로저를 만드는 함수입니다 
    function makeOutListener(infowindow) {
        return function() {
            infowindow.close();
        };
    }
 
</script>
</body>
</html>