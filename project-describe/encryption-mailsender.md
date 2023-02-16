# SHA256 클래스를 이용한 유저 비밀번호 암호화

> SHA256

```java
package info.filmtong.movie.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

	private static final String SALT = "dlwpszjavbxj!@34";

	public static String encode(String source) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA256");
			md.update((SALT+source).getBytes());
			byte[] bytes = md.digest();
			StringBuffer sb = new StringBuffer();
			for(byte b : bytes) {
				sb.append(String.format("%02X", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
```


# 이메일 인증 번호 발송
  난수를 6번 발생시켜 6자리의 난수를 
  JAVA MAILSENDER를 이용하여 유저의 이메일로 전송
  그 6자리 난수를 세션에 저장 후 비교.

> EmailSendService

```java
package info.filmtong.movie.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSendService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Async
	public String sendEmail(String toAddress) {
		MimeMessage message = javaMailSender.createMimeMessage();
		String authKey = "";
		try {
			message.addRecipients(MimeMessage.RecipientType.TO, toAddress);
			message.setSubject("[FILTONG] 회원 가입 인증 코드: ");
			
			StringBuffer key = new StringBuffer();
			Random random = new Random();
			for(int i=0; i<6; i++) {
				key.append(random.nextInt(10));
			}
			authKey = key.toString();
			String msg = "";
			msg += "<table align=\"center\" width=\"500\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:5px solid #EEE\">";
			msg += "<tbody><tr><td height=\"50\" bgcolor=\"#2f2f2f\" style=\"padding-left:15px\"><a href=\"https://www.필통.com\" "
					+ "style=\"font-weight:bold;font-size:15px;color:#FFF;text-decoration:none\" "
					+ "rel=\"noreferrer noopener\" target=\"_blank\">[FILTONG] 회원 가입 인증 코드</a></td></tr>";
			msg += "<tr><td style=\"padding:20px\">";
			msg += "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">";
			msg += "<tbody><tr><td>";
			msg += "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-weight:bold\">";
			msg += "<tbody><tr><td valign=\"top\" height=\"30\" style=\"font-size:14px\">안녕하세요,</td></tr>";
			msg += "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family:'Dotum';\">\n"
					+ "<tbody><tr><td valign=\"top\" height=\"30\" style=\"font-size:17px\">필통 회원가입에 필요한 인증번호입니다.</td></tr>";
			msg += "<tr><td valign=\"top\" height=\"40\" style=\"border-bottom:1px solid #EEE;line-height:20px\">";
			msg += "아래 인증번호를 입력하여 이메일 인증을 완료해 주세요.</td></tr>";
			msg += "</tbody></table></td></tr>";
			msg += "<tr><td style=\"padding:20px 0;border-bottom:1px solid #EEE\">";
			msg += "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">";
			msg += "<colgroup><col width=\"50%\"><col width=\"70%\"></colgroup>";
			msg += "<tbody><tr height=\"25\"><td><p>인증번호 &nbsp; : &nbsp; " + authKey;
			msg += "</p></td></tr>";
			msg += "</tbody></table></td></tr>";
			msg += "<tr><td style=\"padding-top:20px;line-height:20px;font-size:12px;\">";
			msg += "FILTONG을 이용해 주셔서 감사합니다.";
			msg += "</td></tr>";
			msg += "</tbody></table></td></tr>";
			msg += "</tbody></table>";
			message.setText(msg, "UTF-8", "html");
			javaMailSender.send(message);
 		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return authKey;
	}

}
```


> MailAuthController
```java
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
```


> function emailNumCheck()
```javascript
function emailNumCheck() {
	const param = {
		uiEmail : document.querySelector('#uiEmail').value
	}
	fetch('/email-checked/' + document.querySelector('#uiCertification').value, {
			method : 'POST',
			headers : {
				'Content-type' : 'application/json'
			},
			body : JSON.stringify(param)
		})
		.then(function(res) {
			return res.json();
		})
		.then(function(data) {
			if (data === true) {
				swal('이메일 인증 성공', '이메일 인증이 완료되었습니다. sign-up 버튼을 눌러 회원가입을 완료해주세요.','success');
				isCheckedEmail = true;
			} else {
				swal('이메일 인증 실패', '이메일 인증이 실패하였습니다. 인증 번호 확인 후 다시 시도해주세요.', 'error');
				isCheckedEmail = false;
			}
		})
}
```



# 이메일 인증 예시
<img src="https://user-images.githubusercontent.com/115143371/216803013-29c7cb5d-a520-425c-aa6b-4e22279c0136.png">
