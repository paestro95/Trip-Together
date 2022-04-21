<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="${url}/css/community/communityUpdate.css" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">

<script src="${url}/js/community/communityUpdate.js"></script>
<script src="${url}/js/underBar.js"></script>
<script src="${url}/js/main/main.js"></script>
<script>
$(function(){
	var tags = $("#vo_tag_list").val();
	var tagArr = tags.split(" ");
	
	console.log(tagArr);
	
	for(var i=0; i<tagArr.length; i++){
		console.log(tagArr[i]);
		$(".hashtag_list input:checkbox[value='"+tagArr[i]+"']").attr("checked", true);
		$(".hashtag_list input:checkbox[value='"+tagArr[i]+"']").click();
	}
	
});
</script>
<!-- 모달창 띄우기 위해 insert한 부분입니다 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />

<!-- 위치검색 모달창 내용부분입니다 -->
<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>
<div id="ex1" class="modal">
	<div id="menu_wrap" class="bg_white">
		<div class="option">
			<div>
				<form onsubmit="searchPlaces(); return false;">
					<div class="search_box">
						<button type="submit">
							<span class="material-icons" style="cursor: pointer;">search</span>
						</button>
						<input type="text" value="" id="keyword" size="15">
					</div>
					<hr>
					<input type="text" value="" class="place_name" style="display: none;">
					<input type="text" value="" class="place_addr" style="display: none;">
				</form>
			</div>
		</div>
		<ul id="placesList"></ul>
	</div>
	<div id="map" style="width: 500px; height: 400px;"></div>
	<div class="map_wrap">
		<div id="map" style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
	</div>
</div>

