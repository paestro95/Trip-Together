<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${url}/css/users/joinForm.css" type="text/css">
<link rel="stylesheet" href="${url}/css/main/main.css" type="text/css" />
<link rel="stylesheet" href="${url}/css/bottom.css" type="text/css" />
<script src="${url}/js/users/joinForm.js"></script>


<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>


<div class="wrap">

	<div class="joinForm_wrap">
		<h2 style="text-align: center; margin: 50px 0px 40px; font-size: 35px;">회원가입</h2>
		<form method="post" action="/users/joinOk" onsubmit='return memberCheck()' enctype="multipart/form-data">
         <c:if test="${kakao==null}">
         
         <div id="join_img_wrap">
            <span id="img_upload_phr">Click here!</span> <img src=""   id="join_img" />
         </div>
         </c:if>
         <c:if test="${kakao!=null}">
         
         <div id="join_img_wrap">
            <span id="img_upload_phr2"></span> <img src="${kakao.properties.profile_image}"   id="join_img" />
         </div>
         </c:if>
         
         <input type="file" name="profileImg" id="profileImg" style="display: none;" />
         <input type="hidden" name="kakaoImg" id="kakaoImg" value="${kakao.properties.profile_image}"/>
    
         <p><center>카카오 로그인으로 가입시 현재 등록된 카카오 프로필 이미지로 자동선택 됩니다.</center></p>
			
			<fieldset>
            <legend> 로그인 정보 </legend>
            <div  id="join_notice">* 표시는 필수 입력 사항입니다. </div>
            <ul>
               <li>아이디</li>
               <li>
               <c:if test="${kakao!=null}">
                  <input type="text" id="idchk" value='Y' style="display: none; width: 30px; margin: 0px;">
               </c:if>
               <c:if test="${kakao==null}">
                  <input type="text" id="idchk" value='N' style="display: none; width: 30px; margin: 0px;">
               </c:if>
                  <input type="text" name="id" value="${kakao.id}" placeholder="* 6자리 이상 / 16자리 이하" id="join_id" maxlength=16/>
                  <span id='chk'></span> 
               </li>
               <li>비밀번호</li>
               <li>
                  <input type="password" name="pwd" id="join_pwd" placeholder="* 8자리 이상" />
                  <span id='pwdChk'></span>
               </li>
               <li>비밀번호 확인</li>
               <li>
                  <input type="password" name="pwd2" id="pwd2" placeholder="* 비밀번호 재입력" />
                  <span id='pwd2Chk'></span>
               </li>
            </ul>
         </fieldset>
         
         <fieldset>
            <legend>개인 정보</legend>
            <ul>
               <li>이름</li>
               <li><input type="text" name="name" id="name" placeholder="* 홍길동" value="${kakao.properties.nickname}" /></li>
               <li>성별</li>
               <li>
                  <ul id="radio_list">
                     <li><input type="radio" name="gender" id="gender_man" value="1" />남자 </li>
                     <li><input type="radio" name="gender" id="gender_woman" value="2" />여자</li>
                  </ul>
               </li>
               <li>이메일</li>
               <li>
                  <input type="text" name="email" id="email" placeholder="example@naver.com" value="${kakao.kakao_account.email}"/>
               </li>
               <li>연락처</li>
               <li>
                  <input type="text" name="tel" id="tel" placeholder="* (-) 없이 숫자만 입력해주세요" />
                  <span id='telChk'></span>
               </li>
               <li>연령대</li>
               <li>
                  <ul id="radio_list">
                     <li><input    type="radio" name="age" id="age10" value="1" />10대 </li>
                     <li><input type="radio" name="age" id="age20" value="2" />20대 </li>
                     <li><input type="radio" name="age" id="age30" value="3" />30대 </li>
                     <li><input type="radio" name="age" id="age40" value="4" />40대 </li>
                     <li><input type="radio" name="age" id="age50" value="5" />50대 이상</li>
                  </ul>                  
               </li>
            </ul>
         </fieldset>
			<input type="submit" value="가 입 하 기" />
		</form>
	</div>
</div>
<script src="${url}/js/inc/top.js"></script>
<script src="${url}/js/underBar.js"></script>