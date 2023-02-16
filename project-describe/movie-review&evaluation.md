# 영화에 대한 유저들의 리뷰 조회

```javascript
function getMovieReviews() {
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
```



# 영화에 대한 유저들의 리뷰 CRUD

```javascript
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
				location.href='/views/movie-trailer?id=${param.id}';
			})
		}else{
			swal('삭제 실패!', '다시 시도해 주세요.', 'error');
		}
	})
}
```

<img src="https://user-images.githubusercontent.com/115143371/216807038-500adafc-fdfe-4fc5-9f90-0a628e46e5a7.png">


# 유저 평점 및 좋아요
```javascript
let sum = 0;
function starRating(num){			//리뷰 작성할 때 별을 누르면 num에 담긴 숫자만큼 #starSec 태그에 별이 찍히도록 하는 function
	let html = '';
	sum = num;
	for(let i=0; i<sum; i++) {
		html += '<span class="starRating">★</span>';
	}
	document.querySelector('#starSec').innerHTML = html;
}

function getTotalThumbsUp() {
	fetch('/user-evaluation/${param.id}')
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		document.querySelector('#totalThumbsUp').innerHTML = '총 ' + data.length + '명의 유저가 좋아요를 클릭하셨습니다.';
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
```

<img src="https://user-images.githubusercontent.com/115143371/216807149-a534dbd6-4cec-43f4-a8a7-3a7648a74a98.png">
