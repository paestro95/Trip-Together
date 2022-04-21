<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${url}/css/community/communityView.css" type="text/css" />
<link rel="stylesheet" href="${url}/css/main/main.css" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${url}/js/community/communityView.js"></script>
<script src="${url}/js/underBar.js"></script>
<script src="${url}/js/main/main.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script>
function del(){
	if(confirm("삭제하시겠습니까?")){
			// 확인버튼 선택시
			location.href = "${url}/community/communityDel?no=${vo.board_no}";
		}
}

//댓글 js 권지연
$(function(){
		//댓글목록을 가져오는 함수
		function replyListAll(){
			var params = "board_no="+$('#no').val();
			var url = "/reply/replyList?"+params;
	
			$.ajax({
				type:'get',
				url:url,
				data:params,
				success:function(result){
					var $result = $(result);
					var len = $result.length;
                    $("h3 span").text(len);
					var tag = "<ul>";
					
					$result.each(function(idx, vo){
						
						tag += "<li><div>"+vo.id;
						tag += " ("+vo.writedate+") ";
						
						if(vo.id=='${logId}'){//'${logId==vo.id}'
							tag += "<input type='button' class='edit-comment' value='수정'/>";
							tag += "<input type='button' class='del-comment' value='삭제' title='"+vo.reply_no+"'/>";
						}
						tag += "<br/>" + vo.comment + "</div>";
						
						//본인글일때 수정폼이 있어야 한다.
						if(vo.id=='${logId}'){
							tag += "<div style='display:none'><form method='post'>";
							tag += "<input type='hidden' name='reply_no' value='"+vo.reply_no+"'/>";
							tag += "<div class='edit_comment_wrap'><input name='comment' class='input-comment' value='"+vo.comment+"'/>";
							tag += "<input type='submit' class='edit_submit' value='수정'/></div>";									
							tag += "</form></div>";
						}
						tag += "<hr style='height:0.5px;border:none;color:#ebebeb;background-color:#ebebeb;' /></li>";							
					});
					tag+="</ul>";
					
					$("#replyList").html(tag);
					
				},error:function(e){
					console.log(e.responseText)
				}
			});
		}
		
		// 댓글 등록
		$("#replyFrm").submit(function(){
			event.preventDefault();// form 기본이벤트 제거
			if($("#comment").val()==''){
				alert("댓글을 입력 후 등록하세요.");
				return false;
			} else {
				var params = $("#replyFrm").serialize();	// 폼의 데이터 들이 모두 담김
				$.ajax({
					url: '/reply/replyWriteOK',
					data: params,
					type: 'POST',
					success: function(r) {
						//폼을 초기화
					
						$("#comment").val("");						
						//댓글 목록 출력 refresh
						replyListAll();
					},
					error: function(e) {
						console.log(e.responseText);
					}
				});
			}
		});
		//댓글 수정(Edit)버튼 선택시 해당폼 보여주기
		$(document).on('click','#replyList input[value=수정]', function(){
			$(this).parent().css("display","none");//숨기기
			//보여주기
			$(this).parent().next().css("display","block");
		});
		//댓글수정(DB)
		$(document).on('submit','#replyList form',function(){
			event.preventDefault();
			//데이터
			var params = $(this).serialize();
			var url = '/reply/replyEditOk';
			$.ajax({
				url:url,
				data:params,
				type:'POST',
				success:function(result){
					console.log(result);
					replyListAll();
				},error:function(e){
					console.log('수정에러발생');
				}
			});
		});
		//댓글삭제
      $(document).on('click','#replyList input[value=삭제]',function(){
          if(confirm('댓글을 삭제하시겠습니까?')){
              var params = "reply_no="+$(this).attr("title");
              $.ajax({
                  url:'/reply/replyDel',
                  data:params,
                  success:function(result){
                      console.log(result);
                      replyListAll();
                  }, error:function(){
                      console.log("댓글삭제에러.");
                  }
              })
          }
      });
		
		//현재댓글을 댓글
		replyListAll();
	});
	
	
function follow(obj, fid){
	var btText=$(obj).text().trim();
	if(btText=='팔로우'){
		$.ajax({
			type:'post',
			url:'/follow/'+fid,
			success:function(res){
				$(obj).text('언팔로우');
			}
		})
	}else if(btText=="언팔로우"){
		$.ajax({
			type:'post',
			url:'/unfollow/'+fid,
			success:function(res){
				$(obj).text('팔로우');
			}
		});
	}
}
	
