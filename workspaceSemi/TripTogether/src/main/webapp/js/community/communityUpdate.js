var str = "<div class='post_main_block_wrap'>";
str += "<div class='img_upload_wrap'>";
str += '<div class="img_upload">'
	+ '<img src="" class="preview">'
	+ '<span class="upload_phr"><span class="material-icons" style="vertical-align: middle;">file_upload</span>Upload a picture</span>'
	+ '</div>'
	+ '<input type="file" class="imgSelector" name="photo1" style="display:none;">';
str += "</div>";
str += "<div class='place_content_wrap'>";
str += "<div class='location_wrap'>";
str += '<a href="#ex1" rel="modal:open"><button class="location locationBtn">';
str += '<span class="material-icons" style="font-size: 14px; vertical-align: middle;">place</span>';
str += ' 장소 찾기</button></a> ';
str += "<input type='text' disabled class='location location1'>";
str += "<input type='text' class='location location2'></div>";
str += "<hr><textarea placeholder='사진에 대해 설명해주세요.'></textarea>";
str += "</div></div>";

var postCnt = 1;

$(function() {
	// 추가 버튼을 누르면 글쓰기 양식이 추가되는 함수
	$(document).on("click", "#addBtn", function() {
		$(".post_main_block_entire_wrap").append(str);
		postCnt += 1;

		if (postCnt >= 5) {
			$("#addBtn").css("display", "none");
		}
	});


	// 장소 등록
	$(document).on("click", ".locationBtn", function() {
		$(this).parent().next().attr("class", "location location1 location_name");
		$(this).parent().next().next().attr("class", "location location2 location_datail");
	})

	$(document).on("click", ".info h5", function() {
		var placeName = $(this).text();
		var placeAddr = $(this).next().text();
		$(".place_name").val(placeName);
		$(".place_addr").val(placeAddr);
		$(".location_name").val(placeName);
		$(".location_datail").val(placeAddr);
		$(".location_name").attr("class", "location location1");
		$(".location_datail").attr("class", "location location2");
		$("#keyword").val("");
	});


	// 이미지 미리보기
	$(document).on("change", '.imgSelector', function() {
		setImageFromFile(this, $(this).prev().children('img'));
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
	$(document).on("click", ".img_upload", function() {
		$(this).next().trigger("click");
		$(this).children('span').css("display", "none");
	})

	// 해시태그 색 변경
	$(document).on("click", ".hashtag_list li", function() {
		var chk = $(this).children('input').is(":checked");
		if (!chk) {
			$(this).children('input:checkbox[name="tag"]').prop("checked", true);
			$(this).css("color", "#fff").css("background-color", "#858585");
		} else {
			$(this).children('input:checkbox[name="tag"]').prop("checked", false);
			$(this).css("color", "gray").css("background-color", "#E2E2E2");
		}
	});


/* **************로그인 이벤트 출력************/
// 로그인 이벤트 출력
var loginbtn = document.querySelector(".log-in");
var logindiv = document.querySelector(".logindiv");


// 로그인 이벤트 끄기
var exitlogo = document.querySelector(".exit-logo");
loginbtn.addEventListener("click", function() {
    logindiv.style.right = "0px";
    for (let i = 0; i < opadiv.length; i++) {
            opadiv[i].style.opacity = "0.5";
        }
});

var opadiv = document.querySelectorAll(".opadiv");
exitlogo.addEventListener("click", function() {
    logindiv.style.right = "-500px";
    for (let i = 0; i < opadiv.length; i++) {
            opadiv[i].style.opacity = "1";
        }
});

$(function() {
		$(window).scroll(function() {
			if ($(this).scrollTop() > 500) {
				$('#MOVE_TOP_BTN').fadeIn();
			} else {
				$('#MOVE_TOP_BTN').fadeOut();
			}
		});

		$("#MOVE_TOP_BTN").click(function() {
			$('html, body').animate({
				scrollTop : 0
			}, 400);
			return false;
		});
});



});