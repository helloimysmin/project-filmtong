package info.filmtong.movie.vo.moviegenre;

import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MovieGenreResponseVO {

	private List<MovieGenreVO> genres;
	
}
