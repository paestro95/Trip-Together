

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


var option = document.querySelector(".option");
var ap = document.querySelector(".app");
var a = document.querySelectorAll("a");


ap.addEventListener("mouseover", function() {
	ap.style.background = "white";
	ap.style.color = "black";
	for (let i = 0; i < a.length; i++) {
		a[i].style.color = "black";
	}


});


// 마우스 나갔을 때 다시 돌아오게함.
ap.addEventListener("mouseout", function() {
	if (option.style.height < "85px") {
		ap.style.background = "none";
		ap.style.color = "white";
		for (let i = 0; i < a.length; i++) {
			a[i].style.color = "white";
		}
	}
});

// 로그인 이벤트 출력
var loginbtn = document.querySelector(".log-in");
var logindiv = document.querySelector(".logindiv");


// 로그인 이벤트 끄기
var exitlogo = document.querySelector(".exit-logo");
loginbtn.addEventListener("click", function() {
	logindiv.style.right = "0px";
	alert("abcde");
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

const target = document.querySelector(".target");
const links = document.querySelectorAll(".submenu");

for (let i = 0; i < links.length; i++) {
	links[i].addEventListener("mouseenter", mouseenterFunc);
	links[i].addEventListener("mouseleave", mouseLeaveFunc);
}

function mouseenterFunc() {
	// 마우스가 올라가면 작동하는 함수
	if (!this.parentNode.classList.contains("active")) {
		for (let i = 0; i < links.length; i++) {
			// 액티브를 바꿈
			if (links[i].parentNode.classList.contains("active")) {
				links[i].parentNode.classList.remove("active");
			}
		}
		this.parentNode.classList.add("active");
		const width = this.getBoundingClientRect().width;
		const height = this.getBoundingClientRect().height;
		const left = this.getBoundingClientRect().left + window.pageXOffset;
		const top = this.getBoundingClientRect().top + window.pageYOffset;

		// 언더바가 따라다닐 위치 계산
		target.style.width = `${width}px`;
		target.style.height = `${height}px`;
		target.style.left = `${left}px`;
		target.style.top = `${top}px`;

		target.style.borderColor = "#8FAADC";
	}
}

function mouseLeaveFunc() {
	// 마우스가 떠나면 작동하는 함수
	for (let i = 0; i < links.length; i++) {
		links[i].parentNode.classList.remove("active");
	}
	target.style.borderColor = "transparent";
}

function resizeFunc() {
	// 브라우저 창의 크기가 바뀌어도 따라가는 함수
	const active = document.querySelector(".option li.active");

	if (active) {
		const left = active.getBoundingClientRect().left + window.pageXOffset;
		const top = active.getBoundingClientRect().top + window.pageYOffset;

		target.style.left = `${left}px`;
		target.style.top = `${top}px`;
	}
}

window.addEventListener("resize", resizeFunc);

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

