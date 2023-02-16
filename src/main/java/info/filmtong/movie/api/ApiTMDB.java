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
