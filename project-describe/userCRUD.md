# 세션을 사용한 로그인 기능 및 로그아웃 <br>회원 가입에 필요한 validation check, 회원 정보 수정, 탈퇴 (CRUD)

> HTTPSessionUtil
```java
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
```

> UserInfoService
```java
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
```


> UserInfoController

```java
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
```

> function login(), logout()
```javascript
function login() {
	const param = {
			uiId : document.querySelector('#uiId').value,
			uiPwd : document.querySelector('#uiPwd').value
	}
	fetch('/user-infos/login', {
		method : 'POST',
		headers : {
			'Content-type' : 'application/json'
		},
		body : JSON.stringify(param)
	})
	.then(function(res) {
		return res.text();		//login은 id,pwd로 selectOne. nullable. null이면 JSON으로 파싱이 안됨. 따라서 json.text();
	})
	.then(function(data) {
		if(data) {				//selectOne이 null이 아니다 => 조건식 만족 => JSON 파싱 후 로그인 처리.
			data = JSON.parse(data);		
			swal('환영합니다.', data.uiNickname + '님 어서오세요!', 'success')
			.then(function() {
				location.href='';	
			})
		}else {
			swal('로그인 실패', '회원 정보가 올바르지 않습니다.', 'error');
		}
	})
}
function logOut(){
	fetch('/user-infos/logout')
	.then(function(res){
		return res.json();
	})
	.then(function(data){
		if(data===true){
			swal('로그아웃되었습니다..', '고객님 잘가세요!', 'success')
			.then(function(){
				location.href= '';
			})
		}else{
			swal('어허', '로그인을 해야죠?', 'error');
		}	
	})
}
```


> insert 
<img src="https://user-images.githubusercontent.com/115143371/216803841-e369eaa4-837b-4455-8608-5fbb5a34f53e.png">
