 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Anime Template">
<meta name="keywords" content="Anime, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>로그인</title>
<%@ include file="/WEB-INF/views/common/css_coomon.jsp"%>
</head>
<body>
<header class="header">
	<div class="container">
		<div class="row">
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
                               <li><a>필름통<span class="arrow_carrot-down"></span></a>
                                   <ul class="dropdown">
                                       <li><a href="/views/recommend-view">추천영화</a></li>
                                       <li><a href="/views/popular-view">인기영화</a></li>
                                       <li><a href="/views/hof-view">명예의 전당</a></li>
                                       <li><a href="/views/insert">회원가입</a></li>
                                   </ul>
                               </li>
                               <li><a href="/views/movie-board">게시판</a></li>
                           </ul>
					</nav>
				</div>
			</div>
			<div class="col-lg-2">
				<div class="header__right">
						<a href="#" class="search-switch"><span class="icon_search"></span></a>
						<div class="bd-example" style="display: none">
						</div>
				</div>
			</div>
		</div>
	<div id="mobile-menu-wrap"></div>
		</div>
</header>
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
	      </form>
	  </div>
	</div>
<!-- Search model end -->	
<!-- Normal Breadcrumb Begin -->
    <section class="normal-breadcrumb set-bg" data-setbg="img/normal-breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="normal__breadcrumb__text">
                        <h2>Login</h2>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Normal Breadcrumb End -->
    
    <!-- Login Section Begin -->
    <section class="login spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="login__form">
                        <h3>Login</h3>
                            <div class="input__item">
                                <input type="text" id="uiId" placeholder="ID">
                                <span class="icon_profile"></span>
                            </div>
                            <div class="input__item">
                                <input type="password" id="uiPwd" placeholder="Password">
                                <span class="icon_lock"></span>
                            </div>
                            <button class="site-btn" onclick="login()">Login</button>
                    	</div>
                	</div>
                <div class="col-lg-6">
                    <div class="login__register">
                        <h3>계정이 없으신가요?</h3>
                        <a href="/views/insert" class="primary-btn">Sign Up</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Login Section End -->
<%@include file="/WEB-INF/views/common/footer.jsp" %>
<%@include file="/WEB-INF/views/common/js_common.jsp" %>
<script>
window.onload = function() {
	searchMovies();
}
function searchMovies() {
	document.querySelector('#searchType').name = document.querySelector('#searchMovieType').value;
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
		return res.text();
	})
	.then(function(data) {
		if(data) {
			data = JSON.parse(data);
			swal('환영합니다.', data.uiNickname + '님 어서오세요!', 'success')
			.then(function() {
				location.href='/';	
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
		}else{
			swal('비정상적인 접근입니다.', '', 'error');
		}
	})
}
</script>
</body>
</html>	