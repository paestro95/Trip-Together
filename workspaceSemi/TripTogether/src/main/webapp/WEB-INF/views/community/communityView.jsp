<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${url}/css/community/communityView.css" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${url}/js/community/communityView.js"></script>
<script src="${url}/js/underBar.js"></script>
<script src="${url}/js/main/main.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<title>CommunityView</title>
</head>

<!-- main -->
<main>

<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>
	<div class="feeds">
		<!-- article -->
		<article>
			<header>
				<div class="title">${vo.title}제목입니다!</div>
			</header>
			<div class="main-image">
				<!-- 게시물 대표사진을 불러와용 -->
				<img src="http=5F6B3319" alt="대표사진" class="mainPic">
			</div>
			<div class="content">
				<!-- 유저의 게시물을 불러옴 -->
				${vo.content}
			</div>
			<div class="hl"></div>
			<!-- 댓글달기 -->
			<div class="comment">
				<input id="input-comment" class="input-comment" type="text" placeholder="댓글 달기...">
				<button type="submit" class="submit-comment" disabled>게시</button>
			</div>
		</article>
	</div>

	<!-- main-right -->
	<div class="main-right">
		<div class="myProfile">
			<img class="pic" src="../img/jenny.jpg" alt="프로필 사진">
			<div>
				<span class="id">PowerJ</span> <span class="info">안녕하십니까1!!~~!!</span>
			</div>
		</div>
		<div class="midbtn">

			<%-- 	<!-- 현재 로그인user의 id -->
			<c:choose>
				<c:when test="${page_id == currentid}">
					<div class="fix">
						<a href="update/${user.id}" class="btn btn-default">프로필 수정</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="fix">
						<c:choose>
							<c:when test="${follow == true}">
								<!-- 0이 아니므로 팔로우 되어있음 -->
								<form action="/unfollow" name="form" method="post">
									<!-- 언팔로우 버튼 -->
									<input type="hidden" value="${currentid}" name="user_id">
									<!-- 현재로그인id -->
									<input type="hidden" value="${page_id}" name="page_id">
									<!-- 현재페이지id -->
									<button class="btn btn-default" type="submit">언팔로잉</button>
								</form>
							</c:when>
							<c:otherwise>
								<form action="/follow" name="form" method="post">
									<input type="hidden" value="${currentid}" name="user_id">
									<input type="hidden" value="${page_id}" name="page_id">
									<button class="btn btn-default">팔로잉</button>
								</form>
							</c:otherwise>
						</c:choose>
					</div>
				</c:otherwise>
			</c:choose>
			<ul> --%>

			<button class="followbtn">팔로우</button>
			<li><span class="material-icons">favorite_border</span></li>
			<!-- <span class="material-icons">favorite</span> -->
			<li><span class="material-icons">turned_in_not</span></li>
			<!-- <span class="material-icons">bookmark_added</span> -->
			</ul>
		</div>
		<!-- 게시물더보기-->
		<div class="section-story">
			<div class="menu-title">
				<span class="sub-title">게시물</span> <span class="find-more">모두 보기</span>
			</div>
			<ul class="story-list">
				<li>
					<div class="content-Img">
						<img src="../img/jiii.jpg" alt="게시물 사진">
					</div>
				</li>
			</ul>
		</div>

		<div class="bottombtn">
			<ul>
				<li><a href="/community/communityUpdate?no${vo.no}">수정</a></li>
				<li><a href="javascript:del()">
						<input type="submit" value="삭제" />
					</a></li>
			</ul>
		</div>
	</div>
</main>