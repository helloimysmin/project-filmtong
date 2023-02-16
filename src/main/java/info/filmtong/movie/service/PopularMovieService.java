package info.filmtong.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import info.filmtong.movie.mapper.PopularMovieMapper;
import info.filmtong.movie.vo.popular.PopularMovieInfoVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PopularMovieService {

	@Autowired
	private PopularMovieMapper popularMovieMapper;
	
	public List<PopularMovieInfoVO> selectPopularMovieList(PopularMovieInfoVO popularMovie) {
		return popularMovieMapper.selectPopularMovieList(popularMovie);
	}
	
	public List<PopularMovieInfoVO> selectRecommendMovies(PopularMovieInfoVO popularMovie) {
		return popularMovieMapper.selectRecommendMovies(popularMovie);
	}
	
	public List<PopularMovieInfoVO> selectRecommendMoviesRandom(PopularMovieInfoVO popularMovie) {
		return popularMovieMapper.selectRecommendMoviesRandom(popularMovie);
	}
	
	public List<PopularMovieInfoVO> selectPopularMoviesRandom(PopularMovieInfoVO popularMovie) {
		return popularMovieMapper.selectPopularMoviesRandom(popularMovie);
	}

	public List<PopularMovieInfoVO> selectHofMovies(PopularMovieInfoVO popularMovie) {
		return popularMovieMapper.selectHofMovies(popularMovie);
	}
	
	public List<PopularMovieInfoVO> selectPopularMovieById(int id) {	//아이디로 선택??
		return popularMovieMapper.selectPopularMovieListById(id);
	}
	
	public PageInfo<PopularMovieInfoVO> selectPopularMoviePage(PopularMovieInfoVO popularMovieInfo) {		//인기영화
		PageHelper.startPage(popularMovieInfo.getPageNum(), popularMovieInfo.getPageSize());
		return PageInfo.of(popularMovieMapper.selectPopularMovieList(popularMovieInfo));

	}
	
	public PageInfo<PopularMovieInfoVO> selectRecommendMoviePage(PopularMovieInfoVO popularMovieInfo) {		//추천영화 
		PageHelper.startPage(popularMovieInfo.getPageNum(), popularMovieInfo.getPageSize());
		return PageInfo.of(popularMovieMapper.selectRecommendMovies(popularMovieInfo));

	}
	
	public PageInfo<PopularMovieInfoVO> selectHofMoviePage(PopularMovieInfoVO popularMovieInfo) {		//명예의 전당
		PageHelper.startPage(popularMovieInfo.getPageNum(), popularMovieInfo.getPageSize());
		return PageInfo.of(popularMovieMapper.selectHofMovies(popularMovieInfo));

	}

	public int insertPopularMovies(List<PopularMovieInfoVO> popularMovieInfo) {
		return popularMovieMapper.insertPopularMovies(popularMovieInfo);
	}
	
	public int insertPopularMovie(PopularMovieInfoVO popularMovieInfo) {
		return popularMovieMapper.insertPopularMovie(popularMovieInfo);
	}
	
	
}