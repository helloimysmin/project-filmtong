<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Anime Template">
<meta name="keywords" content="Anime, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>영화 정보</title>
<%@ include file="/WEB-INF/views/common/css_coomon.jsp"%>
<style>
</style>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
<!-- Breadcrumb Begin -->
<div class="breadcrumb-option">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="breadcrumb__links">
					<a href="/"><i class="fa fa-home"></i> Home</a> <a
						href="">Genre</a><span id="genre"></span> 
						<span></span>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Breadcrumb End -->
<!-- Anime Section Begin -->
<section class="anime-details spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="anime__details__episodes">
					<div class="section-title">
						<h5 id="movieTitle"></h5>	
					</div>
				</div>
				<div id="movieTrailer">
				</div>
				<div id="empty-overview" style="display: none"></div>	
				<div class="anime__details__episodes">
					<div class="section-title">				
						<h5>OVERVIEW</h5>
					<div class="overviews"></div>		
					</div>
				</div>
				<div class="movieInfo-wrap">
					<div class="anime__details__episodes">
						<div class="section-title">
							<h5>개봉일자</h5>
						</div>
						<div id="release_date"></div>	
					</div>	
					<div class="anime__details__episodes">
						<div class="section-title">
							<h5>평점</h5>
						</div>
						<div id="vote_average"></div>	
					</div>
					<div class="anime__details__episodes">
						<div class="section-title">
							<h5>좋아요 <input type="button" data-bs-toggle="tooltip" data-bs-placement="top" title="이 영화가 마음에 듭니다." id="thumbsUp"
							 value="♥" style="WIDTH: 30pt; HEIGHT: 30pt; display: none;"/></h5><br>
							<span id="thumbsUpText">로그인이 필요한 서비스입니다.</span><br>
							<span id="totalThumbsUp"></span>	<!-- 좋아요 누른 유저 total -->				
						</div>
					</div>
					<div class="anime__details__episodes">
						<div class="section-title">
							<h5>내 별점</h5>
						</div>
						<div id="starSec"></div>
					</div>
				</div>
				<!-- actors -->
				<div class="section-title">
							<h5>출연 배우</h5>
				</div>
           		 <div class="flex-container">      
                   <div class="card person" >
                       <img id="product__item__pic">
                       <div class="card-body person">                                                             
                           <p class="name"></p>                        
                           <div class="character"></div>
                   	  </div>
                  </div>
 				<div class="card person" >
                       <img id="product__item__pic">
                       <div class="card-body person">                                                             
                           <p class="name"></p>                        
                           <div class="character"></div>
                   	  </div>
                  </div>
 					<div class="card person" >
                       <img id="product__item__pic">
                       <div class="card-body person">                                                             
                           <p class="name"></p>                        
                           <div class="character"></div>
                   	  </div>
                  </div>
 					<div class="card person" >
                       <img id="product__item__pic">
                       <div class="card-body person">                                                             
                           <p class="name"></p>                        
                           <div class="character"></div>
                   	  </div>
                  </div>
 					<div class="card person" >
                       <img id="product__item__pic">
                       <div class="card-body person">                                                             
                           <p class="name"></p>                        
                           <div class="character"></div>
                   	  </div>
                  </div>
                  <div class="card person" >
                       <img id="product__item__pic">
                       <div class="card-body person">                                                             
                           <p class="name"></p>                        
                           <div class="character"></div>
                   	  </div>
                  </div>
                   <div class="card person" >
                       <img id="product__item__pic">
                       <div class="card-body person">                                                             
                           <p class="name"></p>                        
                           <div class="character"></div>
                   	  </div>
                  </div>
                   <div class="card person" >
                       <img id="product__item__pic">
                       <div class="card-body person">                                                             
                           <p class="name"></p>                        
                           <div class="character"></div>
                   	  </div>
                  </div>
                   <div class="card person" >
                       <img id="product__item__pic">
                       <div class="card-body person">                                                             
                           <p class="name"></p>                        
                           <div class="character"></div>
                   	  </div>
                  </div>
                   <div class="card person" >
                       <img id="product__item__pic">
                       <div class="card-body person">                                                             
                           <p class="name"></p>                        
                           <div class="character"></div>
                   	  </div>
                  </div>
                   <div class="card person" >
                       <img id="product__item__pic">
                       <div class="card-body person">                                                             
                           <p class="name"></p>                        
                           <div class="character"></div>
                   	  </div>
                  </div>
                   <div class="card person" >
                       <img id="product__item__pic">
                       <div class="card-body person">                                                             
                           <p class="name"></p>                        
                           <div class="character"></div>
                   	  </div>
                  </div>   
                   	          
				</div>
			</div>
					<div class="wrapper12">
						<div class="anime__details__episodes">
							<div class="section-title">
								<h5 class="reivews">REVIEWS</h5>
								<h6 class="review"></h6>
							</div>
						</div>
						
 				<!-- 리뷰 작성 컨테이너 -->
				<div class="anime__details__form" style="display:none">
					<div class="section-title"></div>
						<div class="reviewSend">
							<div id="myform">
								 <fieldset>
									<span class="text-bold">별점을 선택해주세요</span>
									<input type="radio" name="reviewStar" value="5" id="rate1" onclick="starRating(5)">
									<label for="rate1">★</label>
									<input type="radio" name="reviewStar" value="4" id="rate2" onclick="starRating(4)">
									<label for="rate2">★</label>
									<input type="radio" name="reviewStar" value="3" id="rate3" onclick="starRating(3)">
									<label for="rate3">★</label>
									<input type="radio" name="reviewStar" value="2" id="rate4" onclick="starRating(2)">
									<label for="rate4">★</label>
									<input type="radio" name="reviewStar" value="1" id="rate5" onclick="starRating(1)">
									<label for="rate5">★</label>
								</fieldset>
							</div>
								<button id="reviewBtn">
									<i class="fa fa-location-arrow"></i> Review
								</button>
							</div>
							<textarea id="mrContent" placeholder="당신의 리뷰를 남겨주세요!"></textarea>
							<input type="hidden" id="mrNum">
							<input type="hidden" id="uiNum" value="${userInfo.uiNum}">
						</div>
						<div class="anime__details__episodes">
							<div class="section-title">
								<c:if test="${userInfo ne null}">
									<a class="btn12" style="cursor:pointer" onclick="selectButton('INSERT')">리뷰 작성</a>
									<h5>작성자</h5>
									<h6 id="uiId">${userInfo.uiId}</h6>
								</c:if>
								<div>
									<c:if test="${userInfo eq null}">
										<h5>로그인이 필요한 서비스입니다. 댓글 기능을 이용하시려면 로그인을 해주세요.</h5>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>	
		<!-- 프로필 이미지.. 마이페이지 수정   -->

		<!-- 리뷰 section -->
			<div class="row">
				<div class="reviewsection">
					<div class="anime__details__review">
						<div class="section-title">
							<h5></h5>
						</div>
					</div>
			</div>
		</div>
