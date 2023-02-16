package info.filmtong.movie.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import info.filmtong.movie.mapper.MovieReviewMapper;
import info.filmtong.movie.vo.movieReview.MovieReviewVO;
import info.filmtong.movie.vo.userInfo.UserInfoVO;

@Service
public class MovieReviewService {

	@Autowired
	private MovieReviewMapper movieReviewMapper;
	
	public List<MovieReviewVO> selectMovieReviewById(int id) {
		return movieReviewMapper.selectMovieReviewById(id);
	}
	
	public PageInfo<MovieReviewVO> getMovieReviews(MovieReviewVO movieReview) {
		PageHelper.startPage(movieReview.getPage(), movieReview.getRows());
		return PageInfo.of(movieReviewMapper.selectMovieReviewByUiNum(movieReview));
	}
	
	public MovieReviewVO selectMovieReviewByMrNum(int mrNum) {
		return movieReviewMapper.selectMovieReviewByMrNum(mrNum);
	}
	
	public int insertMovieReview(MovieReviewVO movieReview, HttpSession session) {
		UserInfoVO userInfo = (UserInfoVO) session.getAttribute("userInfo");
		if(userInfo != null && movieReview.getMrContent() != "") {
			movieReview.setUiNum(userInfo.getUiNum());
			return movieReviewMapper.insertMovieReview(movieReview);
		}
		return 0;
	}
	
	public int updateMovieReview(MovieReviewVO movieReview) {
		return movieReviewMapper.updateMovieReview(movieReview);
	}

	public int deleteMovieReview(int mrNum) {
		return movieReviewMapper.deleteMovieReview(mrNum);
	}
}
