<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
							tag += "<input name='comment' class='iniput-comment' value='"+vo.comment+"'/>";
							tag += "<input type='submit' value='수정'/>";									
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
	
	$(function(){
		
	})
	function follow(obj, fid){
		//alert(obj+"/"+fid)
		var btText=$(obj).text();
		if(btText=='팔로우'){
			$.ajax({
				type:'post',
				url:'/follow/'+fid,
				success:function(res){
					alert(res);
					$(obj).text('언팔로우');
				}
			})
		}else if(btText=="언팔로우"){
			$.ajax({
				type:'post',
				url:'/unfollow/'+fid,
				success:function(res){
					alert(res);
					$(obj).text('팔로우');
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
				<h1 class="title">${vo.title}</h1>
			</header>
			
			<c:if test="${vo.photo1 != null}">
				<div class="main-image">
					<!-- 게시물 대표사진을 불러와용 -->
					<img src="${vo.photo1 }" alt="대표사진" class="mainPic">
				</div>
				<div class="content">
					<!-- 유저의 게시물을 불러옴 -->
					${vo.location1 } ${vo.location_addr1 } ${vo.content1 }
				</div>
			</c:if>
			
			<c:if test="${vo.photo2 != null}">
				<div class="main-image">
					<!-- 게시물 대표사진을 불러와용 -->
					<img src="${vo.photo2 }" alt="대표사진" class="mainPic">
				</div>
				<div class="content">
					<!-- 유저의 게시물을 불러옴 -->
					${vo.location2 } ${vo.location_addr2 } ${vo.content2 }
				</div>
			</c:if>
			
			<c:if test="${vo.photo3 != null}">
				<div class="main-image">
					<!-- 게시물 대표사진을 불러와용 -->
					<img src="${vo.photo3 }" alt="대표사진" class="mainPic">
				</div>
				<div class="content">
					<!-- 유저의 게시물을 불러옴 -->
					${vo.location3 } ${vo.location_addr3 } ${vo.content3 }
				</div>
			</c:if>
			
			<c:if test="${vo.photo4 != null}">
				<div class="main-image">
					<!-- 게시물 대표사진을 불러와용 -->
					<img src="${vo.photo4 }" alt="대표사진" class="mainPic">
				</div>
				<div class="content">
					<!-- 유저의 게시물을 불러옴 -->
					${vo.location4 } ${vo.location_addr4 } ${vo.content4 }
				</div>
			</c:if>
			
			<div class="hl"></div>
			
			
			
			
			
			<!-- 댓글달기 -->
			<div class="comment">
				
				<form method="post" id="replyFrm">
				<input type="hidden"name="id" value="${vo.id}">
				<input type="hidden" name="board_no" value="${vo.board_no}">
				<input name="comment" class="input-comment" placeholder="댓글 달기..."/>
				<input type="submit" class="submit-comment" value="게시"/>
			 	<!-- <input id="input-comment" class="input-comment" type="text" placeholder="댓글 달기...">
				<button type="submit" class="submit-comment">게시</button> -->
				</form>
			
			</div>
			<!-- 댓글목록 -->
			<div id="replyList"></div>
		</article>
	</div>

	<!-- main-right -->
	<div class="main-right">
		<div class="myProfile">
			<img class="pic" src="${uVo.user_img }" alt="프로필 사진">
			<div>
				<span class="id">${uVo.id }</span> <span class="info">${uVo.info}</span>
			</div>
		</div>
		<div class="midbtn">						
			<button class="followbtn" onclick="follow(this,'${vo.id}')">팔로우</button><%--팔로우 액션넣어야함 --%>
			<c:choose>
				<c:when test="${logStatus == 'Y' }"><%--액션넣어야함 --%>
					<button>
						<span class="material-icons like" style="cursor: pointer; vertical-align: middle;">favorite_border</span> ${vo.likes}
					</button>
					<button>
						<span class="material-icons save" style="cursor: pointer; vertical-align: middle;">bookmark_border</span> ${vo.wish}
					</button>
				</c:when>
				<%-- 로그인 상태가 아닐 때, alert + 로그인 div가 오른쪽에 나타남 --%>
				<c:otherwise>
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
			<div class="menu-title">
				<span class="sub-title">게시물</span> <span class="find-more">모두 보기</span>
			</div>
			<ul class="story-list">
				<li>
					<div class="content-Img">
						<img src="../img/jiii.jpg" alt="게시물 사진">
					</div>
				</li>
			</ul>
		</div>
		<c:if test="${logId == vo.id }">
		<div class="bottombtn">
			${vo.tags}
			${vo.board_no}
				<ul>
					<li><a href="/community/communityUpdate?no=${vo.board_no}">수정</a></li>
					<li>
						<a href="javascript:del()">
							삭제
						</a>
					</li>
				</ul>
			</div>
		</c:if>
	</div>
</main>