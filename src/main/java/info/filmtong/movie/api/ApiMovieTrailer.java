package info.filmtong.movie.api;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiMovieTrailer {

	@Autowired
	private RestTemplate restTemplate;
	
	public <T> T getData(String url, String url2, int id, Class<T> clazz) {
		try {
			URI uri = new URI(url+id+url2);
			ResponseEntity<T> response = restTemplate.getForEntity(uri, clazz);
			return response.getBody();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}