// 좋아요 기능
function likesClick(obj, no){
	var likesText = $(obj).text().trim();
	if(likesText == 'favorite_border'){
		$.ajax({
		type: 'post',
		url: '/likes/' + no,
			success: function(r){
				$(obj).text('favorite');
				$("#likesCnt").text(r);
				console.log("좋아요 추가 성공!");
			},
			error: function(e){
				console.log("좋아요 추가 에러!!");
			}
		})
	}else if(likesText == 'favorite'){
		$.ajax({
			type: 'post',
			url: '/unlikes/' + no,
			success: function(r){
				$(obj).text('favorite_border');
				$("#likesCnt").text(r);
				console.log("좋아요 취소 성공!");
			},
			error: function(e){
				console.log("좋아요 취소 에러!!");
			}
		});
	}
}

$(".save").click(function() {
    var bno = $(this).attr('data-no')

    if ($(this).text() == "bookmark_border") {
        $(this).text("bookmark");
        $(this).addClass("bookmark");
        $(this).removeClass("bookmark_border");
        wish($(this), bno)
    } else {
        $(this).text("bookmark_border");            
        $(this).removeClass("bookmark");
        $(this).addClass("bookmark_border");
        wish($(this), bno)
    }
});

function wish(obj, wid) {
    //alert(wid + "<<");
    var btText = $(obj).text().trim();
    //alert(btText+"/"+btText.length)
    if (btText == 'bookmark_border') {
        $.ajax({
            type : 'post',
            url : '/wish',
            data : {
                board_no : wid
            },
            success : function(r) {
                $(obj).text('bookmark');
                $(obj).next().text(r);
                console.log(r);
            },
            error : function(e) {
                console.log(e);
            }
        });
    } else if (btText == 'bookmark') {
        $.ajax({
            type : 'post',
            url : '/unwish',
            data : {
                board_no : wid
            },
            success : function(r) {
                $(obj).text('bookmark_border');
                $(obj).next().text(r);
                console.log(r);
            },
            error : function(e) {
                console.log(e);
            }
        });
    }
}

</script>

<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>

