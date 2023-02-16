package info.filmtong.movie.vo.boxoffice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoxOfficeVO {

	private int rank;
	private String movieNm;
	private String movieCd;
	private int rankInten;
	private String rankOldAndNew;
	private String openDt;
	private long salesAmt;
	private float salesShare;
	private long salesInten;
	private float salesChange;
	private long salesAcc;
	private int audiCnt;
	private int audiInten;
	private float audiChange;
	private int audiAcc;
	private int scrnCnt;
	private int showCnt;
	private String targetDt;
}
