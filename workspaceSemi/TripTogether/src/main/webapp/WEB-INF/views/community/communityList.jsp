<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="${url}/css/community/communityList.css" type="text/css" />
<script src="${url}/js/community/communityList.js"></script>
<script src="${url}/js/underBar.js"></script>
<script>
//좋아요 기능
function likesClick(obj, no){
	
	var likesText = $(obj).children().first().text().trim();

	if(likesText == 'favorite_border'){
		$.ajax({
		type: 'post',
		url: '/likes/' + no,
			success: function(r){
				$(obj).children().text('favorite');
				$(obj).children().next().text(r);
				console.log("좋아요 추가 성공!");
			},
			error: function(e){
				console.log("좋아요 추가 에러!!");
			}
		})
	}else if(likesText == 'favorite'){
		$.ajax({
			type: 'post',
			url: '/unlikes/' + no,
			success: function(r){
				$(obj).children().text('favorite_border');
				$(obj).children().next().text(r);
				console.log("좋아요 취소 성공!");
			},
			error: function(e){
				console.log("좋아요 취소 에러!!");
			}
		});
	}
}

$(".save").click(function() {
    var bno = $(this).attr('data-no')

    if ($(this).text() == "bookmark_border") {
        $(this).text("bookmark");
        $(this).addClass("bookmark");
        $(this).removeClass("bookmark_border");
        wish($(this), bno)
    } else {
        $(this).text("bookmark_border");            
        $(this).removeClass("bookmark");
        $(this).addClass("bookmark_border");
        wish($(this), bno)
    }
});

function wish(obj, wid) {

    var btText = $(obj).text().trim();
    //alert(btText+"/"+btText.length)
    if (btText == 'bookmark_border') {
        $.ajax({
            type : 'post',
            url : '/wish',
            data : {
                board_no : wid
            },
            success : function(r) {
                $(obj).text('bookmark');
                $(obj).next().text(r);
            },
            error : function(e) {
                console.log(e);
            }
        });
    } else if (btText == 'bookmark') {
        $.ajax({
            type : 'post',
            url : '/unwish',
            data : {
                board_no : wid
            },
            success : function(r) {
                $(obj).text('bookmark_border');
                $(obj).next().text(r);
                console.log(r);
            },
            error : function(e) {
                console.log(e);
            }
        });
    }
}
</script>

<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>

