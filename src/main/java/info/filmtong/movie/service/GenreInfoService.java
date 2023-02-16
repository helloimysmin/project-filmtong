package info.filmtong.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.filmtong.movie.api.ApiMovieGenre;
import info.filmtong.movie.api.ApiPopularMovie;
import info.filmtong.movie.mapper.GenreInfoMapper;
import info.filmtong.movie.vo.genre.GenreInfoVO;
import info.filmtong.movie.vo.popular.PopularMovieInfoVO;

@Service
public class GenreInfoService {

	@Autowired
	private GenreInfoMapper genreInfoMapper;
	
	@Autowired
	private ApiPopularMovie apiPopularMovie;
	
	@Autowired
	private ApiMovieGenre apiMovieGenre;
	
	public int insertGenreInfos(List<GenreInfoVO> genreInfoList) {
		List<PopularMovieInfoVO> popularMovieInfoList = apiPopularMovie.getPopularMovies(50);
		for(int i=0; i<popularMovieInfoList.size(); i++) {
//			DB INSERT 사용구문.
//			List<Integer> genreIds = popularMovieInfoList.get(i).getGenreIds();
//				for(int genreId : genreIds) {
//					GenreInfoVO genreInfo = new GenreInfoVO();
//					genreInfo.setId(popularMovieInfoList.get(i).getId());
//					genreInfo.setGenreId(genreId);
//					genreInfoList.add(genreInfo);
//				}
		}
		return 	genreInfoMapper.insertGenreInfos(genreInfoList);
	}
	
}
