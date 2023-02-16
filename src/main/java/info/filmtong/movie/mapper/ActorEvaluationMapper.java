package info.filmtong.movie.mapper;

import java.util.List;

import info.filmtong.movie.vo.actorEvaluation.ActorEvaluationVO;

public interface ActorEvaluationMapper {

	ActorEvaluationVO selectActorEvaluationByPiIdAndUiNum(ActorEvaluationVO actorEvaluation);
	
	List<ActorEvaluationVO> selectActorEvaluationByUiNum(ActorEvaluationVO actorEvaluation);
	
	int insertActorEvaluation(ActorEvaluationVO actorEvaluation);
	
	int deleteActorEvaluation(ActorEvaluationVO actorEvaluation);
}
