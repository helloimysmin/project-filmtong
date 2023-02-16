package info.filmtong.movie.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import info.filmtong.movie.exception.AuthException;
import info.filmtong.movie.util.HttpSessionUtil;

@Configuration
public class AuthInterceptorForPage implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			HttpSessionUtil.getUserInfo();
		}catch(AuthException ae) {
			response.sendRedirect("/views/login");
			return false;
		}
		return true;
	}
}