<div class="wrap">
	<div class="communityList_wrap">
		<h1>여기 같이 가볼까?</h1>

		<div class="notice_wrap">
			<a href="${url}/notice/noticeList">
				<span class="material-icons" style="color: gray;">campaign</span>
			</a>
			<marquee direction="left" style="width: 1165px; height: 24px; line-height: 29px; font-size: 14px;">
				<a href="${url}/notice/noticeList" style="color: gray;">${nVO.title}</a>
			</marquee>
		</div>

		<div class="sort_wrap">
			<ul class="category_list region">
				<li style="pointer-events: none;">지역</li>
				<li style="cursor: pointer;">서울</li>
				<li style="cursor: pointer;">경기</li>
				<li style="cursor: pointer;">인천</li>
				<li style="cursor: pointer;">강원</li>
				<li style="cursor: pointer;">충청</li>
				<li style="cursor: pointer;">전라</li>
				<li style="cursor: pointer;">경상</li>
				<li style="cursor: pointer;">제주</li>
				<li style="pointer-events: none;">&nbsp;</li>
				<li style="pointer-events: none;">&nbsp;</li>
				<li style="pointer-events: none;">&nbsp;</li>
			</ul>

			<ul class="category_list theme">
				<li style="pointer-events: none;">분류</li>
				<li style="cursor: pointer;">식당</li>
				<li style="cursor: pointer;">카페</li>
				<li style="cursor: pointer;">액티비티</li>
				<li style="cursor: pointer;">숙박</li>
				<li style="pointer-events: none;">&nbsp;</li>
				<li style="pointer-events: none;">&nbsp;</li>
				<li style="pointer-events: none;">&nbsp;</li>
				<li style="pointer-events: none;">&nbsp;</li>
				<li style="pointer-events: none;">&nbsp;</li>
				<li style="pointer-events: none;">&nbsp;</li>
				<li style="pointer-events: none;">&nbsp;</li>
			</ul>

			<ul class="category_list hashtag">
				<li style="pointer-events: none;">테마</li>
				<li style="cursor: pointer;">#혼자여행</li>
				<li style="cursor: pointer;">#포토존</li>
				<li style="cursor: pointer;">#힐링</li>
				<li style="cursor: pointer;">#가족여행</li>
				<li style="cursor: pointer;">#데이트</li>
				<li style="cursor: pointer;">#뷰맛집</li>
				<li style="cursor: pointer;">#힙한</li>
				<li style="cursor: pointer;">#애견동반</li>
				<li style="cursor: pointer;">#이국적</li>
				<li style="cursor: pointer;">#레트로</li>
				<li style="cursor: pointer;">#감성</li>
			</ul>

			<button class="select_reset" style="float: left; padding-top: 7px;">
				<span class="material-icons" style="font-size: 20px; color: gray; cursor: pointer;">restart_alt</span>
			</button>
			<div class="select_cate_wrap"></div>
		</div>



		<div class="container_card_wrap">
			<div class="hot_new_writeBtn_wrap">
				<div class="hot_new_wrap">
					<span id="hotBtn" style="font-weight: bold">인기</span> | <span id="newBtn">신규</span>
				</div>
				<div class="writeBtn_wrap">
					<c:if test="${logStatus == 'Y'}">
						<a href="${url}/community/communityWrite">
							<input type="button" value="글쓰기" />
						</a>
					</c:if>
				</div>
			</div>

			<c:forEach var="vo" items="${list}">
				<div class="${vo.theme} card_wrap ${vo.region} hot ${vo.tags}">
					<div class="card_writer_wrap">
						<div class="user_img_wrap">
							<a href="${url}/users/userView?id=${vo.id}" class="user_img">
								<img src="${vo.user_img}" />
							</a>
						</div>
						<a href="${url}/users/userView?id=${vo.id}" class="id">${vo.id}</a>
						<p>${vo.info}</p>
					</div>
					<div class="card_content_wrap">
						<div class="card_main_img_wrap">
							<a href="${url}/community/communityView?no=${vo.board_no}&id=${vo.id}">
								<img src="${vo.photo1}" class="card_img" />
							</a>
						</div>
						<aside class="side_menu">
							<c:choose>
								<%-- 로그인 상태일 때, --%>
								<c:when test="${logStatus == 'Y' }">
									<button class="likesBtn" onclick="likesClick(this, '${vo.board_no}');">
										<span class="material-icons like" style="cursor: pointer; vertical-align: middle;"> <c:if test="${vo.likesid !=logId}">favorite_border</c:if> <c:if test="${vo.likesid == logId}">favorite</c:if>
										</span> &nbsp; <span class="likesCnt">${vo.likes}</span>
									</button>
									<button>
										<%--  onclick="wish(this,'${vo.id}')" --%>
										<span class="material-icons save" data-no="${vo.board_no}" style="cursor: pointer; vertical-align: middle;" onclick="wish(this,'${vo.board_no}')"> <c:if test="${vo.wishid == logId}">bookmark</c:if> <c:if test="${vo.wishid != logId}">bookmark_border</c:if>
										</span> <span class="wishCnt">${vo.wish}</span>
									</button>
								</c:when>
								<%-- 로그인 상태가 아닐 때, alert + 로그인 div가 오른쪽에 나타남 --%>
								<c:otherwise>
									<button>
										<span class="material-icons" style="cursor: pointer; vertical-align: middle;" onclick="btn_notlogin();">favorite_border</span> ${vo.likes}
									</button>
									<button>
										<span class="material-icons" style="cursor: pointer; vertical-align: middle;" onclick="btn_notlogin();">bookmark_border</span> ${vo.wish}
									</button>
								</c:otherwise>
							</c:choose>
							<%-- 댓글 아이콘 클릭 시 해당 게시물 view 페이지로 이동 --%>
							<button>
								<span class="material-icons comment" style="cursor: pointer; vertical-align: middle;" onclick="location.href='${url}/community/communityView?no=${vo.board_no}&id=${vo.id}' ">chat_bubble_outline</span> ${vo.comment }
							</button>
						</aside>
						<div class="content_prev">
							<a href="${url}/community/communityView?no=${vo.board_no}" style="color: #5E5E5E;">${vo.content1}</a>
						</div>
					</div>
				</div>
			</c:forEach>

			<c:forEach var="vo" items="${newList}">
				<div class="${vo.theme} card_wrap ${vo.region} new ${vo.tags}" style="display: none">
					<div class="card_writer_wrap">
						<div class="user_img_wrap">
							<a href="${url}/users/userView?id=${vo.id}" class="user_img">
								<img src="${vo.user_img}" />
							</a>
						</div>
						<a href="${url}/users/userView?id=${vo.id}" class="id">${vo.id}</a>
						<p>${vo.info}</p>
					</div>
					<div class="card_content_wrap">
						<div class="card_main_img_wrap">
							<a href="${url}/community/communityView?no=${vo.board_no}&id=${vo.id}">
								<img src="${vo.photo1}" class="card_img" />
							</a>
						</div>
						<aside class="side_menu">
							<c:choose>
								<%-- 로그인 상태일 때, --%>
								<c:when test="${logStatus == 'Y' }">
									<button class="likesBtn" onclick="likesClick(this, '${vo.board_no}');">
										<span class="material-icons like" style="cursor: pointer; vertical-align: middle;"> <c:if test="${vo.likesid !=logId}">favorite_border</c:if> <c:if test="${vo.likesid == logId}">favorite</c:if>
										</span> &nbsp; <span class="likesCnt">${vo.likes}</span>
									</button>
									<button>
										<%--  onclick="wish(this,'${vo.id}')" --%>
										<span class="material-icons save" data-no="${vo.board_no}" style="cursor: pointer; vertical-align: middle;" onclick="wish(this,'${vo.board_no}')"> <c:if test="${vo.wishid == logId}">bookmark</c:if> <c:if test="${vo.wishid != logId}">bookmark_border</c:if>
										</span> <span class="wishCnt">${vo.wish}</span>
									</button>
								</c:when>
								<%-- 로그인 상태가 아닐 때, alert + 로그인 div가 오른쪽에 나타남 --%>
								<c:otherwise>
									<button>
										<span class="material-icons" style="cursor: pointer; vertical-align: middle;" onclick="btn_notlogin();">favorite_border</span> ${vo.likes}
									</button>
									<button>
										<span class="material-icons" style="cursor: pointer; vertical-align: middle;" onclick="btn_notlogin();">bookmark_border</span> ${vo.wish}
									</button>
								</c:otherwise>
							</c:choose>
							<%-- 댓글 아이콘 클릭 시 해당 게시물 view 페이지로 이동 --%>
							<button>
								<span class="material-icons comment" style="cursor: pointer; vertical-align: middle;" onclick="location.href='${url}/community/communityView?no=${vo.board_no}&id=${vo.id}' ">chat_bubble_outline</span> ${vo.comment}
							</button>
						</aside>
						<div class="content_prev">
							<a href="${url}/community/communityView?no=${vo.board_no}" style="color: #5E5E5E;">${vo.content1}</a>
						</div>
					</div>
				</div>
			</c:forEach>
			<a id="MOVE_TOP_BTN" href="#">
				<img src="${url}/Images/back-to-top.png" style="width: 30px;">
			</a>

		</div>
	</div>
</div>
