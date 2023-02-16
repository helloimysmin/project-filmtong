# 메인 페이지
- TMDB API를 사용하여 DB에 저장한 1000여개의 영화를 fetch를 사용하여 비동기 방식으로 데이터를 처리했습니다


- 상단에 jquery owl-slider를 사용하여 추천 영화 8개를 슬라이드가 되도록 구성했습니다.
```javascript
  function getMainMovies() {
    fetch('/popular-moviesRandom')
    .then(function(res) {
      return res.json();
    })
    .then(function(data){
      const heroItems = document.querySelectorAll('.hero__items');
      const heroLabels = document.querySelectorAll('.hero__text .label');
      const heroH2 = document.querySelectorAll('.hero__text h2');
      const heroP = document.querySelectorAll('.hero__text p');
      for(let i=0; i<heroItems.length; i++) {
        heroItems[i].style.backgroundImage="url('https://image.tmdb.org/t/p/original" + data[i].backdropPath + "')";
        heroItems[i].setAttribute('onclick',"location.href=\'/views/movie-trailer?id=" + data[i].id + "'"); 
        heroLabels[i].innerHTML = data[i].name;
        heroH2[i].innerHTML = data[i].title;
      }
    })
  }
```
  

- DB에 저장한 1000여개의 영화를 ORDER BY 쿼리를 사용하여 

  RECOMMEND / POPULAR / HALL OF FAME 세 가지의 탭으로 나누어 메인 페이지를 구성했습니다.
  
  
- PopularMovieController
  ```java
  @RestController
  @Slf4j
  public class PopularMovieController {

  @Autowired
  private PopularMovieService popularMovieService;

  @GetMapping("/popular-movies")
  public List<PopularMovieInfoVO> getPopularMovies(PopularMovieInfoVO popularMovieInfo) {
    return popularMovieService.selectPopularMovieList(popularMovieInfo);
  }

  @GetMapping("/recommend-movies")
  public List<PopularMovieInfoVO> getRecommendMovies(PopularMovieInfoVO popularMovieInfo) {		
    return popularMovieService.selectRecommendMovies(popularMovieInfo);
  }

  @GetMapping("/popular-moviesRandom")
  public List<PopularMovieInfoVO> getPopularMoviesRandom(PopularMovieInfoVO popularMovieInfo) {			//메인화면 배너
    return popularMovieService.selectPopularMoviesRandom(popularMovieInfo);
  }

  @GetMapping("/recommend-moviesRandom")
  public List<PopularMovieInfoVO> getRecommendMoviesRandom(PopularMovieInfoVO popularMovieInfo) {			//메인화면 추천영화
    return popularMovieService.selectRecommendMoviesRandom(popularMovieInfo);
  }

  @GetMapping("/hof-movies")																				//메인화면 명예의 전당
  public List<PopularMovieInfoVO> getHofMovies(PopularMovieInfoVO popularMovieInfo) {
    return popularMovieService.selectHofMovies(popularMovieInfo);
  }

  @GetMapping("/recommendMovies-scroll")
  public PageInfo<PopularMovieInfoVO> selectRecommendMoviePage(PopularMovieInfoVO popularMovieInfo) {		//추천영화 목록
    return popularMovieService.selectRecommendMoviePage(popularMovieInfo);
  }

  @GetMapping("/hofMovies-scroll")
  public PageInfo<PopularMovieInfoVO> selectHofMoviePage(PopularMovieInfoVO popularMovieInfo) {			//명예의 전당 목록
    return popularMovieService.selectHofMoviePage(popularMovieInfo);
  }

  @GetMapping("/popularMovies-scroll")
  public PageInfo<PopularMovieInfoVO> selectPopularMoviePage(PopularMovieInfoVO popularMovieInfo) {		//인기영화 목록
    return popularMovieService.selectPopularMoviePage(popularMovieInfo);
  }

  @GetMapping("/popular-movies/{id}")
  public List<PopularMovieInfoVO> getPopularMovies(@PathVariable int id) {								//movie-trailer 상세 정보
    return popularMovieService.selectPopularMovieById(id);
  }


- function getRecommendMovies()

  ```javascript
  function getRecommendMovies() {
    fetch('/recommend-moviesRandom')
    .then(function(res) {
      return res.json();
    })
    .then(function(data){
      const itemPics = document.querySelectorAll('.trending__product .product__item__pic');
      const comments = document.querySelectorAll('.trending__product .product__item .comment');
      const views = document.querySelectorAll('.trending__product .product__item__pic .view');
      const itemMvTitle = document.querySelectorAll('.trending__product .product__item__text .mvTitle');
      const itemLitags = document.querySelectorAll('.trending__product .product__item__text ul li');
      for(let i=0; i<itemPics.length; i++) {
        itemPics[i].style.backgroundImage = "url('https://image.tmdb.org/t/p/original" + data[i].posterPath + "')";
        itemPics[i].setAttribute('onclick', "location.href=\'/views/movie-trailer?id=" + data[i].id + "'");
        comments[i].innerHTML = data[i].name;
        views[i].innerHTML = data[i].voteAverage + '점';
        itemMvTitle[i].innerHTML = data[i].title;
        itemLitags[i].innerHTML = data[i].originalTitle;
      }
    })
  }
  



- 상세 뷰 화면입니다.
<img alt="main-page" src="https://user-images.githubusercontent.com/115143371/216736175-1c91418a-298b-462c-ac5a-2b014189969a.png">
<img alt="main-page" src="https://user-images.githubusercontent.com/115143371/216736345-d5667476-f654-475d-b04f-c9b568b764a2.png">
<img alt="main-page" src="https://user-images.githubusercontent.com/115143371/216736474-8b158193-a048-492f-90df-c3deab515fc6.png">
<img alt="main-page" src="https://user-images.githubusercontent.com/115143371/216736345-d5667476-f654-475d-b04f-c9b568b764a2.png">
