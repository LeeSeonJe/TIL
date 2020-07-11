```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=카카오 디벨로퍼 자신의 스크립트 key값&libraries=services"></script>
<!-- 카카오맵 API를 사용하기 위한 카카오 디벨로퍼 key와 라이브러리 -->

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- jquery CDN방식을 통해 연결  -->

<!--
//-->
</script>
</head>
<body>
<form action="#" onsubmit="maskFind();">
	<select name="" id="juso1">
		<option value="서울특별시">서울특별시</option>
		<option value="부산광역시">부산광역시</option>
		<option value="대구광역시">대구광역시</option>
		<option value="인천광역시">인천광역시</option>
		<option value="광주광역시">	광주광역시</option>
		<option value="대전광역시">대전광역시</option>
		<option value="울산광역시">울산광역시</option>
		<option value="경기도">경기도</option>
		<option value="강원도">강원도</option>
		<option value="충청북도">충청북도</option>
		<option value="충청남도">충청남도</option>
		<option value="전라북도">전라북도</option>
		<option value="전라남도">전라남도</option>
		<option value="경상북도">경상북도</option>
		<option value="경상남도">경상남도</option>
		<option value="제주특별자치도">제주특별자치도</option>
	</select>
	<input type="text" id="juso2" value="광진구" required="required"/>
	<input type="text" id="juso3"/>
	<button type="submit">검색</button>
</form>
	<div id="map" style="width: 1600px; height: 800px;"></div>
	<script>
		function maskFind() {
			/* 각각 input에서 받은 주소를 합치는 부분 */
			var juso = $('#juso1').val() + ' ' +  $('#juso2').val() + ' ' + $('#juso3').val();
			
			/* 공적마스크 API를 사용하기 위한 ajax */
			$.ajax({
				type : "GET",
				url : "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByAddr/json?address=" + juso,
						/* 공적마스크에 접근하기 위한 API주소와 API에서 제공하는  address 파라미터 값으로 input을 통해 받아온 주소값을 보냄 */
				header : {
					"Content-Type" : "application/json"
					/* ajax 통신시 header 값에 json형식 추가하기 */
				},
				dataType : "json",
				/* ajax를 통해 받아오는 데이터 형식은 json 형식 */
				success : function(data) {
					console.log(data);
					/* data를 들고올시 카카오맵을 그려줌 */
					$('#map').html('');					
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
					mapOption = {
						center: new kakao.maps.LatLng(data.stores[0].lat, data.stores[0].lng), // 지도의 중심좌표
						level : 3
					// 지도의 확대 레벨
					};
					var geocoder = new kakao.maps.services.Geocoder();
					geocoder.addressSearch(juso, function(result, status) {

					    // 정상적으로 검색이 완료됐으면 
					     if (status === kakao.maps.services.Status.OK) {

					        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

					        // 결과값으로 받은 위치를 마커로 표시합니다
					        var marker = new kakao.maps.Marker({
					            map: map,
					            position: coords
					        });

					        // 인포윈도우로 장소에 대한 설명을 표시합니다
					        var infowindow = new kakao.maps.InfoWindow({
					        	// 검색한 주소 위치 마커
					            content: '<div style="width:150px;text-align:center;padding:6px 0; font-size: 12px;">'+ juso +'</div>'
					        });
					        infowindow.open(map, marker);

					        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
					        map.setCenter(coords);
					    } 
					});  
					var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

					// 마커를 표시할 위치와 title 객체 배열입니다 
					var positions = [];
					for(var i in data.stores) {
						positions.push({ 
							title : data.stores[i].name, 
							latlng : new kakao.maps.LatLng(data.stores[i].lat, data.stores[i].lng),
							remain_stat : data.stores[i].remain_stat
						});
					}
					// 마커 이미지의 이미지 주소입니다
					var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

					for (var i = 0; i < positions.length; i++) {

						// 마커 이미지의 이미지 크기 입니다
						var imageSize = new kakao.maps.Size(24, 35);

						// 마커 이미지를 생성합니다    
						var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

						// 마커를 생성합니다
						var marker = new kakao.maps.Marker({
							map : map, // 마커를 표시할 지도
							position : positions[i].latlng, // 마커를 표시할 위치
							title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
							image : markerImage
						// 마커 이미지 
						});
				        // 인포윈도우로 장소에 대한 설명을 표시합니다
				        // 공적마스크의 개수 표시하기
				        if(positions[i].remain_stat == 'plenty') {
					        var infowindow = new kakao.maps.InfoWindow({			        	
					            content: '<div class="location" style="width:150px; text-align:center; padding:6px 0; font-size:10px;">' + positions[i].title + '<br><span style="background: green; color:white;">충분 100 +</span></div>'
					        });
			        	} else if(positions[i].remain_stat == 'some') {
					        var infowindow = new kakao.maps.InfoWindow({			        	
					            content: '<div class="location" style="width:150px; text-align:center; padding:6px 0; font-size:10px;">' + positions[i].title + '<br><span style="background: #ffae02; color:white;">보통 30 ~ 99</span></div>'
					        });
			        	} else if(positions[i].remain_stat == 'few') {
					        var infowindow = new kakao.maps.InfoWindow({			        	
					            content: '<div class="location" style="width:150px; text-align:center; padding:6px 0; font-size:10px;">' + positions[i].title + '<br><span style="background: red; color:white;">부족 2 ~ 29</span></div>'
					        });
			        	} else if(positions[i].remain_stat == 'empty') {
					        var infowindow = new kakao.maps.InfoWindow({			        	
					            content: '<div class="location" style="width:150px; text-align:center; padding:6px 0; font-size:10px;">' + positions[i].title + '<br><span style="background: gray; color:white;"> 없음 </span></div>'
					        });
			        	} else if(positions[i].remain_stat == 'break' || positions[i].remain_stat == null) {
					        var infowindow = new kakao.maps.InfoWindow({			        	
					            content: '<div class="location" style="width:150px; text-align:center; padding:6px 0; font-size:10px;">' + positions[i].title + '<br><span style="background: black; color:white;"> 판매중지 </span></div>'
					        });
			        	} 
				        infowindow.open(map, marker);
					}
				}
			})
		}
	</script>

</body>
</html>

```
