# 무비 트레일러 페이지 구성

- **tmdb api를 사용하여 영화 정보 데이터를 받고 영화 예고편이 있으면 owl-slider를 사용하여 예고편 및 관련 영상들을 슬라이드 시켰습니다.**

```javascript
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
      html += '<div class="movie-trailer owl-carousel owl-theme">';		
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
```




- **영화 정보**
```javascript
function getMovieInfos() {
	fetch('/popular-movies/' + ${param.id})
	.then(function(res) {
		return res.json(); 
	})
	.then(function(data) {
		let html = '';
		for(let i=0; i<data.length; i++) {
			document.querySelector('.overviews').insertAdjacentHTML('afterbegin', data[i].overview); 
			document.querySelector('#movieTitle').insertAdjacentHTML('afterbegin' ,data[i].title + '  (' + data[i].originalTitle + ')');
			document.querySelector('#vote_average').insertAdjacentHTML('afterbegin', data[i].voteAverage + '점');
			document.querySelector('#release_date').insertAdjacentHTML('afterbegin', data[i].releaseDate);
			document.querySelector('#genre').insertAdjacentHTML('afterbegin', data[i].name);
			document.querySelector('#empty-overview').style.backgroundImage = "url('https://image.tmdb.org/t/p/original" + data[i].backdropPath + "')";
			if(!(data[i].overview)) {
				document.querySelector('.overviews').insertAdjacentHTML('afterbegin', '<span>유저님들의 리뷰로 상세 정보를 채워주세요!</span>');
			}
		}
	})
}
```


- **출연 배우**
```javascript
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
			}else{
				document.querySelectorAll('#product__item__pic')[i].style.backgroundImage = "url('https://m.okbible.com/data/skin/mobile_ver2_default/images/common/noimage.gif')";
			}
			document.querySelectorAll('#product__item__pic')[i].setAttribute('onclick', 'location.href="/views/person-info?personId=' + data.cast[i].id + '"');
			document.querySelectorAll('.character')[i].insertAdjacentHTML('afterBegin', '-' + data.cast[i].character + ' 역');
			document.querySelectorAll('.name')[i].insertAdjacentHTML('afterBegin', data.cast[i].name);			
		}	
	})
}
function selectButton(type) {
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
```



# 무비 트레일러 페이지 구성
<img src="https://user-images.githubusercontent.com/115143371/216805899-f99397ab-0676-487f-81da-f86e569de0d5.png">
