package info.filmtong.movie.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import info.filmtong.movie.exception.AuthException;
import info.filmtong.movie.vo.userInfo.UserInfoVO;


public class HttpSessionUtil {

	public static HttpSession getSession() {
		 ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		 return attr.getRequest().getSession();
	}
	public static UserInfoVO getUserInfo() {
		HttpSession session = getSession();
		if(session.getAttribute("userInfo") == null) {
			throw new AuthException("로그인이 필요합니다.");
		}
		return (UserInfoVO) session.getAttribute("userInfo");
	}
	
	public static void setAttribute(String key, Object value) {
		HttpSession session = getSession();
		session.setAttribute(key, value);
	}
}
