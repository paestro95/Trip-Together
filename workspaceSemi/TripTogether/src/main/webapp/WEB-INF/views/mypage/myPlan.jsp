<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPlan</title>
<link rel="stylesheet" href="/css/mypage/myPlan.css" type="text/css">
</head>
<body>
   <div class="container">
     
      <div id="myPlan_profile">
         <h3>MY PAGE</h3>
         <ul>
            <li><img src="/css/users/cat.jpg" id="myPlan_profile_img"/></li>
            <li>ROSE</li>
            <li>팔로워 100 | 팔로잉 100</li>
            <li><a href="#">MY PLAN</a></li>
            <li><a href="/mypage/myPlace" target="ifr">PLACE</a></li>
            <li><a href="#">WISH LIST</a></li>
            <li><a href="#">FOLLOWING</a></li>
            <li><a href="#">About</a> | <a href="#">Log Out</a></li>
         </ul> 
      </div>
      
      <!-- ///////////////////////////////////////////// -->
      
      <div id="myPlan_plan">
         <iframe src="#" name="ifr" style="width:100%; height:750px;" frameBorder="0"></iframe>
      </div>
      
   </div>

</body>
</html>