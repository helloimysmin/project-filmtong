package info.filmtong.movie.vo.movieTrailer;

import java.util.List;

import lombok.Data;

@Data
public class MovieTrailerResultVO {

	private String id;
	private List<MovieTrailerVO> results;
}
