package info.filmtong.movie.mapper;

import java.util.List;

import info.filmtong.movie.vo.person.PersonFilmographyVO;

public interface PersonFilmographyMapper {

	List<PersonFilmographyVO> selectPersonMovieByPfId(int id);
	
	int insertPersonFilmography(PersonFilmographyVO personProfile);
}
