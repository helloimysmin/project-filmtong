<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Anime Template">
<meta name="keywords" content="html">
<meta name="viewport" content="width=device-width, initial-scale=1.0 maximum-scale=1, minimum-scale=1"">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>FilmTong</title>
<%@ include file="/WEB-INF/views/common/css_coomon.jsp"%>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Normal Breadcrumb Begin -->
    <section class="normal-breadcrumb set-bg" data-setbg="img/normal-breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="normal__breadcrumb__text">
                        <h2>마이페이지</h2>
                        <p></p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Normal Breadcrumb End -->

    <!-- 상세화면 Section Begin -->
	<section class="signup spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div class="login__form">
						<div class="pwd_label_wrap">
							<label>PASSWORD</label>
						</div>
						<div class="input__item">
							<input type="password" id="uiPwdCheck" placeholder="PASSWORD" onkeydown="enterPassword(event)">
							<span class="icon_lock"></span>
						</div>
						<button onclick="passwordCheck()">비밀번호 확인</button>
           				<div id="reviews" style="display: none">
                        </div>
					</div>
				</div>
				<div class="col-lg-6">
					<div id="pwdConfirm" style="visibility:hidden">
						<div class="login__form">
							<div class="form-group mb-3">
								<select id="uiProfileImg" is="ms-dropdown">
									<option value="0" selected>동물 이미지를 선택해주세요!</option>
									<option value="/test/img/anime/animal1.png" data-image="/test/img/anime/animal1.png">코알라</option>
									<option value="/test/img/anime/animal2.png" data-image="/test/img/anime/animal2.png">펭귄</option>
									<option value="/test/img/anime/animal3.png" data-image="/test/img/anime/animal3.png">고양이</option>
									<option value="/test/img/anime/animal4.png" data-image="/test/img/anime/animal4.png">늑대</option>
									<option value="/test/img/anime/animal5.png" data-image="/test/img/anime/animal5.png">토끼</option>
									<option value="/test/img/anime/animal6.png" data-image="/test/img/anime/animal6.png">얼룩말</option>
								</select>
							</div>
							<div class="input__item">
								<input type="text" id="uiNickname" placeholder="이름" onkeydown="checkSpace(event)">
								<span class="icon_profile"></span>
							</div>
							<div class="input__item">
								<input type="text" id="uiId" placeholder="아이디" readonly >
								<span class="icon_profile"></span>
							</div>
							<div class="input__item">
								<input type="password" id="uiPwd" placeholder="PASSWORD" onkeydown="checkSpace(event)">
								<span class="icon_lock"></span>
								<span id="pwc"></span>
							</div>
							<div class="input__item">
								<input type="email" id="uiEmail" placeholder="Email" onkeydown="checkSpace(event)" readonly> 
								<span class="icon_mail"></span>
							</div>
							<button onclick="userModify()">회원 정보 수정</button>
							<button onclick="userDelete()">탈퇴</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- 상세화면 Section End -->
<%@include file="/WEB-INF/views/common/footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<%@include file="/WEB-INF/views/common/js_auth.jsp" %>
<script>
window.onload = function() {
	getUserInfo();
	searchMovies();
}

function enterPassword(evt){	/* password 입력시 엔터키로 로그인 */
	if(evt.keyCode == 13){
		passwordCheck();
	}
}
function getUserInfo() {
	fetch('/user-infos/' + ${param.uiNum})
	.then(function(res) {
		return res.text();
	})
	.then(function(data) {
		if(data) {
			data = JSON.parse(data);
			document.querySelector('#uiProfileImg').value = data.uiProfileImg;
			const inputObjs = document.querySelectorAll('#pwdConfirm input[id]');
			const uiPwd = document.querySelector('#uiPwd');
			for(const inputObj of inputObjs) {
				inputObj.value = data[inputObj.getAttribute('id')];
				uiPwd.value = '';
			}
		}else{
			swal('잘못된 접근.', '잘못된 접근입니다. 다시 시도하여주세요', 'error')
			.then(function() {
				location.href = '/';
			})
		}
		
	});
}
function passwordCheck(){	//비밀번호 확인
	const uiPwd = {
			uiPwd : document.querySelector('#uiPwdCheck').value
	}
	fetch('/user-infos/pwdCheck/' + ${param.uiNum}, {
		method : 'POST',
		headers : {
			'Content-type' : 'application/json'
		},
		body : JSON.stringify(uiPwd)
	})
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		if(data===true) {
			document.querySelector('#pwdConfirm').style.visibility='visible';
			swal('비밀번호 확인 완료', '회원 정보 수정 혹은 탈퇴가 가능합니다.', 'success');
		}else{
			swal('비밀번호를 확인해주세요', '잘못된 비밀번호입니다.' , 'error');
		}
	})
}
function userModify() {
	const uiNickname = document.querySelector('#uiNickname');
	if(uiNickname.value.trim().length < 1) {
		swal('이름 오류', '이름이 올바르지 않습니다.', 'warning');
		uiPwd.value = '';
		uiPwd.focus();
		return;
	}
	const uiPwd = document.querySelector('#uiPwd');
	if(uiPwd.value.trim().length < 6) {
		swal('비밀번호 오류', '비밀번호는 6글자 이상이어야합니다.', 'warning');
		uiPwd.value = '';
		uiPwd.focus();
		return;
	}
	const uiProfileImg = document.querySelector('#uiProfileImg');
	if(uiProfileImg.value == 0) {
		swal('프로필 사진', '프로필 사진을 선택해주세요', 'error');
		uiProfileImg.focus();
		return;
	}
	const param = {
			uiNickname : document.querySelector('#uiNickname').value,
			uiPwd : document.querySelector('#uiPwd').value,
			uiProfileImg : document.querySelector('#uiProfileImg').value
	}
	fetch('/user-infos/' + ${param.uiNum}, {
		method : 'PATCH',
		headers : {
			'Content-type' : 'application/json'
		},
		body : JSON.stringify(param)
	})
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		if(data===true) {
			swal('회원 정보 수정 완료', '수정이 완료되었습니다.', 'success')
			.then(function() {
				location.href = '';
			})
		}else{
			swal('잘못된 접근', '정보 수정을 다시 시도해주세요', 'error');
		}
	})
}
function userDelete() {
	fetch('/user-infos/' + ${param.uiNum}, {
		method : 'DELETE'
	})
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		if(data===true) {
			swal('회원 탈퇴 성공', '다음에 또 찾아주세요', 'success').
			then(function() {
				location.href = '/';
			})
		}else{
			swal('회원 탈퇴 실패', '다시 시도해주세요', 'error');
		}
	})
}
</script>
</body>
</html>