package info.filmtong.movie.api;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import info.filmtong.movie.vo.boxoffice.BoxOfficeResponseVO;
import info.filmtong.movie.vo.boxoffice.BoxOfficeVO;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@PropertySource("classpath:env.properties")
public class ApiBoxOffice {
	@Value("${movie.url}")
	private String movieUrl;
	
	@Autowired
	private ObjectMapper om;

	public List<BoxOfficeVO> getBoxOfficeList(int max){
		Instant now = Instant.now();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 포맷팅 안하면 iso기준으로 시간이 나옴.
		List<BoxOfficeVO> allBoxOfficeList = new ArrayList<>();
		for(int i=1; i<=max; i++) { 
			Instant before = now.minus(Duration.ofDays(i)); 	//jdk 8.0부터 가능한 방법. 
			Date date = Date.from(before);
			String dateStr = sdf.format(date);
			String json = get(movieUrl + dateStr);
			try {
				BoxOfficeResponseVO result = om.readValue(json, BoxOfficeResponseVO.class);
				List<BoxOfficeVO> dailyBoxOfficeList = result.getBoxOfficeResult().getDailyBoxOfficeList();
				for(BoxOfficeVO boxOffice : dailyBoxOfficeList) {
					boxOffice.setTargetDt(dateStr);
				}
				allBoxOfficeList.addAll(dailyBoxOfficeList);
			}catch(Exception e) {
				log.error("parse error=>{}", e);
			}
		}
		return allBoxOfficeList;
		
	}

    private String get(String url){
        HttpURLConnection con = connect(url);
       
        try {
            con.setRequestMethod("GET");

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
            throw new RuntimeException("API URL이 잘못되었습니다. : " + movieUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + movieUrl, e);
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