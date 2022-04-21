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
		
		$(".travelog_img_wrap li").hover(function(){
            $(this).children('a').children('img').css("filter", "brightness(70%)");
            $(this).children('div').css("display", "block");
        }, function(){
            $(this).children('a').children('img').css("filter", "brightness(100%)");
            $(this).children('div').css("display", "none");
        });
});


