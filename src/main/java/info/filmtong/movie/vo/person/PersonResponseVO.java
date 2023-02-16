package info.filmtong.movie.vo.person;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonResponseVO {

	private int id;
	private String name;
	private String biography;
	private String birthday;
	private String deathday;
	private int gender;
	private String homepage;
	private String place_of_birth;
	private String profile_path;
	private PersonCastVO credits;
	
}
