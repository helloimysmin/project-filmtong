package info.filmtong.movie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import info.filmtong.movie.interceptor.AuthInterceptorForPage;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	private static final String BASE_PATH;
	
	@Autowired
	private AuthInterceptorForPage authInterceptorForPage;
	
	static {
		String osName = System.getProperty("os.name");
		if (osName.toUpperCase().contains("WINDOW")) {
			BASE_PATH = "file:///C:";
		} else {
			BASE_PATH = "file:/";
		}
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/files/**").addResourceLocations(BASE_PATH + "/file-upload/");
	}

	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptorForPage) 
		.addPathPatterns("/user-infos/**")
		.excludePathPatterns("/user-infos/login"); 
	}
	 
}
