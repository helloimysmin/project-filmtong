package info.filmtong.movie.vo.popular;

import java.util.List;

import lombok.Data;

@Data
public class PopularMovieResponseVO {

	private int page;
	private List<PopularMovieInfoVO> results;
	private int total_pages;
	private int total_results;
}
