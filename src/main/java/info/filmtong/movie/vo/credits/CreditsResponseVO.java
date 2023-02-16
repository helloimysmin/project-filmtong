package info.filmtong.movie.vo.credits;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditsResponseVO {

	private List<CreditsVO> cast;
	
}
