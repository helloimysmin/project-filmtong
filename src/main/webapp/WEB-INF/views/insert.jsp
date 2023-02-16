<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Anime Template">
<meta name="keywords" content="Anime, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>FilmTong</title>
<!-- Google Font -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800;900&display=swap" rel="stylesheet">
<link rel="shortcut icon" href="/test/img/android-icon-192x192.png">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@800&display=swap" rel="stylesheet">
<!-- Css Styles -->
<link rel="stylesheet" href="/test/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="/test/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="/test/css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="/test/css/plyr.css" type="text/css">
<link rel="stylesheet" href="/test/css/nice-select.css" type="text/css">
<link rel="stylesheet" href="/test/css/dd.css" type="text/css">
<link rel="stylesheet" href="/test/css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="/test/css/style2.css" type="text/css">
<link rel="stylesheet" href="/bootstrap/css/style.css">
</head>
<body>
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
							aria-label="Close">									<!-- 인서트 창에서는 X 먹기때문에 onclick 삭제 가능-->
					</button>
				</div>
				<div class="modal-body p-5 pt-0">
					<div class="form-floating mb-3">
						<input type="text" class="form-control rounded-3" id="uiId" placeholder="ID" onkeyup="nextPwdBlank(event)"> 
							<label for="floatingInput" style="color:gray"></label>
					</div>
					<div class="form-floating mb-3">
						<input type="password" class="form-control rounded-3" id="uiPwd" placeholder="PASSWORD" onkeyup="nextLogin(event)"> 
							<label for="floatingPassword" style="color:gray"></label>
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
<!-- signUp Start -->
<section class="ftco-section">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6 text-center mb-5">
				<h2 class="heading-section">회원가입</h2>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-md-7 col-lg-5">
				<div class="login-wrap">
					<div class="signup-form">
					<div class="form-group mb-3">
						<label class="text" for="name">PROFILE IMAGE</label><br>
							<select id="uiProfileImg" is="ms-dropdown">
								<option value="0" selected>동물 이미지를 선택해주세요!</option>
								<option value="/test/img/anime/animal1.png"
									data-image="/test/img/anime/animal1.png">코알라</option>
								<option value="/test/img/anime/animal2.png"
									data-image="/test/img/anime/animal2.png">펭귄</option>
								<option value="/test/img/anime/animal3.png"
									data-image="/test/img/anime/animal3.png">고양이</option>
								<option value="/test/img/anime/animal4.png"
									data-image="/test/img/anime/animal4.png">늑대</option>
								<option value="/test/img/anime/animal5.png"
									data-image="/test/img/anime/animal5.png">토끼</option>
								<option value="/test/img/anime/animal6.png"
									data-image="/test/img/anime/animal6.png">얼룩말</option>
							</select>
						</div>
						<div class="form-group mb-3">
							<label class="text" for="name">NICKNAME</label> 
							<input type="text" id="uiNickname" class="form-control"
								placeholder="이름" onkeydown="checkSpace(event)"> <span
								class="icon fa fa-user-o"></span>
						</div>
						<div class="form-group mb-3">
							<label for="inputId">ID</label> <input type="text"
								id="insertUiId" class="form-control" placeholder="아이디"
								onkeydown="checkSpace(event)"> <span
								class="icon fa fa-user-o"></span>
						</div>
						<button class="signup_btn" onclick="idCheck()">중복 확인</button>

						<div class="form-group mb-3">
							<label for="inputPwd">PASSWORD</label> <input type="password"
								id="insertUiPwd" class="form-control" placeholder="비밀번호"
								onkeydown="checkSpace(event)"> <span toggle="#password"
								class="fa fa-fw fa-eye field-icon toggle-password"></span> <span
								class="icon fa fa-lock"></span>
						</div>

						<div class="form-group mb-3">
							<label for="passwordCheck">VERIFY PASSWORD</label> <input
								id="uiPwdCheck" type="password" class="form-control"
								placeholder="비밀번호 확인" onkeydown="checkSpace(event)"> <span
								toggle="#password-confirm"
								class="fa fa-fw fa-eye field-icon toggle-password"></span> <span
								class="icon fa fa-lock"></span>
						</div>

						<div class="form-group mb-3">
							<label for="emailAuthenticationRequest">REQUEST
								CERTIFICATION NUMBER </label> <input type="email" id="uiEmail"
								class="form-control" placeholder="이메일"
								onkeydown="checkSpace(event)"> <span
								class="icon fa fa-user-o"></span>
						</div>
						<button class="signup_btn" onclick="emailNumSend()">인증
							번호 요청</button>
						<div class="form-group mb-3">
							<label for="emailAuthenticationConfirm">이메일 인증에는 5초 정도의
								시간이 소요될 수 있습니다.<br>잠시만 기다려주세요!
							</label>
						</div>
						<div class="form-group mb-3">
							<label for="emailAuthenticationConfirm">Verification
								NUMBER</label> <input type="text" id="uiCertification"
								class="form-control" placeholder="인증번호"
								onkeydown="checkSpace(event)"> <span
								class="icon fa fa-paper-plane-o"></span>
						</div>
						<button class="signup_btn" onclick="emailNumCheck()">인증
							번호 확인</button>
						<div class="form-group" style="margin: 60px">
							<button class="form-control btn btn-primary submit px-3"
								onclick="join()">회원 가입</button>
						</div>
					</div>
					<p>
						난 이미 가입되어있어요! <a href="/views/login">로그인</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</section>