<!-- 카카오 키워드 검색 지도api 부분입니다. -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4f8960b9ced83f38eb2bc1f28eb2f53c&libraries=services"></script>
<script>
				// 마커를 담을 배열입니다
				var markers = [];
		
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
				mapOption = {
					center : new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
					level : 3
				// 지도의 확대 레벨
				};
		
				// 지도를 생성합니다    
				var map = new kakao.maps.Map(mapContainer, mapOption);
		
				// 장소 검색 객체를 생성합니다
				var ps = new kakao.maps.services.Places();
		
				// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
				var infowindow = new kakao.maps.InfoWindow({
					zIndex : 1
				});
		
				// 키워드로 장소를 검색합니다
				searchPlaces();
		
				// 키워드 검색을 요청하는 함수입니다
				function searchPlaces() {
		
					var keyword = document.getElementById('keyword').value;
		
					if (!keyword.replace(/^\s+|\s+$/g, '')) {
						return false;
					}
		
					// 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
					ps.keywordSearch(keyword, placesSearchCB);
				}
		
				// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
				function placesSearchCB(data, status, pagination) {
					if (status === kakao.maps.services.Status.OK) {
		
						// 정상적으로 검색이 완료됐으면
						// 검색 목록과 마커를 표출합니다
						displayPlaces(data);
		
						
		
					} else if (status === kakao.maps.services.Status.ZERO_RESULT) {
		
						alert('검색 결과가 존재하지 않습니다.');
						return;
		
					} else if (status === kakao.maps.services.Status.ERROR) {
		
						alert('검색 결과 중 오류가 발생했습니다.');
						return;
		
					}
				}
		
				// 검색 결과 목록과 마커를 표출하는 함수입니다
				function displayPlaces(places) {
		
					var listEl = document.getElementById('placesList'), menuEl = document
							.getElementById('menu_wrap'), fragment = document
							.createDocumentFragment(), bounds = new kakao.maps.LatLngBounds(), listStr = '';
		
					// 검색 결과 목록에 추가된 항목들을 제거합니다
					removeAllChildNods(listEl);
		
					// 지도에 표시되고 있는 마커를 제거합니다
					removeMarker();
		
					for (var i = 0; i < places.length; i++) {
		
						// 마커를 생성하고 지도에 표시합니다
						var placePosition = new kakao.maps.LatLng(places[i].y,
								places[i].x), marker = addMarker(placePosition, i), itemEl = getListItem(
								i, places[i]); // 검색 결과 항목 Element를 생성합니다
		
						// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
						// LatLngBounds 객체에 좌표를 추가합니다
						bounds.extend(placePosition);
		
						// 마커와 검색결과 항목에 mouseover 했을때
						// 해당 장소에 인포윈도우에 장소명을 표시합니다
						// mouseout 했을 때는 인포윈도우를 닫습니다
						(function(marker, title) {
							kakao.maps.event.addListener(marker, 'mouseover',
									function() {
										displayInfowindow(marker, title);
									});
		
							kakao.maps.event.addListener(marker, 'mouseout',
									function() {
										infowindow.close();
									});
		
							itemEl.onmouseover = function() {
								displayInfowindow(marker, title);
							};
		
							itemEl.onmouseout = function() {
								infowindow.close();
							};
						})(marker, places[i].place_name);
		
						fragment.appendChild(itemEl);
					}
		
					// 검색결과 항목들을 검색결과 목록 Element에 추가합니다
					listEl.appendChild(fragment);
					menuEl.scrollTop = 0;
		
					// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
					map.setBounds(bounds);
				}
		
				// 검색결과 항목을 Element로 반환하는 함수입니다
				function getListItem(index, places) {
		
					var el = document.createElement('li'), itemStr = '<span class="markerbg marker_'
							+ (index + 1)
							+ '"></span>'
							+ '<div class="info">'
							+ '   <h5><a href="#" rel="modal:close" style="color: black;">' + places.place_name + '</a></h5>';
		
					if (places.road_address_name) {
						itemStr += '    <span>' + places.road_address_name + '</span>'
								+ '   <span class="jibun gray">' + places.address_name
								+ '</span>';
					} else {
						itemStr += '    <span>' + places.address_name + '</span>';
					}
		
					itemStr += '  <span class="tel">' + places.phone + '</span>'
							+ '</div>';
		
					el.innerHTML = itemStr;
					el.className = 'item';
		
					return el;
				}
		
				// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
				function addMarker(position, idx, title) {
					var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
					imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
					imgOptions = {
						spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
						spriteOrigin : new kakao.maps.Point(0, (idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
						offset : new kakao.maps.Point(13, 37)
					// 마커 좌표에 일치시킬 이미지 내에서의 좌표
					}, markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize,
							imgOptions), marker = new kakao.maps.Marker({
						position : position, // 마커의 위치
						image : markerImage
					});
		
					marker.setMap(map); // 지도 위에 마커를 표출합니다
					markers.push(marker); // 배열에 생성된 마커를 추가합니다
		
					return marker;
				}
		
				// 지도 위에 표시되고 있는 마커를 모두 제거합니다
				function removeMarker() {
					for (var i = 0; i < markers.length; i++) {
						markers[i].setMap(null);
					}
					markers = [];
				}
		
				
		
				// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
				// 인포윈도우에 장소명을 표시합니다
				function displayInfowindow(marker, title) {
					var content = '<div style="padding:5px;z-index:1;">' + title
							+ '</div>';
		
					infowindow.setContent(content);
					infowindow.open(map, marker);
				}
		
				// 검색결과 목록의 자식 Element를 제거하는 함수입니다
				function removeAllChildNods(el) {
					while (el.hasChildNodes()) {
						el.removeChild(el.lastChild);
					}
				}
			</script>

<div class="wrap">
<input type="hidden" value="${vo.tags}" id="vo_tag_list">

	<div class="communityUpdate_wrap">
		<form method="post" action="${url}/community/communityUpdateOk" onsubmit="return boardCheck()" enctype="multipart/form-data">
			<input type="hidden" name="board_no" value="${vo.board_no}">
			<div class="write_menu_wrap">
				<div class="write_select_wrap">
					<select name="region" id="region">
						<option value="지역">지역</option>
						<option value="서울" <c:if test="${vo.region =='서울'}">selected</c:if>>서울</option>
						<option value="경기" <c:if test="${vo.region =='경기'}">selected</c:if>>경기</option>
						<option value="인천" <c:if test="${vo.region =='인천'}">selected</c:if>>인천</option>
						<option value="강원" <c:if test="${vo.region =='강원'}">selected</c:if>>강원</option>
						<option value="충청" <c:if test="${vo.region =='충청'}">selected</c:if>>충청</option>
						<option value="전라" <c:if test="${vo.region =='전라'}">selected</c:if>>전라</option>
						<option value="경상" <c:if test="${vo.region =='경상'}">selected</c:if>>경상</option>
						<option value="제주" <c:if test="${vo.region =='제주'}">selected</c:if>>제주</option>
					</select>
					<select name="theme" id="theme">
						<option value="분류">분류</option>
						<option value="식당" <c:if test="${vo.theme =='식당'}">selected</c:if>>식당</option>
						<option value="카페" <c:if test="${vo.theme =='카페'}">selected</c:if>>카페</option>
						<option value="액티비티" <c:if test="${vo.theme =='액티비티'}">selected</c:if>>액티비티</option>
						<option value="숙박" <c:if test="${vo.theme =='숙박'}">selected</c:if>>숙박</option>
					</select>
				</div>
				<div class="write_submit_wrap">
					<input type="submit" value="수정" id="submit">
				</div>
			</div>

			<input type="text" name="title" id="title" placeholder="Title" value="${vo.title}">
			
			<ul class="hashtag_list">
				<li><input type="checkbox" name="tag" value="혼자여행">#혼자여행</li>
				<li><input type="checkbox" name="tag" value="포토존">#포토존</li>
				<li><input type="checkbox" name="tag" value="힐링">#힐링</li>
				<li><input type="checkbox" name="tag" value="가족여행">#가족여행</li>
				<li><input type="checkbox" name="tag" value="데이트">#데이트</li>
				<li><input type="checkbox" name="tag" value="뷰맛집">#뷰맛집</li>
				<li><input type="checkbox" name="tag" value="힙한">#힙한</li>
				<li><input type="checkbox" name="tag" value="애견동반">#애견동반</li>
				<li><input type="checkbox" name="tag" value="이국적">#이국적</li>
				<li><input type="checkbox" name="tag" value="레트로">#레트로</li>
				<li><input type="checkbox" name="tag" value="감성">#감성</li>
			</ul>
			
			<input type="text" name="tags" id="tags" value="" style="display: none;">
			
			<!-- 메인 부분 -->
			<div class="post_main_block_entire_wrap">
				<div class="post_main_block_wrap">
					<div class="img_upload_wrap">
						<div class="img_upload">
							<img src="${vo.photo1 }" class="preview"> <span class="upload_phr" style="display: none;"><span class="material-icons" style="vertical-align: middle;">file_upload</span>Upload a picture</span>
						</div>
						<input type="file" class="imgSelector" name="photo" style="display: none;">
					</div>
					<div class="place_content_wrap">
						<div class="location_wrap">
							<div class="locationBtn_delBtn_wrap" style="display: flex;">
								<div style="flex: 1;">
									<a href="#ex1" rel="modal:open">
										<button class="location locationBtn">
											<span class="material-icons" style="font-size: 14px; vertical-align: middle;">place</span>장소 찾기
										</button>
									</a>
								</div>
								<div class="delBtn" style="text-align: right;">
									<span class="material-icons del" style="vertical-align: middle; color: gray; cursor: pointer;">delete</span>
								</div>
							</div>
							<input type="text" class=" location location1" value="${vo.location1}" name="location" id="location1">
							<input type="text" class="location location2" value="${vo.location_addr1 }" name="location_addr" id="location_addr1">
						</div>
						<hr>
						<textarea name="content" id="content1" placeholder="사진에 대해 설명해주세요.">${vo.content1}</textarea>
					</div>
				</div>
				
				<c:if test="${!empty vo.content2}">
					<div class="post_main_block_wrap">
						<div class="img_upload_wrap">
							<div class="img_upload">
								<img src="${vo.photo2 }" class="preview"> <span class="upload_phr" style="display: none;"><span class="material-icons" style="vertical-align: middle;">file_upload</span>Upload a picture</span>
							</div>
							<input type="file" class="imgSelector" name="photo" style="display: none;">
							
						</div>
						<div class="place_content_wrap">
							<div class="location_wrap">
								<div class="locationBtn_delBtn_wrap" style="display: flex;">
									<div style="flex: 1;">
										<a href="#ex1" rel="modal:open">
											<button class="location locationBtn">
												<span class="material-icons" style="font-size: 14px; vertical-align: middle;">place</span>장소 찾기
											</button>
										</a>
									</div>
									<div class="delBtn" style="text-align: right;">
										<span class="material-icons del" style="vertical-align: middle; color: gray; cursor: pointer;">delete</span>
									</div>
								</div>
								<input type="text" class=" location location1" value="${vo.location2}" name="location" id="location2">
								<input type="text" class="location location2" value="${vo.location_addr2 }" name="location_addr" id="location_addr2">
							</div>
							<hr>
							<textarea name="content" id="content2" placeholder="사진에 대해 설명해주세요.">${vo.content2}</textarea>
						</div>
					</div>
				</c:if>
				
				<c:if test="${!empty vo.content3}">
					<div class="post_main_block_wrap">
						<div class="img_upload_wrap">
							<div class="img_upload">
								<img src="${vo.photo3 }" class="preview"> <span class="upload_phr" style="display: none;"><span class="material-icons" style="vertical-align: middle;">file_upload</span>Upload a picture</span>
							</div>
							<input type="file" class="imgSelector" name="photo" style="display: none;">
							
						</div>
						<div class="place_content_wrap">
							<div class="location_wrap">
								<div class="locationBtn_delBtn_wrap" style="display: flex;">
									<div style="flex: 1;">
										<a href="#ex1" rel="modal:open">
											<button class="location locationBtn">
												<span class="material-icons" style="font-size: 14px; vertical-align: middle;">place</span>장소 찾기
											</button>
										</a>
									</div>
									<div class="delBtn" style="text-align: right;">
										<span class="material-icons del" style="vertical-align: middle; color: gray; cursor: pointer;">delete</span>
									</div>
								</div>
								<input type="text" class=" location location1" value="${vo.location3}" name="location" id="location3">
								<input type="text" class="location location2" value="${vo.location_addr3 }" name="location_addr" id="location_addr3">
							</div>
							<hr>
							<textarea name="content" id="content3" placeholder="사진에 대해 설명해주세요.">${vo.content3}</textarea>
						</div>
					</div>
				</c:if>
				
				<c:if test="${!empty vo.content4}">
					<div class="post_main_block_wrap">
						<div class="img_upload_wrap">
							<div class="img_upload">
								<img src="${vo.photo4 }" class="preview"> <span class="upload_phr" style="display: none;"><span class="material-icons" style="vertical-align: middle;">file_upload</span>Upload a picture</span>
							</div>
							<input type="file" class="imgSelector" name="photo" style="display: none;">
							
						</div>
						<div class="place_content_wrap">
							<div class="location_wrap">
								<div class="locationBtn_delBtn_wrap" style="display: flex;">
									<div style="flex: 1;">
										<a href="#ex1" rel="modal:open">
											<button class="location locationBtn">
												<span class="material-icons" style="font-size: 14px; vertical-align: middle;">place</span>장소 찾기
											</button>
										</a>
									</div>
									<div class="delBtn" style="text-align: right;">
										<span class="material-icons del" style="vertical-align: middle; color: gray; cursor: pointer;">delete</span>
									</div>
								</div>
								<input type="text" class=" location location1" value="${vo.location4}" name="location" id="location4">
								<input type="text" class="location location2" value="${vo.location_addr4}" name="location_addr" id="location_addr4">
							</div>
							<hr>
							<textarea name="content" id="content4" placeholder="사진에 대해 설명해주세요.">${vo.content4}</textarea>
						</div>
					</div>
				</c:if>
			</div>
			
			
		<input type="button" value="+추가" id="addBtn">
			
		</form>
	</div>
<script src="${url}/js/underBar.js"></script>
</div>