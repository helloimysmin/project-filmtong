package info.filmtong.movie.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import info.filmtong.movie.vo.moviegenre.MovieGenreResponseVO;
import info.filmtong.movie.vo.moviegenre.MovieGenreVO;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@PropertySource("classpath:env.properties")
public class ApiMovieGenre {
	@Value("${genre.url}")
	private String genreUrl;
	
	@Value("${popular.movie.method}")
	private String method;
	
	@Autowired
	private ObjectMapper om;

	public List<MovieGenreVO> getMovieGenreInfos(){
		try {
			String json = get(genreUrl);
			MovieGenreResponseVO genre = om.readValue(json, MovieGenreResponseVO.class);
			return genre.getGenres();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

    private String get(String url){
        HttpURLConnection con = connect(url);
       
        try {
            con.setRequestMethod(method);

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + genreUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + genreUrl, e);
        }
    }

    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
