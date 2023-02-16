package info.filmtong.movie.mapper;

import java.util.List;

import info.filmtong.movie.vo.userInfo.UserInfoVO;

public interface UserInfoMapper {
	
	UserInfoVO selectUserInfo(int uiNum);

	UserInfoVO selectUserInfoByUiId(String uiId);
	
	UserInfoVO selectUserInfoByKakaoId(String kakaoId);
	
	UserInfoVO selectUserInfoByIdAndPwd(UserInfoVO userInfo);
	
	int insertUserInfo(UserInfoVO userInfo);
	
	int updateUserInfo(UserInfoVO userInfo);
	
	int deleteUserInfo(int uiNum);
}
