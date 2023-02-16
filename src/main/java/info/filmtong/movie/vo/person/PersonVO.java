package info.filmtong.movie.vo.person;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonVO {

	private int id;
	private String title;
	private String original_title;
	private String overview;
	private String poster_path;
	private String release_date;
	private String character;
}
