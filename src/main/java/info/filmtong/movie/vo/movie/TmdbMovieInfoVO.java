package info.filmtong.movie.vo.movie;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import info.filmtong.movie.vo.moviegenre.MovieGenreVO;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbMovieInfoVO {

	private String adult;
	private CollectionInfoVO belongs_to_collection;
	private String budget;
	private int id;
	private String original_title;
	private String overview;
	private float popularity;
	private String poster_path;
	private String release_date;
	private long revenue;
	private int runtime;
	private String tagline;
	private String title;
	private float vote_average;
	private int vote_count;
	
}
