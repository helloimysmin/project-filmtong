package info.filmtong.movie.vo.movie;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import info.filmtong.movie.vo.moviegenre.MovieGenreVO;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieInfoVO {

	private String budget;
	private int id;
	private String originalTitle;
	private String overview;
	private float popularity;
	private String posterPath;
	private String releaseDate;
	private long revenue;
	private int runtime;
	private String tagLine;
	private String title;
	private float voteAverage;
	private int voteCount;
	private int genreId;
	private String name;
	
}
