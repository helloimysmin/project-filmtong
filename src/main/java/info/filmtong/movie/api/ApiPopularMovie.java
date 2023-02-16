package info.filmtong.movie.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import info.filmtong.movie.vo.popular.PopularMovieInfoVO;
import info.filmtong.movie.vo.popular.PopularMovieResponseVO;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@PropertySource("classpath:env.properties")
public class ApiPopularMovie {
	@Value("${popular.movie.url}")
	private String tmdbUrl;
	
	@Value("${popular.movie.method}")
	private String method;
	
	@Autowired
	private ObjectMapper om;
	
	public List<PopularMovieInfoVO> getPopularMovies(int page) {
		List<PopularMovieInfoVO> popularMovieInfoList = new ArrayList<>();
		for(int i=1; i<=page; i++) { 
			String json = get(tmdbUrl + i);
			try {
				PopularMovieResponseVO result = om.readValue(json, PopularMovieResponseVO.class);
				List<PopularMovieInfoVO> popularMovieList = result.getResults();
				popularMovieInfoList.addAll(popularMovieList);
			}catch(Exception e) {
				log.error("parse error=>{}", e);
			}
		}
		return popularMovieInfoList;
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
            throw new RuntimeException("API URL이 잘못되었습니다. : " + tmdbUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + tmdbUrl, e);
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
