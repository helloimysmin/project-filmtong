package info.filmtong.movie.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.filmtong.movie.mapper.UserInfoMapper;
import info.filmtong.movie.util.HttpSessionUtil;
import info.filmtong.movie.util.SHA256;
import info.filmtong.movie.vo.userInfo.UserInfoVO;

@Service
public class UserInfoService {
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	public boolean existUserId(String uiId) {
		if(userInfoMapper.selectUserInfoByUiId(uiId) == null) {
			return false;
		}
		return true;
	}
	
	public UserInfoVO getUserInfo(int uiNum) {
		UserInfoVO loginUserInfo = HttpSessionUtil.getUserInfo();
		UserInfoVO selectUserInfo = userInfoMapper.selectUserInfo(uiNum);
		if(selectUserInfo.getUiNum() == loginUserInfo.getUiNum()) {
			return selectUserInfo;
		}
		return null;
	}
	
	public UserInfoVO getUserInfoById(String uiId) {
		return userInfoMapper.selectUserInfoByUiId(uiId);	
	}
	
	public UserInfoVO login(UserInfoVO userInfo) {
		HttpSession session = HttpSessionUtil.getSession();
		userInfo.setUiPwd(SHA256.encode(userInfo.getUiPwd()));
		UserInfoVO loginUserInfo = userInfoMapper.selectUserInfoByIdAndPwd(userInfo);
		if(loginUserInfo != null) {
			session.setAttribute("userInfo", loginUserInfo);
			loginUserInfo.setUiPwd(null);
		}
		return loginUserInfo;
	}
	
	public boolean logout() {
		HttpSession session = HttpSessionUtil.getSession();
		HttpSessionUtil.getUserInfo();
		session.invalidate();
		return true;
	}
	
	public boolean passwordCheck(UserInfoVO userInfo, int uiNum) {
		UserInfoVO selectUserInfo = userInfoMapper.selectUserInfo(uiNum);
		if(selectUserInfo != null) {
			if(SHA256.encode(userInfo.getUiPwd()).equals(selectUserInfo.getUiPwd())) {
				return true;
			}
		}
		return false;
	}
	
	public int insertUserInfo(UserInfoVO userInfo) {
		userInfo.setUiPwd(SHA256.encode(userInfo.getUiPwd()));
		return userInfoMapper.insertUserInfo(userInfo);
	}
	
	public boolean updateUserInfo(UserInfoVO userInfo) {
		UserInfoVO loginUserInfo = HttpSessionUtil.getUserInfo();
		userInfo.setUiPwd(SHA256.encode(userInfo.getUiPwd()));
		if(loginUserInfo.getUiNum() == userInfo.getUiNum() && userInfoMapper.updateUserInfo(userInfo) == 1) {
			UserInfoVO tmpUserInfo = userInfoMapper.selectUserInfo(userInfo.getUiNum());
			HttpSessionUtil.setAttribute("userInfo", tmpUserInfo);
			return true;
		}
		return false;
	}
	
	public boolean deleteUserInfo(int uiNum) {
		HttpSession session = HttpSessionUtil.getSession();
		UserInfoVO loginUserInfo = HttpSessionUtil.getUserInfo();
		if(loginUserInfo.getUiNum() == uiNum && userInfoMapper.deleteUserInfo(uiNum) == 1) {
			session.invalidate();
			return true;
		}
		return false;
	}	
}
