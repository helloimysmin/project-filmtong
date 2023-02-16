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
			message.setSubject("[FILMTONG] 회원 가입 인증 코드: ");
			
			StringBuffer key = new StringBuffer();
			Random random = new Random();
			for(int i=0; i<6; i++) {
				key.append(random.nextInt(10));
			}
			authKey = key.toString();
			String msg = "";
			msg += "<table align=\"center\" width=\"500\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:5px solid #EEE\">";
			msg += "<tbody><tr><td height=\"50\" bgcolor=\"#2f2f2f\" style=\"padding-left:15px\"><a href=\"http://www.filmtong.info\" "
					+ "style=\"font-weight:bold;font-size:15px;color:#FFF;text-decoration:none\" "
					+ "rel=\"noreferrer noopener\" target=\"_blank\">[FILMTONG] 회원 가입 인증 코드</a></td></tr>";
			msg += "<tr><td style=\"padding:20px\">";
			msg += "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">";
			msg += "<tbody><tr><td>";
			msg += "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-weight:bold\">";
			msg += "<tbody><tr><td valign=\"top\" height=\"30\" style=\"font-size:14px\">안녕하세요,</td></tr>";
			msg += "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family:'\"Mulish\", sans-serif';\">\n"
					+ "<tbody><tr><td valign=\"top\" height=\"30\" style=\"font-size:16px\">필통 회원가입에 필요한 인증번호입니다.</td></tr>";
			msg += "<tr><td valign=\"top\" height=\"40\" style=\"border-bottom:1px solid #EEE;line-height:20px;font-size:16px\">";
			msg += "아래 인증번호를 입력하여 이메일 인증을 완료해 주세요.</td></tr>";
			msg += "</tbody></table></td></tr>";
			msg += "<tr><td style=\"padding:20px 0;border-bottom:1px solid #EEE\">";
			msg += "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">";
			msg += "<colgroup><col width=\"50%\"><col width=\"70%\"></colgroup>";
			msg += "<tbody><tr height=\"25\"><td><p>인증번호 &nbsp; : &nbsp; " + authKey;
			msg += "</p></td></tr>";
			msg += "</tbody></table></td></tr>";
			msg += "<tr><td style=\"padding-top:20px;line-height:20px;font-size:12px;\">";
			msg += "FILMTONG을 이용해 주셔서 감사합니다.";
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