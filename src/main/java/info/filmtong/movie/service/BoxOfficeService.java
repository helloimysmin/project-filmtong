package info.filmtong.movie.service;

import info.filmtong.movie.api.ApiBoxOffice;
import info.filmtong.movie.mapper.BoxOfficeMapper;
import info.filmtong.movie.rest.RESTApiBoxOffice;
import info.filmtong.movie.vo.boxoffice.BoxOfficeResponseVO;
import info.filmtong.movie.vo.boxoffice.BoxOfficeVO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
public class BoxOfficeService {

	@Value("${movie.url}")
	private String dataUrl;

	@Autowired
	RESTApiBoxOffice restApiBoxOffice;

	@Autowired
	ApiBoxOffice apiBoxOffice;

	@Autowired
	BoxOfficeMapper boxOfficeMapper;

	public List<BoxOfficeVO> getBoxOfficeList(int cnt) {
		return apiBoxOffice.getBoxOfficeList(cnt);
	}

	public BoxOfficeResponseVO getRESTBoxOfficeList() {
		Map<String, Object> param = new HashMap<>();
		param.put("targetDt", "20230130");
		BoxOfficeResponseVO response = (BoxOfficeResponseVO) this.restApiBoxOffice.getData(dataUrl,
				BoxOfficeResponseVO.class, param);
		List<BoxOfficeVO> boxOfficeList = response.getBoxOfficeResult().getDailyBoxOfficeList();
		for (BoxOfficeVO boxOffice : boxOfficeList)
			boxOffice.setTargetDt("20230130");
		return response;
	}

	public int insertBoxOfficeList(List<BoxOfficeVO> boxOfficeList) {
		return this.boxOfficeMapper.insertBoxOfficeList(boxOfficeList);
	}

	public int insertBoxOffice(BoxOfficeVO boxOffice) {
		return this.boxOfficeMapper.insertBoxOffice(boxOffice);
	}

	public List<BoxOfficeVO> selectBoxOfficeList() {
		return this.boxOfficeMapper.selectBoxOfficeList();
	}

}
