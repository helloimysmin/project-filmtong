package info.filmtong.movie.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
public class RESTApiBoxOffice {

	private static final Logger log = LoggerFactory.getLogger(info.filmtong.movie.rest.RESTApiBoxOffice.class);

	@Autowired
	RestTemplate restTemplate;

	public <T> T getData(String url, Class<T> clazz, Map<String, Object> param) {
		try {
			if (param != null) {
				if (url.indexOf("?") == -1)
					url = String.valueOf(url) + "?";
				Iterator<String> it = param.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					Object value = param.get(key);
					url = String.valueOf(url) + "&" + key + "=" + value;
				}
			}
			URI uri = new URI(url);
			ResponseEntity<T> response = this.restTemplate.getForEntity(uri, clazz);
			if (response.getStatusCode() == HttpStatus.OK)
				return (T) response.getBody();
			log.error("error=>{}", response);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String generateUrl(String url, Map<String, Object> param) {
		if (param != null) {
			if (url.indexOf("?") == -1)
				url = String.valueOf(url) + "?";
			Iterator<String> it = param.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				Object value = param.get(key);
				url = String.valueOf(url) + "&" + key + "=" + value;
			}
		}
		return url;
	}
}
