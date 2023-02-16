package info.filmtong.movie.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import info.filmtong.movie.service.UserInfoService;
import info.filmtong.movie.util.HttpSessionUtil;
import info.filmtong.movie.vo.userInfo.UserInfoVO;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class AuthInterceptorForAPI implements HandlerInterceptor{

	private final UserInfoService userInfoService;

	private static final boolean isLocal;
	static {
		String osname = System.getProperty("os.name");
		if(osname.toUpperCase().contains("WINDOW")) {
			isLocal = true;
		}else {
			isLocal = false;
		}
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

//		if(isLocal && !HttpSessionUtil.isLogin()) {
//			UserInfoVO userInfo = new UserInfoVO();
//			userInfo.setUsiEmail("a@naver.com");
//			userInfo.setUsiPwd("dhrnjstlr12!");
//			UserInfoVO userInfoSession = userInfoService.selectUserInfo(userInfo);
//			userInfoSession.setUsiPwd(null);
//			HttpSessionUtil.setAttribute("userInfo", userInfoSession);
//		}
//		HttpSessionUtil.getUserInfo();
		return true;
	}
}
