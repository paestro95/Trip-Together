//팔로우 기능 함수
$("#followbtn").on('click', function() {
	follow(true);
});
$("#unfollowbtn").on('click', function() {
	follow(false);
});

function follow(check) {
	if (check) {
		$.ajax({
			type: "POST",
			url: "/triptogether/follow/${user.id}",//팔로우, 유저 담을 공간 필요
			headers: {
				"Content-Type": "application/json",
				"X-HTTP-Method-Override": "POST"
			},
			success: function(result) {
				console.log("result : " + result);
				if (result === "FollowOk") {
					$(".follow").html("<button class='followbtn' id='unfollowbtn'>언팔로우</button)");
					location.href = "/triptogether/post/${user.id}"
				}
			}
		});
	} else {
		$.ajax({
			type: "POST",
			url: "triptogether/unfollow/${user.id}",
			headers: {
				"Content-Type": "application/json",
				"X-HTTP-Method-Override": "POST"
			},
			success: function(result) {
				console.log("result : " + result);
				if (result === "UnFollowOk") {
					$(".follow").html("<button class='followbtn' id='followbtn'>팔로우</button>");
					location.href = "triptogether/post/${user.id}";
				}
			}
		});
	}
}

// 글삭제 기능
function del() {
	if (confirm('삭제할까요?')) {
		location.href = "community/communityDel?no=${vo.no}";
	}
}