package info.filmtong.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import info.filmtong.movie.api.ApiTMDB;
import info.filmtong.movie.vo.credits.CreditsResponseVO;

@Service
public class CreditsService {
	
	@Value("${tmdb.credits.url}")
	private String creditsUrl;
	
	@Value("${tmdb.credits.url2}")
	private String creditsUrl2;
	
	@Autowired
	private ApiTMDB apiCreditsRest;
	
	public CreditsResponseVO getApiCreditsRest(int id) {
		return this.apiCreditsRest.getData(creditsUrl, creditsUrl2, id, CreditsResponseVO.class);
	}
	
}
