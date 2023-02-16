package info.filmtong.movie.vo.userInfo;

import lombok.Data;

@Data
public class UserInfoVO {

	private int uiNum;
	private String uiId;
	private String uiNickname;
	private String uiPwd;
	private String uiEmail;
	private String uiProfileImg;
	private int mrNum;
	private String mrContent;
	private String mrRating;
	private String credat;
	private String cretim;
	private String lmodat;
	private String lmotim;
	private String ueThumbsUp;
}
