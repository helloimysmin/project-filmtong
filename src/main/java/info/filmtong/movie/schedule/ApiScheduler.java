package info.filmtong.movie.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import info.filmtong.movie.service.BoxOfficeService;
import info.filmtong.movie.vo.boxoffice.BoxOfficeVO;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApiScheduler {
	@Autowired
	private BoxOfficeService boxOfficeService;
	
	//cron="초 분 시 일 월 주 + 0시1분 업데이트 안 됨. 0시2분에는 성공."
	@Scheduled(cron="0 2 0 * * *")
	public void dailyBoxOffice() {
		List<BoxOfficeVO> boxOfficeList = boxOfficeService.getBoxOfficeList(1);
		int result = boxOfficeService.insertBoxOfficeList(boxOfficeList);
		log.debug("result=>{}",result);
	}
}