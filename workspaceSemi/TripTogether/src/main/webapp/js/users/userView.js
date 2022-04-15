/**
 * 
 */

$(function() {

	// 이미지 hover 시 색 어둡게, 좋아요/댓글 보이기
	$(".post_card img").hover(function() {
		$(this).css("filter", "brightness(70%)");
		$(this).parent().next().css("display", "block");
	}, function() {
		$(this).css("filter", "brightness(100%)");
		$(this).parent().next().css("display", "none");
	});

	// 좋아요 버튼 클릭 시 색바꾸기
	$(".like").click(function() {
		if ($(this).html() == "favorite_border") {
			$(this).html("favorite");
		} else {
			$(this).html("favorite_border");
		}
	});

	// 저장 버튼 클릭 시 색바꾸기
	$(".save").click(function() {
		if ($(this).html() == "bookmark_border") {
			$(this).html("bookmark");
		} else {
			$(this).html("bookmark_border");
		}
	});


});