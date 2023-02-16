package info.filmtong.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.filmtong.movie.api.ApiMovieInfo;
import info.filmtong.movie.mapper.MovieInfoMapper;

@Service
public class TmdbMovieInfoService {

	@Autowired
	private ApiMovieInfo apiGetMovieInfo;
	
	@Autowired
	private MovieInfoMapper movieInfoMapper;
	
}
