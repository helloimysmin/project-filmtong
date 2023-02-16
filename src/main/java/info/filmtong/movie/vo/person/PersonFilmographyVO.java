package info.filmtong.movie.vo.person;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PersonFilmographyVO {

	private int pfId;
	private String pfTitle;
	private String pfOriginalTitle;
	private String pfOverview;
	private String pfPosterPath;
	private String pfReleaseDate;
	private String pfCharacter;
	private int piId;
	
}
