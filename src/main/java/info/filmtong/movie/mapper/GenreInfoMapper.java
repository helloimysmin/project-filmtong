package info.filmtong.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import info.filmtong.movie.vo.genre.GenreInfoVO;
import info.filmtong.movie.vo.moviegenre.MovieGenreVO;

public interface GenreInfoMapper {

	List<MovieGenreVO> selectGenreInfoList();
	
	int insertGenreInfo(GenreInfoVO genreInfo);
	
	int insertGenreInfos(@Param("genreInfoList") List<GenreInfoVO> genreInfoList);
}
