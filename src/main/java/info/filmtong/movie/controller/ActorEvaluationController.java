package info.filmtong.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import info.filmtong.movie.service.ActorEvaluationService;
import info.filmtong.movie.vo.actorEvaluation.ActorEvaluationVO;

@RestController
public class ActorEvaluationController {

	@Autowired
	private ActorEvaluationService actorEvaluationService;
	
	@GetMapping("/actor-evaluation/{id}/{uiNum}")
	public ActorEvaluationVO getActorEvaluation(@PathVariable("id") int id, @PathVariable("uiNum") int uiNum, ActorEvaluationVO actorEvaluation) {
		actorEvaluation.setPiId(id);
		actorEvaluation.setUiNum(uiNum);
		return actorEvaluationService.selectActorEvaluationByPiIdAndUiNum(actorEvaluation);
	}
	
	@GetMapping("/actor-evaluation/page")
	public PageInfo<ActorEvaluationVO> getActorEvaluationByUiNum(ActorEvaluationVO actorEvaluation) {
		return actorEvaluationService.selectActorEvaluationByUiNum(actorEvaluation);
	}
	
	@PostMapping("/actor-evaluation")
	public int insertActorEvaluation(@RequestBody ActorEvaluationVO actorEvaluation) {
		return actorEvaluationService.insertActorEvaluation(actorEvaluation);
	}
	
	@DeleteMapping("/actor-evaluation")
	public int deleteActorEvaluation(@RequestBody ActorEvaluationVO actorEvaluation) {
		return actorEvaluationService.deleteActorEvaluation(actorEvaluation);
	}
}
