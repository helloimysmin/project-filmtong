package info.filmtong.movie;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import info.filmtong.movie.service.MemberClassService;
import info.filmtong.movie.vo.MemberInfoVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class FilmtongApplicationTests {
	
	@Autowired
	private MemberClassService memberClassService;
	
	@Test
	void contextLoads() {
		List<MemberInfoVO> memberInfos = new ArrayList<>();
		for(int i=0; i<4; i++) {
			MemberInfoVO memberInfo = new MemberInfoVO();
			if(i==3) {
				memberInfo.setMiId("a1" + 1);
				memberInfo.setMiEmail("a1" + 1 + "@na");
			}
			memberInfo.setMiId("a1" + i);
			memberInfo.setMiEmail("a1" + i+ "@na");
			memberInfos.add(memberInfo);
		}
		int result = memberClassService.insertMemberInfo(memberInfos);
		log.debug("result=>{}", result);
	}

}
