package info.filmtong.movie.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import info.filmtong.movie.anno.CheckAuth;
import info.filmtong.movie.service.MovieReviewService;
import info.filmtong.movie.vo.movieReview.MovieReviewVO;
import info.filmtong.movie.vo.userInfo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MovieReviewController {

	@Autowired
	private MovieReviewService movieReviewService;
	
	@GetMapping("/movie-review/{id}")
	public List<MovieReviewVO> getMovieReviewsById(@PathVariable("id") int id) {
		return movieReviewService.selectMovieReviewById(id);
	}
	
	@GetMapping("/movie-reviews/page")
	public PageInfo<MovieReviewVO> getMovieReviewsPage(MovieReviewVO moviewReview) {
		return movieReviewService.getMovieReviews(moviewReview);
	}
	
	@GetMapping("/movie-review/user/{mrNum}")
	public MovieReviewVO getUserReview(@PathVariable("mrNum") int mrNum) {
		return movieReviewService.selectMovieReviewByMrNum(mrNum);
	}
	
	@PostMapping("/movie-review/{id}")
	public int insertMovieReview(@RequestBody MovieReviewVO movieReview, HttpSession session, @PathVariable int id) {
		movieReview.setId(id);
		return movieReviewService.insertMovieReview(movieReview, session);
	}
	
	@PatchMapping("/movie-review/update")
	public int updateMovieReview(@RequestBody MovieReviewVO movieReview) {
		return movieReviewService.updateMovieReview(movieReview);
	}
	
	@DeleteMapping("/movie-review/{mrNum}")
	public int deleteMovieReview(@PathVariable("mrNum") int mrNum) {
		return movieReviewService.deleteMovieReview(mrNum);
	}
}
