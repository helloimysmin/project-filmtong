	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
@media only screen and (min-width: 768px) and (max-width: 991px) {
	.header__right {
	    position: absolute;
	    right: 120px;
	    top: 23%;
	}
	.header__logo {
		padding: 20px 0 17px;
		width:100%;
	}
	
}

@media only screen and (min-width: 577px) and (max-width: 768px) {
	.header__right {
	    position: absolute;
	    right: 96px;
	    top: 23%;
	    padding:0;
	    
	}
	.header__logo {
		padding: 20px 0 17px;
		width:100%;
	}
	
}
@media only screen and (max-width: 576px) {
	.header__right {
	    position: absolute;
	    right: 96px;
	    top: 23%;
	    padding:0;
	}
	.header__logo {
		padding: 20px 0 17px;
		width:100%;
	}
	
}
/* css 중복 헤더 media */
</style>
<body>
<!-- Header Section Begin -->
<header class="header">
	<div class="container">
		<div class="rain" style="display:flex">
			<div class="col-lg-2">
				<div class="header__logo">
					<a href="/"> <img src="/test/img/logo.png" alt="로고">
					</a>
				</div>
			</div>
			<div class="col-lg-8">
				<div class="header__nav">
					<nav class="header__menu mobile-menu">
						 <ul>
                               <li class="active"><a href="/">Home</a></li>
                               <li><a style="cursor:pointer">필름통<span class="arrow_carrot-down"></span></a>
                                   <ul class="dropdown">
                                       <li><a href="/views/recommend-view">추천영화</a></li>
                                       <li><a href="/views/popular-view">인기영화</a></li>
                                       <li><a href="/views/hof-view">명예의 전당</a></li>
                                       <li><a href="/views/insert">회원가입</a></li>
                                   </ul>
                               </li>
                               <li><a style="cursor:pointer">장르별<span class="arrow_carrot-down"></span></a>
                                   <ul class="dropdown">
                                       <li><a style="cursor:pointer" onclick="moveToGenres(this)">로맨스</a></li>
                                       <li><a style="cursor:pointer" onclick="moveToGenres(this)">액션</a></li>
                                       <li><a style="cursor:pointer" onclick="moveToGenres(this)">공포</a></li>
                                       <li><a style="cursor:pointer" onclick="moveToGenres(this)">판타지</a></li>
                                       <li><a style="cursor:pointer" onclick="moveToGenres(this)">범죄</a></li>
                                       <li><a style="cursor:pointer" onclick="moveToGenres(this)">SF</a></li>
                                       <li><a style="cursor:pointer" onclick="moveToGenres(this)">가족</a></li>
                                       <li><a style="cursor:pointer" onclick="moveToGenres(this)">스릴러</a></li>
                                   </ul>
                               </li>  
                               <c:if test="${userInfo ne null}">
							<li><a href="/views/auth/my-page?uiNum=${userInfo.uiNum}">마이페이지</a></li>
							</c:if>
                          </ul>
					</nav>
				</div>
			</div>
			<div class="col-lg-2">
			<div class="header__right">
					<a href="#" class="search-switch"><span class="icon_search"></span></a>
					<div class="bd-example" style="display: none"></div>
					<c:if test="${userInfo eq null}">
					<a data-toggle="modal" href="#modalSignin" class="userIcon">
					<span class="icon_profile"></span></a>
					</c:if>
					<c:if test="${userInfo ne null}">
						<div class="dropdown">
						  <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
						 	${userInfo.uiNickname}님
						  </button>
						  <ul class="dropdown-menu">
						    <li><a class="dropdown-item" href="/views/auth/my-page?uiNum=${userInfo.uiNum}">마이페이지</a></li>
						    <li><a class="dropdown-item" href="/views/auth/user-activity?uiNum=${userInfo.uiNum}">활동 내역</a></li>
						    <li><a class="dropdown-item" onclick="logOut()" style="cursor:pointer">로그아웃</a></li>
						  </ul>
						</div>
				</c:if>
			</div>
		</div>
		</div>
		<c:if test="${userInfo eq null}">
		<div class="modal fade" id="modalSignin" tabindex="-1" role="dialog">
		<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
			<div class="modal-content rounded-4 shadow">
				<div class="modal-header p-5 pb-4 border-bottom-0">
					<!-- <h1 class="modal-title fs-5" >Modal title</h1> -->
					<h1 class="fw-bold mb-0 fs-2">로그인</h1>
					<button type="button" class="btn-close" data-dismiss="modal"
							aria-label="Close">
					</button>
				</div>
				<div class="modal-body p-5 pt-0">
					<div class="form-floating mb-3">
						<input type="text" class="form-control rounded-3" id="uiId" placeholder="ID" onkeyup="nextPwdBlank(event)"> 
							<label for="floatingInput" style="color:gray">ID</label>
					</div>
					<div class="form-floating mb-3">
						<input type="password" class="form-control rounded-3" id="uiPwd" placeholder="PASSWORD" onkeyup="nextLogin(event)"> 
							<label for="floatingPassword" style="color:gray">PASSWORD</label>
					</div>
					<button class="w-100 mb-2 btn btn-lg rounded-3 btn-danger" onclick="login()">로그인</button>
						<medium class="text-muted">
					<a href="/views/insert" class="link-primary">회원가입</a> </medium>
					</div>
				</div>
			</div>
		</div>
	</c:if>		
	<div id="mobile-menu-wrap"></div>
