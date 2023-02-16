package info.filmtong.movie.mapper;

import java.util.List;

import info.filmtong.movie.vo.userEvaluation.UserEvaluationVO;

public interface UserEvaluationMapper {
	
	public List<UserEvaluationVO> selectUserEvaluationById(int id);
	
	public UserEvaluationVO selectUserEvaluationByIdAndUiId(UserEvaluationVO userEvaluation);
	
	public List<UserEvaluationVO> selectUserEvaluationByUiNum(UserEvaluationVO userEvaluation);

	public int insertUserEvaluation(UserEvaluationVO userEvaluation);
	
	public int deleteUserEvaluation(UserEvaluationVO userEvaluation);
}
