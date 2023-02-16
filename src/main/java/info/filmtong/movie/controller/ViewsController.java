package info.filmtong.movie.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import info.filmtong.movie.anno.CheckAuth;

@Controller 
public class ViewsController {

	@GetMapping("/")
	public String home() {
		return "views/index"; 
	}

	@GetMapping("/views/**")
	public String goPage(HttpServletRequest request) {
		String uri = request.getRequestURI();
		return uri.substring(1);
	}
}