</div>
</header>
<!-- Header End -->
  <!-- Search model Begin -->
	<div class="search-model">
	  <div class="h-100 d-flex align-items-center justify-content-center">
	      <div class="search-close-switch"><i class="icon_close"></i></div>
	      <form class="search-model-form" action="/views/popular-view">
	      	<select id="searchMovieType" onchange="searchMovies()">
	      		<option value="title">제목</option>
	      		<option value="name">장르</option>
	      	</select>
	          <input type="text" id="searchType" placeholder="Search here....." onkeydown="checkSpace(event)">
	          <button class="searchbtn"onclick="mobileSearchMovies()">검색</button>
	      </form>
	  </div>
	</div>
	<!-- Search model end -->				
<script>
function mobileSearchMovies(){
	searchMovies();
}
function searchMovies() {
	document.querySelector('#searchType').name = document.querySelector('#searchMovieType').value;
}		

function checkSpace(evt) {		/* 검색시 스페이스 X */
	if (evt.keyCode == 32) {
		evt.preventDefault();
		return;
	}
}
function nextPwdBlank(evt){		/* id 입력후 password칸으로 focus이동 */
	if(evt.keyCode == 13){
		document.querySelector('#uiPwd').focus();
	}
}

function nextLogin(evt){	/* password 입력시 엔터키로 로그인 */
	if(evt.keyCode == 13){
		login();
	}
}
function moveToGenres(obj) {		//onclick="moveToGenres(this)" 를 해서 누른 태그의 innerHTML을 name의 value로
	location.href='/views/popular-view?name=' + obj.innerHTML;
}

function login() {
	const param = {
			uiId : document.querySelector('#uiId').value,
			uiPwd : document.querySelector('#uiPwd').value
	}
	fetch('/user-infos/login', {
		method : 'POST',
		headers : {
			'Content-type' : 'application/json'
		},
		body : JSON.stringify(param)
	})
	.then(function(res) {
		return res.text();		//login은 id,pwd로 selectOne. nullable. null이면 JSON으로 파싱이 안됨. 따라서 json.text();
	})
	.then(function(data) {
		if(data) {				//selectOne이 null이 아니다 => 조건식 만족 => JSON 파싱 후 로그인 처리.
			data = JSON.parse(data);		
			swal('환영합니다.', data.uiNickname + '님 어서오세요!', 'success')
			.then(function() {
				location.href='';	
			})
		}else {
			swal('로그인 실패', '회원 정보가 올바르지 않습니다.', 'error');
		}
	})
}
function logOut(){
	fetch('/user-infos/logout')
	.then(function(res){
		return res.json();
	})
	.then(function(data){
		if(data===true){
			swal('로그아웃되었습니다..', '고객님 잘가세요!', 'success')
			.then(function(){
				location.href= '';
			})
		}	
	})
}
</script>
</body>
</html>