package info.filmtong.movie.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import info.filmtong.movie.service.UserInfoService;
import info.filmtong.movie.vo.KakaoLoginVO;
import info.filmtong.movie.vo.userInfo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping("/user-info/check/{uiId}")
	@ResponseBody
	public boolean existUserId(@PathVariable String uiId) {
		return userInfoService.existUserId(uiId);
	}
	
	@GetMapping("/user-infos/{uiNum}")
	@ResponseBody
	public UserInfoVO getUserInfo(@PathVariable("uiNum") int uiNum) {
		return userInfoService.getUserInfo(uiNum);
	}
	
	@GetMapping("/user-info/{uiId}")
	@ResponseBody
	public UserInfoVO getUserInfoById(@PathVariable("uiId") String uiId) {
		return userInfoService.getUserInfoById(uiId);
	}
	
	@GetMapping("/oauth")
	public String ouath(@RequestParam("code") String code, Model model, HttpSession session) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://kauth.kakao.com/oauth/token";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("grant_type", "authorization_code");
		map.add("client_id", "4ae3bd6dc9b6ea451508f49c102def61");
		map.add("redirect_uri", "http://localhost/oauth");
		map.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		
		ResponseEntity<KakaoLoginVO> res = restTemplate.postForEntity(uri, restTemplate, KakaoLoginVO.class);
		KakaoLoginVO kakaoLogin = res.getBody();
		uri = "https://kapi.kakao.com/v2/user/me";
		headers.set("Authorization", "Bearer " + kakaoLogin.getAccess_token());
		ResponseEntity<String> res2 = restTemplate.postForEntity(uri, request, String.class); 	//원래는 서비스에서 최소 3개의 서비스로 구현을 해야함.
		log.debug("body=>{}", res2.getBody());		
		return "views/kakao/oauth";
	}
	
	@PostMapping("/user-info")
	@ResponseBody
	public int insertUserInfo(@RequestBody UserInfoVO userInfo) {
		return userInfoService.insertUserInfo(userInfo);
	}
	
	@PostMapping("/user-infos/login")
	@ResponseBody
	public UserInfoVO login(@RequestBody UserInfoVO userInfo) {
		UserInfoVO loginUserInfo = userInfoService.login(userInfo);
		return loginUserInfo;
	}
	
	@GetMapping("/user-infos/logout")
	@ResponseBody
	public boolean logout() {		
		return userInfoService.logout();
	}
		
	@PostMapping("/user-infos/pwdCheck/{uiNum}")
	@ResponseBody
	public boolean passwordCheck(@RequestBody UserInfoVO userInfo, @PathVariable int uiNum) {
		return userInfoService.passwordCheck(userInfo, uiNum);
	}
	
	@PatchMapping("/user-infos/{uiNum}")
	@ResponseBody
	public boolean updateUserInfo(@RequestBody UserInfoVO userInfo, @PathVariable int uiNum) {
		userInfo.setUiNum(uiNum);
		return userInfoService.updateUserInfo(userInfo);
	}
	
	@DeleteMapping("/user-infos/{uiNum}")
	@ResponseBody
	public boolean deleteUserInfo(@PathVariable int uiNum) {
		return userInfoService.deleteUserInfo(uiNum);
	}
}
