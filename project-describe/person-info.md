# 배우들의 정보, 필모그래피를 확인 할 수 있는 페이지 구성

- **배우 정보 조회**
```javascript
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
```

- **배우에 대한 유저의 좋아요 CRUD**
```javascript
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
```
<img src="https://user-images.githubusercontent.com/115143371/216808177-2dc10f8b-31fb-41ca-a96f-6a24f55484da.png">

- **배우 필모그래피 mouse over시 오버뷰 확인 가능하도록 구성**
```javascript
const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))	//툴팁
```
<img src="https://user-images.githubusercontent.com/115143371/216808226-06b9c28a-bbd1-402b-be2d-777f53a2ccdd.png">
