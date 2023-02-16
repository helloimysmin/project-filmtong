package info.filmtong.movie.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import info.filmtong.movie.service.UserEvaluationService;
import info.filmtong.movie.vo.movieReview.MovieReviewVO;
import info.filmtong.movie.vo.userEvaluation.UserEvaluationVO;

@RestController
public class UserEvaluationController {

	@Autowired
	private UserEvaluationService userEvaluationService;
	
	@GetMapping("/user-evaluation/{id}")
	public List<UserEvaluationVO> getUserEvaluationById(@PathVariable int id) {
		return userEvaluationService.selectUserEvaluationById(id);
	}
	
	@GetMapping("/user-evaluation")
	public UserEvaluationVO getUserEvaluationByIdAndUiId(UserEvaluationVO userEvaluation) {
		return userEvaluationService.selectUserEvaluationByIdAndUiId(userEvaluation);
	}
	
	@GetMapping("/user-evaluation/page")
	public PageInfo<UserEvaluationVO> getMovieReviewsPage(UserEvaluationVO userEvaluation) {
		return userEvaluationService.selectUserEvaluationByUiNum(userEvaluation);
	}
	
	@PostMapping("/user-evaluation")
	public int insertUserEvaluation(@RequestBody UserEvaluationVO userEvaluation) {
		return userEvaluationService.insertUserEvaluation(userEvaluation);
	}
	
	@DeleteMapping("/user-evaluation")
	public int deleteUserEvaluation(@RequestBody UserEvaluationVO userEvaluation) {
		return userEvaluationService.deleteUserEvaluation(userEvaluation);
	}
}
