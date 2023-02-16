package info.filmtong.movie.mapper;

import java.util.List;

import info.filmtong.movie.vo.movieReview.MovieReviewVO;

public interface MovieReviewMapper {

	List<MovieReviewVO> selectMovieReviews(MovieReviewVO movieReview);
	
	List<MovieReviewVO> selectMovieReviewById(int id);
	
	List<MovieReviewVO> selectMovieReviewByUiNum(MovieReviewVO movieReview);
	
	MovieReviewVO selectMovieReviewByMrNum(int mrNum);
	
	int insertMovieReview(MovieReviewVO movieReview);
	
	int updateMovieReview(MovieReviewVO movieReview);
	
	int deleteMovieReview(int mrNum);
}
