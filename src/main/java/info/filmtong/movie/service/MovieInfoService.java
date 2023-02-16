package info.filmtong.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.filmtong.movie.mapper.MovieInfoMapper;
import info.filmtong.movie.vo.movie.MovieInfoVO;

@Service
public class MovieInfoService {

	@Autowired
	private MovieInfoMapper movieInfoMapper;
	
	public List<MovieInfoVO> getMovieInfoList() {
		return movieInfoMapper.selectMovieInfoList();
	}
		
	public List<MovieInfoVO> getMovieInfo(int id) {
		return movieInfoMapper.selectMovieInfoListById(id);
	}
	
}
