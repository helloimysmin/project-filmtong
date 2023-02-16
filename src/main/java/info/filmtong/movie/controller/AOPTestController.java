package info.filmtong.movie.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import info.filmtong.movie.anno.CheckAuth;
import info.filmtong.movie.vo.userInfo.UserInfoVO;

@Controller
public class AOPTestController {

	
	@GetMapping("/json1")
	@CheckAuth
	@ResponseBody
	public ResponseEntity<String> sendJson1(@ModelAttribute UserInfoVO userInfo) {
		return new ResponseEntity<>("json1", HttpStatus.OK);
	}
	
//	session에 userInfo이쓴ㄴ지 없늕 ㅣ체크
	
	@GetMapping("/json2")
	@ResponseBody
	public ResponseEntity<String> sendJson2() {
		return new ResponseEntity<>("json2",HttpStatus.OK);
	}
//	 얜 안함
}
