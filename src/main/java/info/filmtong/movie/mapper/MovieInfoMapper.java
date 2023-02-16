package info.filmtong.movie.mapper;

import java.util.List;

import info.filmtong.movie.vo.movie.MovieInfoVO;

public interface MovieInfoMapper {

	List<MovieInfoVO> selectMovieInfoList();
	
	List<MovieInfoVO> selectMovieInfoListById(int id);
	
}
