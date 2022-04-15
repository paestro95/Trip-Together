var option = document.querySelector(".option");
var ap = document.querySelector(".app");
var a = document.querySelectorAll("a");


ap.addEventListener("mouseover", function() {
	ap.style.background = "white";
	ap.style.color = "black";
	for (let i = 0; i < a.length; i++) {
		alert(a[i]);
		a[i].style.color = "black";
	}


});
$('#top2 .app').hover(function() {
	//ap.style.background = "white";
	$(this).css('background', 'white')
	$(this).css('color', "black")
	$('#title').css('color', "black")
	$('#login').css('color', "black")
	$('#logout').css('color', "black")
	$('#submenu1').css('color', "black")
	$('#submenu2').css('color', "black")
	$('#submenu3').css('color', "black")

	/*ap.style.color = "black";
	for (let i = 0; i < a.length; i++) {
		alert(a[i]);
		a[i].style.color = "black";
	}*/
}, function() {

	if ($("nav.option").height() < "85") {
		$(this).css('background', 'none')
		$(this).css('color', "white")
		$('#title').css('color', "white")
		$('#login').css('color', "white")
		$('#logout').css('color', "white")
		$('#submenu1').css('color', "white")
		$('#submenu2').css('color', "white")
		$('#submenu3').css('color', "white")

	}

})



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











