package info.filmtong.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.filmtong.movie.mapper.PersonFilmographyMapper;
import info.filmtong.movie.vo.person.PersonFilmographyVO;

@Service
public class PersonFilmographyService {

	@Autowired
	private PersonFilmographyMapper personFilmographyMapper;
	
	public List<PersonFilmographyVO> selectPersonFilmography(int id) {
		return personFilmographyMapper.selectPersonMovieByPfId(id);
	}
	
	public int insertPersonMovie(PersonFilmographyVO personFilmography) {
		return personFilmographyMapper.insertPersonFilmography(personFilmography);
	}
}
