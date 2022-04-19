//가입하기 클릭 시
function memberCheck() {
	//아이디 입력 여부
	let userid = document.getElementById("join_id");
	if (userid.value == '') {
		alert("아이디를 입력하세요.");
		userid.focus();
		return false;
	}

	//아이디 형식+중복 여부('N')
	if (document.getElementById("idchk").value == 'N') {
		alert("아이디 형식 오류 또는 중복이 확인되지 않았습니다.");
		return false;
	}

	//비밀번호 입력 여부 + 확인란 체크
	let userpwd = document.getElementById("join_pwd");
	let userpwd2 = document.getElementById("pwd2");
	if (userpwd.value == '' || userpwd2.value == '') {
		alert("비밀번호와 비밀번호 확인란을 모두 입력해주세요.");
		userpwd.focus();
		return false;
	}
	if (userpwd.value != userpwd2.value) {
		alert("비밀번호가 비밀번호 확인란과 일치하지 않습니다.");
		userpwd2.focus();
		return false;
	}

	//이름 입력 여부
	let username = document.querySelector("#name");
	if (username.value == '') {
		alert("이름을 입력하세요.");
		username.focus();
		return false;
	}

	//전화번호
	let tel = document.getElementById("tel");
	let regTel = /^(010|011|016|019|02|031|032|041|051)[0-9]{3,4}[0-9]{4}$/;
	if (tel.value == '') {
		alert("전화번호를 입력하세요");
		tel.focus();
		return false;
	}
	if (!regTel.test(tel.value)) {
		alert("전화번호 형식이 잘못 되었습니다. 확인 후 다시 입력해주세요.");
		tel.focus();
		return false;
	}

	return true;
}

$(function() {
/*
	$("#profileImg").change(function() {
		$("#join_img").attr('src', "/css/img_changed.png");
	});*/



	//아이디 중복검사
	$("#join_id").keyup(function() {
		var id = $("#join_id").val();
		//공백이 아니고 && 6글자 이상일 때만 가능
		if (id != '' && id.length >= 6 && id.length <= 16) {		// + 정규표현식도 맞춰주기!
			var url = "/users/userIdCheck"
			$.ajax({
				url: url,
				data: "id=" + id,
				type: "POST",
				success: function(result) {
					if (result > 0) {	//중복 데이터 존재함 (불가능)
						$("#chk").html("이미 있는 아이디입니다.");
						$("#idchk").val('N');
						$("#chk").css("color", "red").css("font-size", "14px");
					} else {			//중복 데이터 없음(가능)
						$("#chk").html("사용 가능한 아이디입니다.");
						$("#idchk").val('Y');
						$("#chk").css("color", "blue").css("font-size", "14px");
					}
				}
			});
		} else {		//중복은 모르겠고 형식 오류(불가능)
			$("#chk").html("잘못된 아이디 형식입니다.");
			$("#idchk").val('N');
			$("#chk").css("color", "red").css("font-size", "14px");
		}
	});

	//유효성 검사
	//비밀번호
	$("#join_pwd").keyup(function() {
		if ($("#join_pwd").val().length < 8) {
			$("#pwdChk").html("취약한 비밀번호입니다.").css('color', 'red').css("font-size", "14px");
		} else {
			$("#pwdChk").html("사용 가능한 비밀번호입니다.").css('color', 'blue').css("font-size", "14px");
		}
	});

	//비밀번호 확인
	$("#pwd2").keyup(function() {
		if ($("#join_pwd").val() != $("#pwd2").val()) {
			$("#pwd2Chk").html("비밀번호가 일치하지 않습니다.").css('color', 'red').css("font-size", "14px");
		} else {
			$("#pwd2Chk").html("비밀번호가 일치합니다.").css('color', 'blue').css("font-size", "14px");
		}
	});

	//전화번호 확인
	let regTel2 = /^(010|011|016|019|02|031|032|041|051)[0-9]{3,4}[0-9]{4}$/;
	$("#tel").keyup(function() {
		if (!regTel2.test($("#tel").val())) {
			$("#telChk").html("전화번호 형식이 잘못 되었습니다.").css('color', 'red').css("font-size", "14px");
		} else {
			$("#telChk").html("&nbsp;").css('color', 'blue').css("font-size", "14px");
		}
	});

	// 이미지 미리보기
	$(document).on("change", '#profileImg', function() {
		setImageFromFile(this, "#join_img");
	});

	function setImageFromFile(input, expression) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$(expression).attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}

	// 

	$("#join_img_wrap").click(function() {
		$("#profileImg").trigger("click");
		$("#img_upload_phr").css("display", "none");
	})


});
