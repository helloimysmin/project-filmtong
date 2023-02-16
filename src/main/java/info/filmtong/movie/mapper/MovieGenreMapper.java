package info.filmtong.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import info.filmtong.movie.vo.moviegenre.MovieGenreResponseVO;
import info.filmtong.movie.vo.moviegenre.MovieGenreVO;

public interface MovieGenreMapper {

	MovieGenreResponseVO selectMovieGenreList();
	
	int insertGenreInfos(@Param("movieGenreList") List<MovieGenreVO> movieGenreList);
}
