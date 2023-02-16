package info.filmtong.movie.vo.popular;

import lombok.Data;

@Data
public class PopularMovieInfoVO {

	private String adult;
	private String backdropPath;
	private int id;
	private String originalLanguage;
	private String originalTitle;
	private String overview;
	private float popularity;
	private String posterPath;
	private String releaseDate;
	private String title;
	private float voteAverage;
	private int voteCount;
	private String genreId;
	private String name;
	private int pageNum;
	private int pageSize;
}
