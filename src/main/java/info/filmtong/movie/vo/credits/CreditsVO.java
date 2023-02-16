package info.filmtong.movie.vo.credits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditsVO {

	private int gender;
	private int id;
	private String name;
	private String profile_path;
	private String character;
	private String homepage;
}
