package info.filmtong.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import info.filmtong.movie.vo.boxoffice.BoxOfficeVO;

public interface BoxOfficeMapper {
	

	List<BoxOfficeVO> selectBoxOfficeList();
  
    int insertBoxOfficeList(@Param("boxOfficeList") List<BoxOfficeVO> paramList);
  
    int insertBoxOffice(BoxOfficeVO paramBoxOfficeVO);

}