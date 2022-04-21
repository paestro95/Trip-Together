function editCheck() {

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
	//전화번호 확인
	let regTel2 = /^(010|011|016|019|02|031|032|041|051)[0-9]{3,4}[0-9]{4}$/;
	$("#tel").keyup(function() {
		if (!regTel2.test($("#tel").val())) {
			$("#telChk").html("전화번호 형식이 잘못 되었습니다.").css('color', 'red').css("font-size", "14px");
		} else {
			$("#telChk").html("&nbsp;").css('color', 'blue').css("font-size", "14px");
		}
	});

	$("#mFrm").submit(function() {
		alert("회원정보 수정이 완료되었습니다.");
		return true;
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

	//  이미지 영역 클릭하면 파일 버튼 클릭
	$("#join_img_wrap").click(function() {
		$("#profileImg").trigger("click");
		$("#img_upload_phr").css("display", "none");
	})

})