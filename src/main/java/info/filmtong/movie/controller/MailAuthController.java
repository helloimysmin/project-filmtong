package info.filmtong.movie.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import info.filmtong.movie.service.EmailSendService;
import info.filmtong.movie.service.UserInfoService;
import info.filmtong.movie.vo.userInfo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MailAuthController {

	@Autowired
	private EmailSendService emailSendService;
													
	@GetMapping("/mail-checks/{uiEmail}")			
	public boolean sendEmail(@PathVariable("uiEmail") String uiEmail, HttpSession session) {
		String authKey = emailSendService.sendEmail(uiEmail);
		if(authKey != null) {
			session.setAttribute("authKey", authKey);
			session.setAttribute("userEmail", uiEmail);		
			return true;
		}
		return false;
	}
	
	@PostMapping("/email-checked/{checkNum}")
	public boolean checkEmail(@RequestBody UserInfoVO userInfo, @PathVariable("checkNum") String checkNum, HttpSession session) {
		String authKey = (String) session.getAttribute("authKey");
		String userEmail = (String) session.getAttribute("userEmail");			
		if(authKey != null && userEmail.equals(userInfo.getUiEmail())) {
			return authKey.equals(checkNum);		
		}
		return false;	 
	}
}
