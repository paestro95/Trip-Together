$(function() {
	$(".wrap").on("click", function(){
		var postCnt = $(".post_main_block_wrap").length;
		if(postCnt >= 4){
			$("#addBtn").css("display", "none");
		}
		if($(".hashtag_list li input").prop("checked") == true){
			
		}
	});
	
	$(".wrap").click();
			
	// 추가 버튼을 누르면 글쓰기 양식이 추가되는 함수
	$(document).on("click", "#addBtn", function() {
			var postCnt = $(".post_main_block_wrap").length;
			var idNum = postCnt+1;
			
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
			str += '<div class="locationBtn_delBtn_wrap" style="display: flex;">'
				+ '<div style="flex: 1;">'
				+	'<a href="#ex1" rel="modal:open">'
				+ '<button class="location locationBtn">'
				+ '<span class="material-icons" style="font-size: 14px; vertical-align: middle;">place</span>장소 찾기'
				+'</button>'
				+ '</a>'
				+ '</div>'
				+ '<div class="delBtn" style="text-align: right;">'
				+ '<span class="material-icons del" style="vertical-align: middle; color: gray; cursor: pointer;">delete</span>'
				+ '</div>'
				+ '</div>';
			str += "<input type='text' class='location location1' name='location' id='location"+ idNum +"'>";
			str += "<input type='text' class='location location2' name='location_addr' id='location_addr"+ idNum +"'></div>";
			str += "<hr><textarea name='content' id='content"+  idNum + "' placeholder='사진에 대해 설명해주세요.'></textarea>";
			str += "</div></div>";
			
		$(".post_main_block_entire_wrap").append(str);
		postCnt++;
		
		if (postCnt >= 4) {
			$("#addBtn").css("display", "none");
		}
	});
	
	// 휴지통 버튼 클릭시 폼 삭제
	$(document).on("click", ".delBtn", function(){
		$(this).closest(".post_main_block_wrap").remove();
		
		var postCnt = $(".post_main_block_wrap").length;

		if (postCnt < 5) {
			$("#addBtn").css("display", "block");
		}
	});


	// 장소 등록
	$(document).on("click", ".locationBtn", function() {
		$(this).closest(".locationBtn_delBtn_wrap").next().attr("class", "location location1 location_name");
		$(this).closest(".locationBtn_delBtn_wrap").next().next().attr("class", "location location2 location_datail");
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
		$(this).parent().next().children().children("div").children(".delBtn").css("display", "block");
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
	
	
	$(document).ready(function(){
		$(document).on("click", "#submit", function(){
			var checkedTag = [];
			$("input[name=tag]:checked").each(function(){
				checkedTag.push($(this).val());
			});
			
			if(checkedTag.length > 0){
				var saveTag = checkedTag[0];
			}
			for(var i=1; i<checkedTag.length; i++){
				saveTag += " " + checkedTag[i];
			}
			
			$("#tags").val(saveTag);
		});
	});

});

// 커뮤니티 글등록 지역, 제목 빈칸 확인
function boardCheck(){
	if($("#region").val() == "지역"){
		alert("지역을 선택해 주세요.");
		return false;
	}
	
	if($("#title").val() == ''){
		alert("제목을 입력해 주세요.");
		return false;
	}
	
	if($("#location1").val() == ''){
		alert("장소를 입력해 주세요.");
		return false;
	}
	
	if($("#content1").val() == ''){
		alert("내용을 입력해 주세요.");
	}
}


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

