package info.filmtong.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import info.filmtong.movie.service.MovieInfoService;
import info.filmtong.movie.vo.movie.MovieInfoVO;

@RestController
public class MovieInfoController {

	@Autowired
	private MovieInfoService movieInfoService;
	
	@GetMapping("/movie-infos/{id}")
	public List<MovieInfoVO> getMovieInfos(@PathVariable("id") int id) {
		return movieInfoService.getMovieInfo(id);
	}
	
}