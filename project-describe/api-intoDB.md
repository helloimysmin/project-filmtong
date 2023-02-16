# 배우 이력 조회 

- **api restTemplate**
```java
package info.filmtong.movie.api;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApiTMDB {
	
	private static final Logger log = LoggerFactory.getLogger(info.filmtong.movie.api.ApiTMDB.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	public <T> T getData(String url, String url2, int id, Class<T> clazz) {
		try {
			URI uri = new URI(url + id + url2);
			ResponseEntity<T> response = this.restTemplate.getForEntity(uri, clazz);
			if (response.getStatusCode() == HttpStatus.OK)
				return (T) response.getBody();
			log.error("error=>{}", response);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}
```

- **DB에 배우 조회시 데이터가 존재하면 DB에서 select, 없다면 api 호출 후 DB에 인서트.**
```java
package info.filmtong.movie.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.filmtong.movie.vo.person.PersonCastVO;
import info.filmtong.movie.vo.person.PersonFilmographyVO;
import info.filmtong.movie.vo.person.PersonInfoVO;
import info.filmtong.movie.vo.person.PersonResponseVO;
import info.filmtong.movie.vo.person.PersonVO;

@Service
public class PersonTotalService {

	@Autowired
	private PersonInfoService personInfoService;		//PERSON_INFO TABLE 서비스.
	
	@Autowired
	private PersonFilmographyService personFilmographyService;		//PERSON_FILMOGRAPHY TABLE 서비스.
	
	@Autowired
	private PersonService personService;							//API 호출 서비스.
	
	
	public PersonResponseVO getPersonResponse(int id) {
		PersonInfoVO personInfo = personInfoService.selectPersonInfoByPiId(id);	//PERSON_INFO TABLE 조회.
		if(personInfo==null) {														//DB에 없다면 만족.
			int count = 0;												
			PersonResponseVO personResponse = personService.getPersonInfo(id);				//person id로 api에서 조회.			
			personInfoService.insertPersonInfoWithPersonResponse(personResponse);			//PERSON_INFO TABLE INSERT.
			PersonFilmographyVO personProfile = new PersonFilmographyVO();					//PERSON_FILMOGRAPHT TABLE VO
			personProfile.setPiId(personResponse.getId());
			List<PersonVO> personList = personResponse.getCredits().getCast();				
			for(PersonVO person : personList) {
				personProfile.setPfId(person.getId());
				personProfile.setPfTitle(person.getTitle());
				personProfile.setPfOriginalTitle(person.getOriginal_title());
				personProfile.setPfOverview(person.getOverview());
				personProfile.setPfPosterPath(person.getPoster_path());
				personProfile.setPfReleaseDate(person.getRelease_date());
				personProfile.setPfCharacter(person.getCharacter());
				count += personFilmographyService.insertPersonMovie(personProfile);			//PERSON_FILMOGRAPHY TABLE INSERT.
				if(count == 10) {															//10번 인서트하면 break.
					break;
				}
			}
			return personResponse;
		}
		PersonResponseVO personResult = new PersonResponseVO();
		List<PersonVO> personList = new ArrayList<>();
		PersonCastVO personCast = new PersonCastVO();
		personResult.setId(personInfo.getPiId());											
		personResult.setName(personInfo.getPiName());
		personResult.setBiography(personInfo.getPiBiography());
		personResult.setBirthday(personInfo.getPiBirthDay());
		personResult.setGender(personInfo.getPiGender());
		personResult.setHomepage(personInfo.getPiHomepage());
		personResult.setPlace_of_birth(personInfo.getPiPlaceOfBirth());
		personResult.setProfile_path(personInfo.getPiProfilePath());
		personResult.setDeathday(personInfo.getPiDeathDay());
		List<PersonFilmographyVO> personProfileList = personFilmographyService.selectPersonFilmography(personInfo.getPiId());			
		for(PersonFilmographyVO personProfile : personProfileList) {
			PersonVO person = new PersonVO();
			person.setId(personProfile.getPfId());
			person.setTitle(personProfile.getPfTitle());
			person.setOriginal_title(personProfile.getPfOriginalTitle());
			person.setOverview(personProfile.getPfOverview());
			person.setPoster_path(personProfile.getPfPosterPath());
			person.setRelease_date(personProfile.getPfReleaseDate());
			person.setCharacter(personProfile.getPfCharacter());
			personList.add(person);
		}
		personCast.setCast(personList);
		personResult.setCredits(personCast);
		return personResult;
	}
}

```
