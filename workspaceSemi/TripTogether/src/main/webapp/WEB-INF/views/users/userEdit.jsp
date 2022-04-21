<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${url}/css/users/userEdit.css" type="text/css">
<link rel="stylesheet" href="${url}/css/main/main.css" type="text/css" />
<link rel="stylesheet" href="${url}css/bottom.css" type="text/css" />

<script src="${url}/js/underBar.js"></script>
<script src="${url}/js/main/main.js"></script>
<script src="/js/users/userEdit.js"></script>

<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>

<div class="wrap">
	<div class="userEdit_wrap">
	<h2 style="text-align: center; margin: 50px 0px 40px; font-size: 35px;">회원정보 수정</h2>
		<div>
			<form method="post" id="mFrm" action="/users/userEditOk" onsubmit='return editCheck()' enctype="multipart/form-data">
				<div id="join_img_wrap">
					<!-- img src 변경 필요 = DB에 이미지경로 값 추가 -> 해당 값으로 대체 -->
					<span id="img_upload_phr" style='display: none'>Click here!</span> <img src="${logImg }" id="join_img" />
				</div>
				<input type="file" name="profileImg" id="profileImg" style="display: none;" value="${logImg}" />
				<input type="hidden" name="kakaoImg" id="kakaoImg" value="${kakao.properties.profile_image}"/>
				
				<fieldset>
					<legend>계정 정보</legend>
					<ul>
						<li>아이디</li>
						<li>${vo.id}</li>
						<li>비밀번호</li>
						<li style="font-size: 15px; color: gray; margin-top: 5px;">[비밀번호는 변경이 불가능합니다.]</li>
					</ul>
				</fieldset>

				<fieldset>
					<legend>개인 정보</legend>
					<ul>
						<li>소개</li>
						<li><textarea name="info" id="info" rows="7">${vo.info }</textarea></li>
						<li>이름</li>
						<li><input type="text" name="name" id="name" value="${vo.name }" /></li>
						<li>성별</li>
						<li>
							<ul id="radio_list">
								<li><input type="radio" name="gender" id="gender_man" value="1"
									<c:if test="${vo.gender == 1}"> checked </c:if>
									 />남자</li>
								<li><input type="radio" name="gender" id="gender_woman" value="2"
									<c:if test="${vo.gender == 2}"> checked </c:if>
								 />여자</li>
							</ul>
						</li>
						<li>이메일</li>
						<li><input type="text" name="email" id="email" value="${vo.email }" /></li>
						<li>연락처</li>
						<li><input type="text" name="tel" id="tel" value="${vo.tel }" /> <span id='telChk'></span></li>
						
						<li>연령대</li>
						<li>
							<ul id="radio_list">
								<li><input type="radio" name="age" id="age10" value="1"
									<c:if test="${vo.age == 1}"> checked </c:if>
								 	/>10대</li>
								<li><input type="radio" name="age" id="age20" value="2" 
									<c:if test="${vo.age == 2}"> checked </c:if>
									/>20대</li>
								<li><input type="radio" name="age" id="age30" value="3" 
									<c:if test="${vo.age == 3}"> checked </c:if>
									/>30대</li>
								<li><input type="radio" name="age" id="age40" value="4" 
									<c:if test="${vo.age == 4}"> checked </c:if>
									/>40대</li>
								<li><input type="radio" name="age" id="age50" value="5" 
									<c:if test="${vo.age == 5}"> checked </c:if>
									/>50대 이상</li>
							</ul>
						</li>
					</ul>
				</fieldset>

				<div class="submit_button_wrap">
					<input type="submit" value="수 정 하 기" style="margin-left: 3px; margin-right: 5px;" />
					<input type="button" value="탈 퇴 하 기" style="margin-left: 5px; margin-right: 3px;" />
				</div>

			</form>
		</div>
	</div>
</div>