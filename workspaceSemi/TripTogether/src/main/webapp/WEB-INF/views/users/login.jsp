<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table id="back_table">
	<tr>
		<td>
			<h1 style="margin-bottom:100px; text-align: center;">로그인(임시)</h1>
			<form method="post" action="${url}/users/loginOk" id="frm">
				<div class="form-group">
					<input type="text" id="id" name="id" placeholder="아이디 입력" class="form-control">
				</div>
				<div class="form-group">
					<input type="password" id="pwd" name="pwd" placeholder="비밀번호 입력" class="form-control">
				</div>
				<input type="submit" value="LOGIN" class="form-control btn btn-secondary">
				
			</form>
		</td>
	</tr>
</table>
</body>
</html>