package info.filmtong.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import info.filmtong.movie.api.ApiTMDB;
import info.filmtong.movie.vo.person.PersonResponseVO;

@Service
public class PersonService {

	@Value("${tmdb.person.url}")
	private String personUrl;
	@Value("${tmdb.person.url2}")
	private String personUrl2;
	
	@Autowired
	private ApiTMDB apiTMDB;
	
	public PersonResponseVO getPersonInfo(int id) {
		return this.apiTMDB.getData(personUrl, personUrl2, id, PersonResponseVO.class);
	}

}
