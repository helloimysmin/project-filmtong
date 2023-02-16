package info.filmtong.movie.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import info.filmtong.movie.api.ApiTMDB;
import info.filmtong.movie.vo.movieTrailer.MovieTrailerResultVO;
import info.filmtong.movie.vo.movieTrailer.MovieTrailerVO;

@Service
@PropertySource("classpath:env.properties")
public class MovieTrailerService {
	
	@Value("${tmdb.trailer.url}")
	private String trailerUrl;
	
	@Value("${tmdb.trailer.url2}")
	private String trailerUrl2;
	
	@Autowired
	private ApiTMDB apiTMDB;

	public MovieTrailerResultVO getMovieTrailer(int id) {
		return apiTMDB.getData(trailerUrl, trailerUrl2, id, MovieTrailerResultVO.class);
	} 
}
