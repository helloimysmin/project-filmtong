package info.filmtong.movie.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import info.filmtong.movie.mapper.UserEvaluationMapper;
import info.filmtong.movie.vo.userEvaluation.UserEvaluationVO;
import info.filmtong.movie.vo.userInfo.UserInfoVO;

@Service
public class UserEvaluationService {

	@Autowired
	private UserEvaluationMapper userEvaluationMapper;
	
	public List<UserEvaluationVO> selectUserEvaluationById(int id) {
		return userEvaluationMapper.selectUserEvaluationById(id);
	}
	
	public UserEvaluationVO selectUserEvaluationByIdAndUiId(UserEvaluationVO userEvaluation) {
		return userEvaluationMapper.selectUserEvaluationByIdAndUiId(userEvaluation);
	}
	
	public PageInfo<UserEvaluationVO> selectUserEvaluationByUiNum(UserEvaluationVO userEvaluation) {
		PageHelper.startPage(userEvaluation.getPage(), userEvaluation.getRows());
		return PageInfo.of(userEvaluationMapper.selectUserEvaluationByUiNum(userEvaluation));
	}

	public int insertUserEvaluation(UserEvaluationVO userEvaluation) {
		return userEvaluationMapper.insertUserEvaluation(userEvaluation);
	}	
	
	public int deleteUserEvaluation(UserEvaluationVO userEvaluation) {
		return userEvaluationMapper.deleteUserEvaluation(userEvaluation);
	}
	
}
