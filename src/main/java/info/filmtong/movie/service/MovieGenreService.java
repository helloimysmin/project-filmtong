package info.filmtong.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.filmtong.movie.mapper.MovieGenreMapper;
import info.filmtong.movie.vo.moviegenre.MovieGenreResponseVO;
import info.filmtong.movie.vo.moviegenre.MovieGenreVO;

@Service
public class MovieGenreService {

	@Autowired
	private MovieGenreMapper movieGenreMapper;
	
	public MovieGenreResponseVO selectMovieGenreList() {
		return movieGenreMapper.selectMovieGenreList();
	}
	
	public int insertMovieGenres(List<MovieGenreVO> movieGenreList) {
		return movieGenreMapper.insertGenreInfos(movieGenreList);
	}
}
