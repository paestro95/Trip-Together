<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<link rel="stylesheet" href="${url}/css/users/userView.css" type="text/css">
<link rel="stylesheet" href="${url}/css/main/main.css" type="text/css">
<script src="${url}/js/users/userView.js"></script>
<script src="${url}/js/underBar.js"></script>
<script src="${url}/js/main/main.js"></script>


<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>
<div class="wrap">
	<div class="userView_wrap">
		<div class="user_profile_wrap">
			<div class="user_profile_img">
				<img src="${url}/Images/jennie.jpeg">
			</div>
			<div class="user_profile_info">
				<div class="id_btn_wrap">
					<span style="font-weight: bold;">Jennie</span>
					<input type="button" value="팔로우" name="follow">
				</div>
				<div class="post_follower_wrap">
					<ul>
						<li>게시물 <span style="font-weight: bold;">000</span></li>
						<li>팔로워 <span style="font-weight: bold;">000</span></li>
					</ul>
				</div>
				<div class="info_wrap">
					안녕하세요!<br>카페 정복! 개인카페 추천드려요~!<br> 여행과 예쁜 카페 먹는 걸 좋아해요 좋아요와 팔로우 부탁드려요~
				</div>
			</div>
		</div>

		<hr>

		<h3 style="padding-left: 20px; margin-top: 60px; font-size: 24px;">인기 게시물</h3>
		<div class="hot_post_wrap">
			<div class="hot_post_card_wrap">

				<ul class="place_list">
					<li><a href="#">연남동 뿌시기</a></li>
					<li>홍대 카페</li>
					<li>연남동 맛집</li>
					<li>연남동 소품샵</li>
					<li>연남동 바</li>
				</ul>
				<ul class="btn_list">
					<li><span class="material-icons like" style="cursor: pointer;">favorite_border</span>&nbsp;12</li>
					<li><span class="material-icons save" style="cursor: pointer;">bookmark_border</span>&nbsp;23</li>
					<li><span class="material-icons comment" style="cursor: pointer;">chat_bubble_outline</span>&nbsp;23</li>
				</ul>
			</div>

			<div class="hot_post_card_wrap">
				<ul class="place_list">
					<li><a href="#">남양주 드라이브</a></li>
					<li>별내 카페거리</li>
					<li>맛집</li>
					<li>드라이브 코스</li>
				</ul>
				<ul class="btn_list">
					<li><span class="material-icons like" style="cursor: pointer;">favorite_border</span>&nbsp;12</li>
					<li><span class="material-icons save" style="cursor: pointer;">bookmark_border</span>&nbsp;23</li>
					<li><span class="material-icons comment" style="cursor: pointer;">chat_bubble_outline</span>&nbsp;23</li>
				</ul>
			</div>

			<div class="hot_post_card_wrap">
				<ul class="place_list">
					<li><a href="#">파주 드라이브</a></li>
					<li>영화거리</li>
					<li>맛집</li>
					<li>드라이브 코스</li>
				</ul>
				<ul class="btn_list">
					<li><span class="material-icons like" style="cursor: pointer;">favorite_border</span>&nbsp;12</li>
					<li><span class="material-icons save" style="cursor: pointer;">bookmark_border</span>&nbsp;23</li>
					<li><span class="material-icons comment" style="cursor: pointer;">chat_bubble_outline</span>&nbsp;23</li>
				</ul>
			</div>

			<div class="hot_post_card_wrap">
				<ul class="place_list">
					<li><a href="#">가까운 인천~</a></li>
					<li>을왕리 바닷가</li>
					<li>을왕리 횟집</li>
					<li>을왕리 카페</li>
					<li>을왕리 드라이브</li>
				</ul>
				<ul class="btn_list">
					<li><span class="material-icons like" style="cursor: pointer;">favorite_border</span>&nbsp;12</li>
					<li><span class="material-icons save" style="cursor: pointer;">bookmark_border</span>&nbsp;23</li>
					<li><span class="material-icons comment" style="cursor: pointer;">chat_bubble_outline</span>&nbsp;23</li>
				</ul>
			</div>
		</div>

		<h3 style="padding-left: 20px; margin-top: 60px; font-size: 24px;">게시물</h3>
		<div class="all_post_wrap">

			<div class="post_card">
				<a href="#">
					<img src="${url}/Images/room.png">
				</a>
				<div class="post_card_hover">
					<span class="material-icons" style="vertical-align: bottom;">favorite</span> 00 &nbsp; &nbsp; <span class="material-icons" style="vertical-align: bottom;">textsms</span> 00
				</div>
			</div>

			<div class="post_card">
				<a href="#">
					<img src="${url}/Images/room.png">
				</a>
				<div class="post_card_hover">
					<span class="material-icons" style="vertical-align: bottom;">favorite</span> 000 &nbsp; &nbsp; <span class="material-icons" style="vertical-align: bottom;">textsms</span> 000
				</div>
			</div>

			<div class="post_card">
				<a href="#">
					<img src="${url}/Images/room.png">
				</a>
				<div class="post_card_hover">
					<span class="material-icons" style="vertical-align: bottom;">favorite</span> 000 &nbsp; &nbsp; <span class="material-icons" style="vertical-align: bottom;">textsms</span> 000
				</div>
			</div>

			<div class="post_card">
				<a href="#">
					<img src="${url}/Images/room.png">
				</a>
				<div class="post_card_hover">
					<span class="material-icons" style="vertical-align: bottom;">favorite</span> 000 &nbsp; &nbsp; <span class="material-icons" style="vertical-align: bottom;">textsms</span> 000
				</div>
			</div>



		</div>




	</div>
</div>