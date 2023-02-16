package info.filmtong.movie.vo.userEvaluation;

import lombok.Data;

@Data
public class UserEvaluationVO {

	private int uiNum;
	private int id;
	private String ueThumbsUp;
	private String uiId;
	private String uiNickName;
	private String title;
	private float voteAverage;
	private String releaseDate;
	private String originalTitle;
	private int page;
	private int rows;
}
