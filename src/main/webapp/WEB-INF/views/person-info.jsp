<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta charset="UTF-8">
    <meta name="description" content="Anime Template">
    <meta name="keywords" content="Anime, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>출연자 정보</title>
    <%@ include file="/WEB-INF/views/common/css_coomon.jsp" %>
</head>
<style>
.tooltip{
	background:black;
	color:white;
}
</style>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="/"><i class="fa fa-home"></i> Home</a>
                        <a onclick="history.back()" >MovieTrailer</a>	<!-- 이전 페이지  -->
                        <span>actors</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Section Begin -->
    <section class="anime-details spad">
        <div class="container">
            <div class="anime__details__content">
                <div class="row">
                    <div class="col-lg-3">
                        <div class="credit__details__pic set-bg" data-setbg="">	<!-- profile_path 들어갈곳 -->
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <div class="anime__details__text">
                            <div class="anime__details__title">
                                <h3 id="actorName"></h3>
                            </div>
                            <div class="credit__details__widget">
                            	<div class="col-lg-6 col-md-6">
                                	<ul>
                                	 	<li><span>성별 :&nbsp;</span><span id="gender"></span></li>
                                       	<li><span>생년월일 :&nbsp;</span><span id="birthDay"></span></li>
                                       	<li><span>출생지 :&nbsp;</span><span id="placeOfBirth"></span></li>
                                       	<li><span>홈페이지 :&nbsp;</span><span id="homePage"></span></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="anime__details__btn">
                                <button class="follow-btn" data-bs-toggle="tooltip" data-bs-placement="bottom" title="좋아요" id="actorThumbsUp"><i class="fa fa-heart-o"></i> 좋아요</button>
                                <span id="comment"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="section-title">
					<h5>필모그래피</h5>
					<span class="overviewInfo">마우스를 올리면 상세 정보가 나옵니다</span>
				</div>
                <div class="flex-container" >      
                  <div class="card person">
                       <img id="product__item__pic" data-bs-toggle="tooltip" data-bs-placement="bottom">
                       <div class="card-body person"> 
                           <span class="person-mvTitle"></span>
                           <div class="character"></div>
                   	  </div>
                  </div>
                  <div class="card person">
                       <img id="product__item__pic" data-bs-toggle="tooltip123" data-bs-placement="bottom123">
                       <div class="card-body person"> 
                           <span class="person-mvTitle"></span>
                           <div class="character"></div>
                   	  </div>
                  </div>
                  <div class="card person">
                       <img id="product__item__pic" data-bs-toggle="tooltip" data-bs-placement="bottom">
                       <div class="card-body person"> 
                           <span class="person-mvTitle"></span>
                           <div class="character"></div>
                   	  </div>
                  </div>
                   <div class="card person">
                       <img id="product__item__pic" data-bs-toggle="tooltip" data-bs-placement="bottom" >
                       <div class="card-body person"> 
                           <span class="person-mvTitle"></span>
                           <div class="character"></div>
                   	  </div>
                  </div>
                   <div class="card person">
                       <img id="product__item__pic" data-bs-toggle="tooltip" data-bs-placement="bottom">
                       <div class="card-body person"> 
                           <span class="person-mvTitle"></span>
                           <div class="character"></div>
                   	  </div>
                  </div>
                   <div class="card person">
                       <img id="product__item__pic" data-bs-toggle="tooltip" data-bs-placement="bottom">
                       <div class="card-body person"> 
                           <span class="person-mvTitle"></span>
                           <div class="character"></div>
                   	  </div>
                  </div>
                   <div class="card person">
                       <img id="product__item__pic" data-bs-toggle="tooltip" data-bs-placement="bottom">
                       <div class="card-body person"> 
                           <span class="person-mvTitle"></span>
                           <div class="character"></div>
                   	  </div>
                  </div>
                  <div class="card person">
                       <img id="product__item__pic" data-bs-toggle="tooltip" data-bs-placement="bottom">
                       <div class="card-body person"> 
                           <span class="person-mvTitle"></span>
                           <div class="character"></div>
                   	  </div>
                  </div>
                  <div class="card person">
                       <img id="product__item__pic" >
                       <div class="card-body person"> 
                           <span class="person-mvTitle"></span>
                           <div class="character"></div>
                   	  </div>
                  </div>
                  <div class="card person">
                       <img id="product__item__pic" data-bs-toggle="tooltip" data-bs-placement="bottom">
                       <div class="card-body person"> 
                           <span class="person-mvTitle"></span>
                           <div class="character"></div>
                   	  </div>
                  </div> 
                  <input type="hidden" id="uiNum" value="${userInfo.uiNum}">
				</div>
          	</div>
       </section>
        <!-- Section End -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