</section>
<!-- Anime Section End -->
<%@include file="/WEB-INF/views/common/footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="/bootstrap/js/popper.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/bootstrap/js/main.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="/bootstrap/js/dd.min.js"></script>
<script src="/test/js/bootstrap.min.js"></script>
<script src="/test/js/player.js"></script>
<script src="/test/js/jquery.nice-select.min.js"></script>
<script src="/test/js/mixitup.min.js"></script>
<script src="/test/js/jquery.slicknav.js"></script>
<script src="/test/js/owl.carousel.min.js"></script>
<script src="/test/js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<script>
document.addEventListener("DOMContentLoaded", function(){
	getMovieTrailers();
	getMovieReviews();
	getUserEvaluation();
	getMovieInfos();
	getCredits();
	getTotalThumbsUp();
	searchMovies();
});

let sum = 0;
function starRating(num){			//리뷰 작성할 때 별을 누르면 num에 담긴 숫자만큼 #starSec 태그에 별이 찍히도록 하는 function
	let html = '';
	sum = num;
	for(let i=0; i<sum; i++) {
		html += '<span class="starRating">★</span>';
	}
	document.querySelector('#starSec').innerHTML = html;
}
function getMovieTrailers() {
	let loop = false;
	fetch('/movie-trailer/' + ${param.id})		//movie id로 트레일러 api 조회.
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		let html = '';
		if(data.results.length > 2) {			//트레일러가 2개 이하면 루프 안돌게
			loop = true;
		}else if(!(data.results.length)) {		//트레일러가 없다면 만족. #empty-overview display 보이도록.
			document.querySelector('#empty-overview').style.display = '';	
			return;
		}
		html += '<div class="movie-trailer owl-carousel owl-theme">';		//owl-theme 있어야 dots 생성	
		for(let i=0; i<data.results.length; i++) {	
			if(i >= 5) {
				break;
			}
			html += '<div class="item-video" data-merge="2">';
			html += '<iframe src="https://www.youtube-nocookie.com/embed/' + data.results[i].key; 
			html += '?vq=hd1080&origin=http://filmtong.info" title="YouTube video player" frameborder="0"';
			html +=	'width="100%" height="400px" allowfullscreen></iframe></div>';
		} 
		html += '</div>';
		document.querySelector('#movieTrailer').insertAdjacentHTML('afterbegin', html);
	})
	.then(function() {
      $('.movie-trailer').owlCarousel({			//태그 생성 후 owlCarousel이 실행되어야 슬라이드가 생성됨.
    	   navigation : true,
		   loop: loop,
		   center: true,
		   margin:3,
		   merge: true,
		   video:true,
	       nav: true,
	       dots: true,
		   navText: ["<span class='arrow_carrot-left'></span>", "<span class='arrow_carrot-right'></span>"],
		   responsive:{
		       0:{
		           items: 1
		           },            
		       600:{
		           items: 2
		           },        
		       900:{
		           items: 3
		           }
		   }    
       });
	})
}
function getTotalThumbsUp() {
	fetch('/user-evaluation/${param.id}')
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		document.querySelector('#totalThumbsUp').innerText = '총 ' + data.length + '명의 유저가 좋아요를 눌렀습니다!';
	})
}
function getMovieInfos() {
	fetch('/popular-movies/' + ${param.id})
	.then(function(res) {
		return res.json(); 
	})
	.then(function(data) {
		let html = '';
		for(let i=0; i<data.length; i++) {
			if(!data[i].backdropPath) {			//backdropPath가 없다면 준비중 이미지 삽입.
				document.querySelector('#empty-overview').style.backgroundImage = "url('https://m.okbible.com/data/skin/mobile_ver2_default/images/common/noimage.gif')";
			}else{
				document.querySelector('#empty-overview').style.backgroundImage = "url('https://image.tmdb.org/t/p/original" + data[i].backdropPath + "')";
			}
			document.querySelector('.overviews').insertAdjacentHTML('afterbegin', data[i].overview); 
			document.querySelector('#movieTitle').insertAdjacentHTML('afterbegin' ,data[i].title + '  (' + data[i].originalTitle + ')');
			document.querySelector('#vote_average').insertAdjacentHTML('afterbegin', data[i].voteAverage + '점');
			document.querySelector('#release_date').insertAdjacentHTML('afterbegin', data[i].releaseDate);
			document.querySelector('#genre').insertAdjacentHTML('afterbegin', data[i].name);
			if(!(data[i].overview)) {
				document.querySelector('.overviews').insertAdjacentHTML('afterbegin', '<span>유저님들의 리뷰로 상세 정보를 채워주세요!</span>');
			}
		}
	})
}
function getUserEvaluation() {
	if(!(document.querySelector('#uiId').innerHTML)) { //el태그로 #uiId에 아이디를 넣었음. 세션에 저장된 로그인 정보 없다면 ""이기 때문에 if문 사용
		return;
	}
	document.querySelector('#thumbsUp').style.display = '';
	fetch('/user-evaluation?id=${param.id}&uiId=' + document.querySelector('#uiId').innerHTML)
	.then(function(res) {
		return res.text();			//movie id와 uiId(묶어서 pk) 로 유저 좋아요 여부 확인. nullable 따라서 res.text();
	})
	.then(function(data) {	
		if(data) {					//넘어온 data가 !=null이면 조건 만족.
			data = JSON.parse(data);
			if(data.ueThumbsUp === 'Y') {
				document.querySelector('#thumbsUp').setAttribute('onclick', 'deleteThumbsUp()');
				document.querySelector('#thumbsUpText').innerHTML = '이미 좋아요를 누르셨습니다.';
				document.querySelector('#thumbsUp').classList.add('active');
			}
		}else{
			document.querySelector('#thumbsUpText').innerHTML = '좋아요를 눌러주세요!';
			document.querySelector('#thumbsUp').classList.remove('active');
			document.querySelector('#thumbsUp').setAttribute('onclick', 'insertThumbsUp()');
		}
	})
	
}
function insertThumbsUp() {
	const param = {
			uiNum : document.querySelector('#uiNum').value,
			id : ${param.id}
	}
	fetch('/user-evaluation', {
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
			getUserEvaluation();		//누르자마자 active되도록 한 번 더 호출했다.
			getTotalThumbsUp();
			swal('좋아요!', '이 영화에 좋아요를 클릭하셨습니다!', 'success');
		}else {
			swal('잘못된 접근', '잘못된 접근입니다.', 'error');
		}
	})
} 
function deleteThumbsUp() {
	const param = {
			uiNum : document.querySelector('#uiNum').value,
			id : ${param.id}
	}
	fetch('/user-evaluation', {
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
			getUserEvaluation();		//누르자마자 deactive 되도록 한 번 더 호출했다.
			getTotalThumbsUp();
			swal('좋아요 해제!', '이 영화에 대한 좋아요를 해제하셨습니다.', 'success');
		}else{
			swal('잘못된 접근!', '잘못된 접근입니다.', 'error');
		}
	})
}
function getMovieReviews() {											//review 
	fetch('/movie-review/' + ${param.id})
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		let html = '';
		for(let i=0; i<data.length; i++) {
			const uiId = document.querySelector('#uiId').innerHTML;		//el태그 사용해서 로그인 되어있으면 id가 넘어온다.
			const today = new Date();									//현재 시간 확인. ex)Sun Jan 29 2023 22:24:12 GMT+0900 (한국 표준시)
			const totalCreate = data[i].credat + ' ' + data[i].cretim;	//최초 작성 일자와 시간을 합쳤다. 
			const totalModify = data[i].lmodat + ' ' + data[i].lmotim;  //수정 일자, 시간을 합침. ex) 2023-01-25 11:26:25
			const create = new Date(totalCreate);						//작성일시 dateFormat을 today와 같게 맞춤
			const modify = new Date(totalModify);						//수정일시  (상동)
			let time = '';
			let diffTime = '';
			if(totalCreate === totalModify) {							//수정 안했으면 만족.
				diffTime = today.getTime() - create.getTime();
				time = '작성 시간 : '
			}else {
				diffTime = today.getTime() - modify.getTime();
				time = '마지막 수정 시간 : '
			}
			let dateDiffMin = Math.floor(diffTime % (1000*60*60) / (1000 * 60));	//분
			let dateDiffHour = Math.floor(diffTime / (60*60*1000));					//시	
			let dateDiffDay = Math.floor(diffTime / (24*60*60*1000));				//일
			html += '<div class="anime__review__item">';
			html += '<div class="anime__review__item__pic">';
			html += '<img src="' + data[i].uiProfileImg + '" alt="">'; 
			html += '</div><div class="anime__review__item__text">';
			html += '<h6>' + data[i].uiNickname;
			if(dateDiffDay >= 1) {				//1일 차이 이상 날 때.
				html += '<br><span>'+ time + dateDiffDay +'일 전</span></h6>';	
			}else if(dateDiffHour >= 1) {		//1시간 이상 차이 날 때
				html += '<br><span>'+ time + dateDiffHour +'시간 전</span></h6>';
			}else if(dateDiffMin >= 0){			 				
				html += '<br><span>'+ time + dateDiffMin +'분 전</span></h6>';
			}
			html += '<p>' + data[i].mrContent + '</p>';
			let text = '';
			if(!data[i].mrRating) {							//sum=0일 시 만족.
				html +='<span style="color:#b7b7b7;"> 별점을 등록해 주세요! </span>';
			}else {
				for(let j=0; j<data[i].mrRating; j++) {
					html += '<span class="starRating">★</span>';
					text += '<span class="starRating">★</span>';	//댓글을 남긴 사람 평점 #starSec에 찍혀보이게 하기 위해서 하나 더 넣어줌.
				}				
			}
			if(uiId === data[i].uiId) {	//uiId는 unique이기 때문에 세션 정보 id와 DB를 조회한 uiId가 같으면 리뷰에는 수정 삭제 버튼 활성화 + 여러 태그에 innerHTML.
				html += '<button onclick="removeMovieReviews(' + data[i].mrNum + ')" style="border:1px solid #1d1e39; border-radius: 2px;background-color:#1d1e39 ;color:white;float:right;">삭제</button>';
				html += '<button onclick="selectButton(' + data[i].mrNum + ')" style="border:1px solid #1d1e39; border-radius: 2px;background-color:#1d1e39 ;color:white;float:right;">수정</button>';
				if(sum) {
					sum = data[i].mrRating; //166행에 선언된 전역 변수. uiId가 같은 데이터의 mrRating을 저장. 
					document.querySelector('input[value="'+ sum + '"]').checked = true;
				}
				document.querySelector('#starSec').innerHTML = text;
				document.querySelector('#mrNum').value = data[i].mrNum;			//update에 사용하려고 input type hidden에 mrNum을 넣었다.
				document.querySelector('.btn12').style.display = 'none';		//리뷰 작성 시 작성 버튼 안보이도록 처리.
				document.querySelector('.review').innerHTML = '이미 리뷰를 작성하셨습니다';	
			}
			html += '</div></div>';
		}
		document.querySelector('.anime__details__review').innerHTML = html;
	})
}
function getCredits() {
	fetch('/credits/${param.id}')
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		if(!data.cast.length) {
			return;
		}
		for(let i=0; i<document.querySelectorAll('#product__item__pic').length; i++) {
			if(i > data.cast.length - 1) {
				break;
			}
			if(data.cast[i].profile_path) {	
				document.querySelectorAll('#product__item__pic')[i].style.backgroundImage = "url('https://image.tmdb.org/t/p/original" + data.cast[i].profile_path + "')";
			}else{				//poster_path 없을시, 이미지 없음 사진 추가
				document.querySelectorAll('#product__item__pic')[i].style.backgroundImage = "url('https://m.okbible.com/data/skin/mobile_ver2_default/images/common/noimage.gif')";
			}
			document.querySelectorAll('#product__item__pic')[i].setAttribute('onclick', 'location.href="/views/person-info?personId=' + data.cast[i].id + '"');		//배우 상세페이지 이동
			document.querySelectorAll('.character')[i].insertAdjacentHTML('afterBegin', '-' + data.cast[i].character + ' 역');
			document.querySelectorAll('.name')[i].insertAdjacentHTML('afterBegin', data.cast[i].name);			
		}	
	})
}
function selectButton(type) {
	document.querySelector('.btn12').style.display = 'none';
	if(type === 'INSERT') {
		document.querySelector('.anime__details__form').style.display='';
		document.querySelector('#reviewBtn').setAttribute('onclick', 'inputMovieReview("INSERT")');	//작성 시에는 매개변수로 insert를 줬다
	}else{
		fetch('/movie-review/user/' + type)
		.then(function(res) {
			return res.json();
		})
		.then(function(data) {
			document.querySelector('.anime__details__form').style.display='';
			document.querySelector('#mrContent').value = data.mrContent;
			document.querySelector('#reviewBtn').setAttribute('onclick', 'inputMovieReview("UPDATE")');	//최초 작성 이후는 수정만 가능하기 때문에 매개변수 update로
		})
	}
}
function inputMovieReview(type) {
	const mrContent = document.querySelector('#mrContent').value;
	if(!mrContent) {
		swal('리뷰 등록 실패!', '내용을 입력해주세요', 'error');
		return;
	}
	if(type === 'INSERT') {
		const param = {
				mrContent : mrContent.replace(/\n/g, "<br>"),	//엔터키 사용 해서 리뷰 입력시 엔터키 사용한 그대로 입력 되도록 처리.
				mrRating : sum									
		}
		fetch('/movie-review/' + ${param.id} , {
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
			if(data===1) {
				swal('리뷰 작성 성공!', '성공적으로 등록되었습니다.', 'success')
				.then(function() {						//sweet alert 사용시 then을 사용 안하면 뒤 코드들이 실행이 안 된다.
					mrContent.value = '';
					document.querySelector('.anime__details__form').style.display='none';
					getMovieReviews();
				});
			}else{
				swal('리뷰 작성 실패!', '댓글 작성은 1회만 가능합니다.', 'error');
			}
		})
	}else if(type === 'UPDATE') {
		const param = {
				mrContent : document.querySelector('#mrContent').value.replace(/\n/g, "<br>"),
				mrNum : document.querySelector('#mrNum').value,
				mrRating : sum
		}
		fetch('/movie-review/update', {
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
			if(data===1) {
				swal('수정 성공', '등록된 리뷰가 수정되었습니다.', 'success')
				.then(function() {
					mrContent.value = '';
					document.querySelector('.anime__details__form').style.display='none';
					getMovieReviews();
				})
			}else {
				swal('수정 실패', '양식을 확인해주세요', 'error');
			}
		})	
	}
}
function removeMovieReviews(mrNum) {
	fetch('/movie-review/' + mrNum , {
		method : 'DELETE'
	})
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		if(data===1) {
			swal('삭제 성공!', '등록하신 리뷰가 삭제되었습니다.', 'success')
			.then(function() {
				document.querySelector('.btn12').style.display = '';
				document.querySelector('.review').style.display ='none';
				document.querySelector('.anime__details__form').style.display='none';
				getMovieReviews();
			})
		}else{
			swal('삭제 실패!', '다시 시도해 주세요.', 'error');
		}
	})
}

const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))	//툴팁
</script>
</body>
</html>