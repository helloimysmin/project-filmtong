package info.filmtong.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import info.filmtong.movie.mapper.ActorEvaluationMapper;
import info.filmtong.movie.vo.actorEvaluation.ActorEvaluationVO;

@Service
public class ActorEvaluationService {

	@Autowired
	private ActorEvaluationMapper actorEvaluationMapper;
	
	public ActorEvaluationVO selectActorEvaluationByPiIdAndUiNum(ActorEvaluationVO actorEvaluation) {
		return actorEvaluationMapper.selectActorEvaluationByPiIdAndUiNum(actorEvaluation);
	}
	
	public PageInfo<ActorEvaluationVO> selectActorEvaluationByUiNum(ActorEvaluationVO actorEvaluation) {
		PageHelper.startPage(actorEvaluation.getPage(), actorEvaluation.getRows());
		return PageInfo.of(actorEvaluationMapper.selectActorEvaluationByUiNum(actorEvaluation));
	}
	
	public int insertActorEvaluation(ActorEvaluationVO actorEvaluation) {
		return actorEvaluationMapper.insertActorEvaluation(actorEvaluation);
	}
	
	public int deleteActorEvaluation(ActorEvaluationVO actorEvaluation) {
		return actorEvaluationMapper.deleteActorEvaluation(actorEvaluation);
	}
}