<%@ include file="/WEB-INF/views/common/js_common.jsp" %>
<script>
window.onload = function() {
	searchMovies();
	getPersonInfo();
	getActorThumbsUp();
}
function getPersonInfo() {
	fetch('/person-info/${param.personId}')
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		if(data.gender === 1){						
			document.querySelector('#gender').innerHTML = '여';		// 1은 여, 2는 남
		}else{
			document.querySelector('#gender').innerHTML = '남';
		}
		document.querySelector('#actorName').innerHTML = data.name;	
		document.querySelector('.credit__details__pic').style.backgroundImage = "url('https://image.tmdb.org/t/p/original" + data.profile_path + "')";
		if(!data.profile_path){
			document.querySelector('.credit__details__pic').style.backgroundImage = "url('https://m.okbible.com/data/skin/mobile_ver2_default/images/common/noimage.gif')";
		}
		document.querySelector('#birthDay').innerHTML = data.birthday;	
		document.querySelector('#placeOfBirth').innerHTML = data.place_of_birth;
		if(!data.homepage){													//homepage 값이 null 일시 홈페이지 없음 , 아닐시 홈페이지 링크 
			document.querySelector('#homePage').innerHTML = '공식 홈페이지가 없습니다.';	
		}else{
			document.querySelector('#homePage').innerHTML = '<a href="' + data.homepage + '">' +  data.homepage + '</a>';	
		}
		const itemPics = document.querySelectorAll('#product__item__pic');
		for(let i=0; i < itemPics.length; i++) {
			itemPics[i].style.backgroundImage = "url('https://image.tmdb.org/t/p/original" + data.credits.cast[i].poster_path + "')";
			if(!data.credits.cast[i].poster_path) {	
				itemPics[i].style.backgroundImage = "url('https://m.okbible.com/data/skin/mobile_ver2_default/images/common/noimage.gif')";
			}	
			itemPics[i].title = "\"" + data.credits.cast[i].overview + "\"";
			if(!data.credits.cast[i].overview) {
				itemPics[i].title = '상세 정보가 없습니다.';
			}
			document.querySelectorAll('.person-mvTitle')[i].innerHTML = data.credits.cast[i].original_title; 
			document.querySelectorAll('.character')[i].innerHTML = '-' +  data.credits.cast[i].character + '역';
		}
	})
}
document.querySelector('#actorThumbsUp').addEventListener('click', function() {
	if(!(document.querySelector('#uiNum').value)) {
		swal('로그인 필요', '로그인을 해주시기 바랍니다.', 'error')
		return;
	}
	const param = {
			uiNum : document.querySelector('#uiNum').value,
			piId : ${param.personId}
	}
	if(document.querySelector('#actorThumbsUp').classList.contains('active')) {
		fetch('/actor-evaluation', {
			method : 'DELETE',
			headers : {
				'Content-type' : 'application/json'
			},
			body : JSON.stringify(param)
		})
		.then(function(res) {
			return res.json();
		})
		.then(function(data) {
			if(data === 1) {
				document.querySelector('#actorThumbsUp').classList.remove('active');
				swal('좋아요 해제!', '좋아요를 해제 하셨습니다.', 'info');
				getActorThumbsUp();
			}
		})
	}else {
		fetch('/actor-evaluation', {
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
			if(data === 1) {
				getActorThumbsUp();
				swal('좋아요!', '이 배우를 좋아요 하셨습니다.', 'success');
			}
		})
	}	
})
function getActorThumbsUp() {
	if(!(document.querySelector('#uiNum').value)) {
		return;
	}
	fetch('/actor-evaluation/${param.personId}/' + document.querySelector('#uiNum').value)
	.then(function(res) {
		return res.text();
	})
	.then(function(data) {
		if(data) {
			data = JSON.parse(data);
			if(data.aeThumbsUp === 'Y') {
				document.querySelector('#actorThumbsUp').classList.add('active');
				document.querySelector('#comment').innerHTML = '이미 좋아요를 누르셨습니다~';
			}
		}else{
			document.querySelector('#comment').innerHTML = '이 배우를 좋아한다면 좋아요를 눌러주세요~';
		}
	})
}
const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))	//툴팁
</script>
</body>
</html>