/**
 * 
 */
 
 $(function(){
	
	// 사용자 프로필/아이디 hover 시 색바꾸기 
	$(".user_img").hover(function(){
		$(this).css("opacity", "50%");
		$(this).next().css("opacity", "50%");
	},function(){
		$(this).css("opacity", "initial");
		$(this).next().css("opacity", "initial");
	});
	
	// 사용자 프로필/아이디 hover 시 색바꾸기 
	$(".id").hover(function(){
		$(this).css("opacity", "50%");
		$(this).prev().css("opacity", "50%");
	}, function(){
		$(this).css("opacity", "initial");
		$(this).prev().css("opacity", "initial");
	});
	
	// 팔로우 hover 시 색바꾸기
	$(".followBtn").hover(function(){
		$(this).css("opacity", "50%");
	}, function(){
		$(this).css("opacity", "initial");
	});
	
	// 이미지 hover시 확대
	$(".card_img").hover(function(){
		$(this).css("transform", "scale(1.1)").css("transition", "transform .5s");	
		$(this).closest(".card_main_img_wrap").next().next().css("opacity", "50%");
	}, function(){
		$(this).css("transform", "scale(1)");
		$(this).closest(".card_main_img_wrap").next().next().css("opacity", "initial");
	});
	
	// 글 hover 시 색바꿈
	$(".content_prev a").hover(function(){
		$(this).css("opacity", "50%");
		$(this).parent().prev().prev().children().children().css("transform", "scale(1.1)").css("transition", "transform .5s");	
	}, function(){
		$(this).css("opacity", "initial");
		$(this).parent().prev().prev().children().children().css("transform", "scale(1)");
	});

	
	// 카테고리 중복선택 시 이전에 선택한 메뉴 삭제
	$(".category_list li").click(function(){
		var addv = "close" + $(this).text();
		$(".selected").each(function(index, item){
			var item = $(item).text();
			if(addv == item){
				$(this).remove();
			}
		});
		$(".select_cate_wrap").append("<div class='selected' value='"+$(this).html()+"'><span class='material-icons' style='cursor:pointer;vertical-align: middle;'>close</span>"+$(this).html()+"</div>");	
	});
	
	var rCate = [];
	var tCate = [];
	var hCate = [];
	
	/* 지역 카테고리 선택 */
	$(".region li").click(function(){
		
		var rName = $(this).text();
		rCate.push(rName);
		
		$(".card_wrap").css("display", "none");
		if(changeStatus){
			if(tCate.length == 0 && hCate.length == 0){
				for(var i=0; i<rCate.length; i++){
				$("."+rCate[i]+".new").css("display", "block");
				}
			}
			if(rCate.length > 0 && tCate.length > 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						for(var z=0; z<hCate.length; z++)
						$("." + rCate[i]+"."+tCate[j]+"."+hCate[j]+".new").css("display", "block");
					}	
				}
			}
			if(tCate.length > 0 && hCate.length == 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						$("." + rCate[i]+"."+tCate[j]+".new").css("display", "block");
					}	
				}
			}	
			if(tCate.length == 0 && hCate.length > 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<hCate.length; j++){
						$("." + rCate[i]+"."+hCate[j]+".new").css("display", "block");
					}	
				}
			}
		}else{
			if(tCate.length == 0 && hCate.length == 0){
				for(var i=0; i<rCate.length; i++){
				$("."+rCate[i]+".hot").css("display", "block");
				}
			}
			if(rCate.length > 0 && tCate.length > 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						for(var z=0; z<hCate.length; z++)
						$("." + rCate[i]+"."+tCate[j]+"."+hCate[j]+".hot").css("display", "block");
					}	
				}
			}
			if(tCate.length > 0 && hCate.length == 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						$("." + rCate[i]+"."+tCate[j]+".hot").css("display", "block");
					}	
				}
			}	
			if(tCate.length == 0 && hCate.length > 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<hCate.length; j++){
						$("." + rCate[i]+"."+hCate[j]+".hot").css("display", "block");
					}	
				}
			}
		}
	});
	
	/* 분류 카테고리 선택 */
	$(".theme li").click(function(){
		
		var tName = $(this).text();
		tCate.push(tName);
		
		$(".card_wrap").css("display", "none");
		
		if(changeStatus){
			if(rCate.length == 0 && hCate.length == 0){
				for(var i=0; i<tCate.length; i++){
					$("."+tCate[i]+".new").css("display", "block");
				}
			}
			if(rCate.length > 0 && hCate.length > 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						for(var z=0; z<hCate.length; z++)
						$("." + rCate[i]+"."+tCate[j]+"."+hCate[j]+".new").css("display", "block");
					}	
				}
			}	
			if(rCate.length > 0 && hCate.length == 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						$("." + rCate[i]+"."+tCate[j]+".new").css("display", "block");
					}	
				}
			}
			if(rCate.length == 0 && hCate.length > 0){
				for(var i=0; i<tCate.length; i++){
					for(var j=0; j<hCate.length; j++){
						$("." + tCate[i]+"."+hCate[j]+".new").css("display", "block");
					}	
				}
			}		
		}else{
			if(rCate.length == 0 && hCate.length == 0){
				for(var i=0; i<tCate.length; i++){
					$("."+tCate[i]+".hot").css("display", "block");
				}
			}
			if(rCate.length > 0 && hCate.length > 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						for(var z=0; z<hCate.length; z++)
						$("." + rCate[i]+"."+tCate[j]+"."+hCate[j]+".hot").css("display", "block");
					}	
				}
			}	
			if(rCate.length > 0 && hCate.length == 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						$("." + rCate[i]+"."+tCate[j]+".hot").css("display", "block");
					}	
				}
			}
			if(rCate.length == 0 && hCate.length > 0){
				for(var i=0; i<tCate.length; i++){
					for(var j=0; j<hCate.length; j++){
						$("." + tCate[i]+"."+hCate[j]+".hot").css("display", "block");
					}	
				}
			}		
		}
	});
	
	/* 해시태그 카테고리 선택 */
	$(".hashtag li").click(function(){
		var hName = $(this).text();
		hName = hName.substr(1);
		hCate.push(hName);
		
		$(".card_wrap").css("display", "none");
		if(changeStatus){
			if(rCate.length == 0 && tCate.length == 0){
				for(var i=0; i<hCate.length; i++){
					$("."+hCate[i]+".new").css("display", "block");
				}
			}
			if(rCate.length > 0 && tCate.length > 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						for(var z=0; z<hCate.length; z++)
						$("." + rCate[i]+"."+tCate[j]+"."+hCate[j]+".new").css("display", "block");
					}	
				}
			}
			if(rCate.length > 0 && tCate.length == 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<hCate.length; j++){
						$("." + rCate[i]+"."+hCate[j]+".new").css("display", "block");
					}	
				}
			}
			if(rCate.length == 0 && tCate.length > 0){
				for(var i=0; i<tCate.length; i++){
					for(var j=0; j<hCate.length; j++){
						$("." + tCate[i]+"."+hCate[j]+".new").css("display", "block");
					}	
				}
			}		
		}else{
			if(rCate.length == 0 && tCate.length == 0){
				for(var i=0; i<hCate.length; i++){
					$("."+hCate[i]+".hot").css("display", "block");
				}
			}
			if(rCate.length > 0 && tCate.length > 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						for(var z=0; z<hCate.length; z++)
						$("." + rCate[i]+"."+tCate[j]+"."+hCate[j]+".hot").css("display", "block");
					}	
				}
			}
			if(rCate.length > 0 && tCate.length == 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<hCate.length; j++){
						$("." + rCate[i]+"."+hCate[j]+".hot").css("display", "block");
					}	
				}
			}
			if(rCate.length == 0 && tCate.length > 0){
				for(var i=0; i<tCate.length; i++){
					for(var j=0; j<hCate.length; j++){
						$("." + tCate[i]+"."+hCate[j]+".hot").css("display", "block");
					}	
				}
			}	
		}
	});
	
	// x버튼 누르면 선택 메뉴 삭제
	$(document).on("click", ".selected span", function(){
		
		var delCate = $(this).parent().attr("value");
		$(this).parent().remove();
		$(".card_wrap").css("display", "none");
		
		for(let i = 0; i < rCate.length; i++) {
		  if(rCate[i] === delCate)  {
		    rCate.splice(i, 1);
		    i--;
		  }
		}
		
		for(let i = 0; i < tCate.length; i++) {
		  if(tCate[i] === delCate)  {
		    tCate.splice(i, 1);
		    i--;
		  }
		}
		
		for(let i = 0; i < hCate.length; i++) {
		  if(hCate[i] === delCate.substring(1))  {
		    hCate.splice(i, 1);
		    i--;
		  }
		}
		
		if(changeStatus){
			/* 지역 */
			if(tCate.length == 0 && hCate.length == 0){
				for(var i=0; i<rCate.length; i++){
				$("." + rCate[i]+".new").css("display", "block");
				}
			}
			
			if(rCate.length > 0 && tCate.length > 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						for(var z=0; z<hCate.length; z++)
						$("." + rCate[i]+"."+tCate[j]+"."+hCate[j]+".new").css("display", "block");
					}	
				}
			}
				
			if(tCate.length > 0 && hCate.length == 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						$("." + rCate[i]+"."+tCate[j]+".new").css("display", "block");
					}	
				}
			}	
				
			if(tCate.length == 0 && hCate.length > 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<hCate.length; j++){
						$("." + rCate[i]+"."+hCate[j]+".new").css("display", "block");
					}	
				}
			}
			
			/* 분류 */
			if(rCate.length == 0 && hCate.length == 0){
				for(var i=0; i<tCate.length; i++){
					$("."+tCate[i]+".new").css("display", "block");
				}
			}
			
			if(rCate.length > 0 && hCate.length > 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						for(var z=0; z<hCate.length; z++)
						$("." + rCate[i]+"."+tCate[j]+"."+hCate[j]+".new").css("display", "block");
					}	
				}
			}
				
			if(rCate.length > 0 && hCate.length == 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						$("." + rCate[i]+"."+tCate[j]+".new").css("display", "block");
					}	
				}
			}
			
			if(rCate.length == 0 && hCate.length > 0){
				for(var i=0; i<tCate.length; i++){
					for(var j=0; j<hCate.length; j++){
						$("." + tCate[i]+"."+hCate[j]+".new").css("display", "block");
					}	
				}
			}		
			
			/* 해시태그 */
			if(rCate.length == 0 && tCate.length == 0){
				for(var i=0; i<hCate.length; i++){
					$("."+hCate[i]+".new").css("display", "block");
				}
			}
			
			if(rCate.length > 0 && tCate.length > 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						for(var z=0; z<hCate.length; z++)
						$("." + rCate[i]+"."+tCate[j]+"."+hCate[j]+".new").css("display", "block");
					}	
				}
			}
			
			if(rCate.length > 0 && tCate.length == 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<hCate.length; j++){
						$("." + rCate[i]+"."+hCate[j]+".new").css("display", "block");
					}	
				}
			}
			
			if(rCate.length == 0 && tCate.length > 0){
				for(var i=0; i<tCate.length; i++){
					for(var j=0; j<hCate.length; j++){
						$("." + tCate[i]+"."+hCate[j]+".new").css("display", "block");
					}	
				}
			}		
			
			if(rCate.length == 0 && tCate.length == 0 && hCate.length == 0){
				$(".card_wrap.new").css("display", "block");
			}
		}else{
			/* 지역 */
			if(tCate.length == 0 && hCate.length == 0){
				for(var i=0; i<rCate.length; i++){
				$("." + rCate[i]+".hot").css("display", "block");
				}
			}
			
			if(rCate.length > 0 && tCate.length > 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						for(var z=0; z<hCate.length; z++)
						$("." + rCate[i]+"."+tCate[j]+"."+hCate[j]+".hot").css("display", "block");
					}	
				}
			}
				
			if(tCate.length > 0 && hCate.length == 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						$("." + rCate[i]+"."+tCate[j]+".hot").css("display", "block");
					}	
				}
			}	
				
			if(tCate.length == 0 && hCate.length > 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<hCate.length; j++){
						$("." + rCate[i]+"."+hCate[j]+".hot").css("display", "block");
					}	
				}
			}
			
			/* 분류 */
			if(rCate.length == 0 && hCate.length == 0){
				for(var i=0; i<tCate.length; i++){
					$("."+tCate[i]+".hot").css("display", "block");
				}
			}
			
			if(rCate.length > 0 && hCate.length > 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						for(var z=0; z<hCate.length; z++)
						$("." + rCate[i]+"."+tCate[j]+"."+hCate[j]+".hot").css("display", "block");
					}	
				}
			}
				
			if(rCate.length > 0 && hCate.length == 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						$("." + rCate[i]+"."+tCate[j]+".hot").css("display", "block");
					}	
				}
			}
			
			if(rCate.length == 0 && hCate.length > 0){
				for(var i=0; i<tCate.length; i++){
					for(var j=0; j<hCate.length; j++){
						$("." + tCate[i]+"."+hCate[j]+".hot").css("display", "block");
					}	
				}
			}		
			
			/* 해시태그 */
			if(rCate.length == 0 && tCate.length == 0){
				for(var i=0; i<hCate.length; i++){
					$("."+hCate[i]+".hot").css("display", "block");
				}
			}
			
			if(rCate.length > 0 && tCate.length > 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<tCate.length; j++){
						for(var z=0; z<hCate.length; z++)
						$("." + rCate[i]+"."+tCate[j]+"."+hCate[j]+".hot").css("display", "block");
					}	
				}
			}
			
			if(rCate.length > 0 && tCate.length == 0){
				for(var i=0; i<rCate.length; i++){
					for(var j=0; j<hCate.length; j++){
						$("." + rCate[i]+"."+hCate[j]+".hot").css("display", "block");
					}	
				}
			}
			
			if(rCate.length == 0 && tCate.length > 0){
				for(var i=0; i<tCate.length; i++){
					for(var j=0; j<hCate.length; j++){
						$("." + tCate[i]+"."+hCate[j]+".hot").css("display", "block");
					}	
				}
			}		
			
			if(rCate.length == 0 && tCate.length == 0 && hCate.length == 0){
				$(".card_wrap.new.hot").css("display", "block");
			}
		}
	});
	
    var  changeStatus = true;
	// 선택한 카테고리 초기화
	$(document).on("click", ".select_reset", function(){
		$(".select_cate_wrap").empty();
		$(".card_wrap").css("display", "none");
		if(changeStatus){
			$(".new").css("display", "block");
		}else{
			$(".hot").css("display", "block");
		}
		
		rCate = [];
		tCate = [];
		hCate = [];
	});
	
	// 신규
	$("#newBtn").click(function(){
		$(this).css("font-weight", "bold");
		$("#hotBtn").css("font-weight", "initial");
		$(".new").css("display", "block");
		$(".hot").css("display", "none");
		changeStatus = true;
		$(".select_cate_wrap").empty();
	});
	
	// 인기
	$("#hotBtn").click(function(){
		$(this).css("font-weight", "bold");
		$("#newBtn").css("font-weight", "initial");
		$(".hot").css("display", "block");
		$(".new").css("display", "none");
		changeStatus =false;
		$(".select_cate_wrap").empty();
	});
	
});

function btn_notlogin(){
	alert("로그인 후 이용해 주세요.");
	$(".logindiv").css("right", "0px");
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
			scrollTop: 0
		}, 400);
		return false;
	});
});
