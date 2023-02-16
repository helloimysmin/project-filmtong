package info.filmtong.movie.vo.movieReview;

import lombok.Data;

@Data
public class MovieReviewVO {

	private int mrNum;
	private String mrContent;
	private String credat;
	private String cretim;
	private String lmodat;
	private String lmotim;
	private int mrActive;
	private int mrRating;
	private int uiNum;
	private int id;
	private String uiId;
	private String uiNickname;
	private String uiProfileImg;
	private String title;
	private int page;
	private int rows;
}
