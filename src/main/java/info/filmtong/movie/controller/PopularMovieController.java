package info.filmtong.movie.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import info.filmtong.movie.service.PopularMovieService;
import info.filmtong.movie.vo.popular.PopularMovieInfoVO;
import lombok.extern.slf4j.Slf4j;

@RestController
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
	

	
}