<!-- main -->
<main>
	<div class="feeds">
		<input type="hidden" id="no" name="board_no" value="${vo.board_no}">
		<!-- article -->
		<article>
			<header>
				<div style="display: flex;">
					<div id="region_theme_wrap">${vo.region} | ${vo.theme}</div>
					<c:if test="${!empty vo.tags}">
						<div id="hashtag_wrap">${vo.tags}</div>
					</c:if>
				</div>
				<h2>${vo.title}</h2>
			</header>


			<div class="main_content_wrap">
				<div class="main-image">
					<!-- 게시물 대표사진을 불러와용 -->
					<img id="photo1" src="${vo.photo1}">
				</div>
				<div class="contents">
					<div class="location">"${vo.location1}"</div>
					<div class="location_addr">${vo.location_addr1}</div>
					<div class="content">${vo.content1}</div>
				</div>
			</div>

			<c:if test="${!empty vo.content2}">
				<div class="main_content_wrap">
					<div class="main-image">
						<!-- 게시물 대표사진을 불러와용 -->
						<img id="photo2" src="${vo.photo2}" id="photo2">
					</div>
					<div class="contents">
						<div class="location">"${vo.location2}"</div>
						<div class="location_addr">${vo.location_addr2}</div>
						<div class="content">${vo.content2}</div>
					</div>
				</div>
			</c:if>

			<c:if test="${!empty vo.content3}">
				<div class="main_content_wrap">
					<div class="main-image">
						<!-- 게시물 대표사진을 불러와용 -->
						<img src="${vo.photo3}" id="photo3">
					</div>
					<div class="contents">
						<div class="location">"${vo.location3}"</div>
						<div class="location_addr">${vo.location_addr3}</div>
						<div class="content">${vo.content3}</div>
					</div>
				</div>
			</c:if>

			<c:if test="${!empty vo.content4}">
				<div class="main_content_wrap">
					<div class="main-image">
						<!-- 게시물 대표사진을 불러와용 -->
						<img src="${vo.photo4}" id="photo4">
					</div>
					<div class="contents">
						<div class="location">"${vo.location4}"</div>
						<div class="location_addr">${vo.location_addr4}</div>
						<div class="content">${vo.content4}</div>
					</div>
				</div>
			</c:if>


			<!-- 댓글달기 -->
			<h3>
				댓글 <span id="replyCnt" style="color: #A3BDF0;">00</span>
			</h3>
			
			<div class="comment">
				<form method="post" id="replyFrm">
					<input type="hidden" value="${vo.id}">
					<input type="hidden" name="board_no" value="${vo.board_no}">
					<div style="display: flex;">
						<div class="comment_submit_wrap">
							<input name="comment" class="input_comment" id="comment" placeholder="댓글 달기..." />
							<input type="submit" class="submit_comment" value="게시" />
						</div>
					</div>
				</form>
			</div>
			
			<!-- 댓글목록 -->
			<div id="replyList">

			</div>
		</article>
	</div>

	<!-- main-right -->
	<div class="main-right">
		<div class="myProfile">
			<div id="user_img_wrap">
				<a href="${url}/users/userView?id=${uVo.id}"><img src="${uVo.user_img }"></a>
			</div>
			<div id="user_id_info_wrap">
				<div id="user_id"><a href="${url}/users/userView?id=${uVo.id}">${uVo.id}</a></div>
				<div>${uVo.info}</div>
			</div>
		</div>
		<div class="midbtn">	
			<c:choose>
				<c:when test="${logStatus == 'Y' }"><%--액션넣어야함 --%>
					<c:if test="${logId != vo.id}">			
						<button class="followbtn" onclick="follow(this,'${uVo.id}')">
							<c:if test="${isFollow==''||isFollow=='U'}">팔로우</c:if>
							<c:if test="${isFollow!=''&&isFollow=='F'}">언팔로우</c:if>
						</button>
					</c:if>
					<button class="likesBtn" onclick="likesClick('.like', '${vo.board_no}');">
						<span class="material-icons like" style="cursor: pointer; vertical-align: middle;">
							<c:if test="${isLikes == '' || isLikes == 'U'}">favorite_border</c:if>
							<c:if test="${isLikes != '' && isLikes == 'L' }">favorite</c:if> 
						</span> 
						&nbsp;<span id="likesCnt">${vo.likes}</span>
					</button>
					<button>
                        <span class="material-icons save" data-no="${vo.board_no}" style="cursor: pointer; vertical-align: middle;" onclick="wish(this,'${vo.board_no}')">
                        	<c:if  test="${isWish!=''&&isWish=='W'}">bookmark</c:if>
                        	<c:if  test="${isWish=='' || isWish=='N'}">bookmark_border</c:if>
                        </span> 
                        <span class="wishCnt">${vo.wish}</span>
                    </button>
				</c:when>
				
				<%-- 로그인 상태가 아닐 때, alert + 로그인 div가 오른쪽에 나타남 --%>
				<c:otherwise>
					<button onclick="btn_notlogin();">
						팔로우
					</button>
					<button>
						<span class="material-icons" style="cursor: pointer; vertical-align: middle;" onclick="btn_notlogin();">favorite_border</span> ${vo.likes}
					</button>
					<button>
						<span class="material-icons" style="cursor: pointer; vertical-align: middle;" onclick="btn_notlogin();">bookmark_border</span> ${vo.wish}
					</button>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- 게시물더보기-->
		<div class="section-story">
			<ul class="story-list">
				<li>
					<div class="content-Img">
						<a href="#photo1"><img src="${vo.photo1}"></a>
					</div>
				</li>
				<li>
					<div class="content-Img">
						<a href="#photo2"><img src="${vo.photo2}"></a>
					</div>
				</li>
				<li>
					<div class="content-Img">
						<a href="#photo3"><img src="${vo.photo3}"></a>
					</div>
				</li>
				<li>
					<div class="content-Img">
						<a href="#photo4"><img src="${vo.photo4}"></a>
					</div>
				</li>
				
			</ul>
		</div>
		
		
		<c:if test="${logId == vo.id }">
			<div class="bottombtn">
				<div><a href="/community/communityUpdate?no=${vo.board_no}">수정</a></div>
				<div><a href="javascript:del()"> 삭제</a></div>
			</div>
		</c:if>

	</div>
</main>