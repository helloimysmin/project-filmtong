package info.filmtong.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import info.filmtong.movie.service.MovieTrailerService;
import info.filmtong.movie.vo.movieTrailer.MovieTrailerResultVO;
import info.filmtong.movie.vo.popular.PopularMovieInfoVO;

@RestController
public class MovieTrailerController {

	@Autowired
	private MovieTrailerService movieTrailerService;
	
	@GetMapping("/movie-trailer/{id}")
	public MovieTrailerResultVO getMovieTrailer(@PathVariable int id) {
		return movieTrailerService.getMovieTrailer(id);
	}
}