<!--  signUp End -->
<%@include file="/WEB-INF/views/common/footer.jsp" %>
<!--  signUp End -->
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="/test/js/bootstrap.min.js"></script>
<script src="/bootstrap/js/dd.min.js"></script>
<script src="/test/js/jquery.nice-select.min.js"></script>
<script src="/test/js/jquery.slicknav.js"></script>
<script src="/test/js/mixitup.min.js"></script>
<script src="/test/js/player.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<script>
window.onload = function(){
	searchMovies(); 
}
let isCheckedId = false;
let isSendEmail = false;
let isCheckedEmail = false;
function idCheck() {
	const uiId = document.querySelector('#insertUiId');
	if (uiId.value.trim().length < 4) {
		swal('ID 중복 확인', '아이디는 4글자 이상이어야합니다.', 'warning');
		uiId.value = '';
		uiId.focus();
		return;
	}
	fetch('/user-info/check/' + document.querySelector('#insertUiId').value)
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		if (data === false) {
			swal('아이디 중복 확인 결과', '등록 가능한 아이디입니다', 'success');
			isCheckedId = true;
		} else {
			swal('아이디 중복 확인 실패', '이미 등록된 아이디입니다.', 'warning');
			isCheckedId = false;
		}
	})
}
function join() {
	const uiNickname = document.querySelector('#uiNickname');
	if (uiNickname.value.trim().length < 1) {
		swal('이름 오류', '이름이 올바르지 않습니다.', 'warning');
		uiPwd.value = '';
		uiPwd.focus();
		return;
	}
	const uiPwd = document.querySelector('#insertUiPwd');
	if (uiPwd.value.trim().length < 6) {
		swal('비밀번호', '비밀번호는 6글자 이상이어야합니다.', 'warning');
		uiPwd.value = '';
		uiPwd.focus();
		return;
	}
	const uiPwdCheck = document.querySelector('#uiPwdCheck');
	if (uiPwdCheck.value != uiPwd.value) {
		swal('비밀번호 확인', '비밀번호가 일치하지 않습니다.', 'error');
		uiPwdCheck.value = '';
		uiPwdCheck.focus();
		return;
	}
	const uiProfileImg = document.querySelector('#uiProfileImg');
	if (uiProfileImg.value == 0) {
		swal('이미지', '이미지를 선택해주세요', 'error');
		uiProfileImg.focus();
		return;
	}
	if (!isCheckedId) {
		swal('중복 확인 필요', '아이디 중복 확인이 필요합니다.', 'warning');
		isCheckedId = false;
		return;
	}
	if (!isSendEmail) {
		swal('이메일 인증 필요', '이메일 인증 번호를 요청해주세요.', 'warning');
		isSendEmail = false;
		return;
	}
	if (!isCheckedEmail) {
		swal('이메일 인증 필요', '이메일 인증을 완료해주세요.', 'warning');
		isCheckedEmail = false;
		return;
	}
	const param = {
		uiNickname : document.querySelector('#uiNickname').value,
		uiId : document.querySelector('#insertUiId').value,
		uiPwd : document.querySelector('#insertUiPwd').value,
		uiEmail : document.querySelector('#uiEmail').value,
		uiProfileImg : document.querySelector('#uiProfileImg').value
	}
	fetch('/user-info', {
		method : 'POST',
		headers : {
			'Content-Type' : 'application/json'
		},
		body : JSON.stringify(param)
	})
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		if (data === 1) {
			swal('가입 성공', '환영합니다! 가입에 성공 하셨습니다.', 'success')
			.then(function() {
				location.href = '/';
			})
		} else {
			swal('가입 실패', '죄송합니다. 올바른 작성이 필요합니다.', 'error');
		}
	})
}
function emailNumSend() {
	fetch('/mail-checks/' + document.querySelector('#uiEmail').value)
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		if (data === true) {
			swal('이메일 인증', '인증 번호가 발송되었습니다.', 'success');
			isSendEmail = true;
		} else {
			swal('이메일 발송 실패','이메일 형식이 올바르지 않습니다. 다시 시도해주세요','warning');
			isSendEmail = false;
		}
	})
}
function emailNumCheck() {
	const param = {
		uiEmail : document.querySelector('#uiEmail').value
	}
	fetch('/email-checked/' + document.querySelector('#uiCertification').value, {
			method : 'POST',
			headers : {
				'Content-type' : 'application/json'
			},
			body : JSON.stringify(param)
		})
		.then(function(res) {
			return res.json();
		})
		.then(function(data) {
			if (data === true) {
				swal('이메일 인증 성공', '이메일 인증이 완료되었습니다. sign-up 버튼을 눌러 회원가입을 완료해주세요.','success');
				isCheckedEmail = true;
			} else {
				swal('이메일 인증 실패', '이메일 인증이 실패하였습니다. 인증 번호 확인 후 다시 시도해주세요.', 'error');
				isCheckedEmail = false;
			}
		})
}
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
/* 없을시 모바일 메뉴 안나옴 */
$(".mobile-menu").slicknav({
	prependTo: '#mobile-menu-wrap',
	allowParentLinks: true
	});
</script>
</body>
</html>