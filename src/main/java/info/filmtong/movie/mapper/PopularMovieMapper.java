package info.filmtong.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import info.filmtong.movie.vo.popular.PopularMovieInfoVO;

public interface PopularMovieMapper {

	List<PopularMovieInfoVO> selectPopularMovieList(PopularMovieInfoVO popularMovie);
	
	List<PopularMovieInfoVO> selectRecommendMovies(PopularMovieInfoVO popularMovie);
	
	List<PopularMovieInfoVO> selectRecommendMoviesRandom(PopularMovieInfoVO popularMovie);
	
	List<PopularMovieInfoVO> selectPopularMoviesRandom(PopularMovieInfoVO popularMovie);
	
	List<PopularMovieInfoVO> selectHofMovies(PopularMovieInfoVO popularMovie);
	
	List<PopularMovieInfoVO> selectPopularMovieListById(int id);
	
	int insertPopularMovies(@Param("popularMovieInfoList") List<PopularMovieInfoVO> popularMovieInfo);
	
	int insertPopularMovie(PopularMovieInfoVO popularMovieInfo);
}
