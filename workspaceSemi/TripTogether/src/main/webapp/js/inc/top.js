
var loginbtn = document.querySelector(".log-in");
var logindiv = document.querySelector(".logindiv");

loginbtn.addEventListener("click", function() {
	logindiv.style.right = "0px";
});


function movesignup(){
	location.href="/users/joinForm";
}