package info.filmtong.movie.mapper;

import java.util.List;

import info.filmtong.movie.vo.person.PersonInfoVO;
import info.filmtong.movie.vo.person.PersonResponseVO;

public interface PersonInfoMapper {

	PersonInfoVO selectPersonInfoByPiId(int id);
	
	int insertPersonInfo(PersonInfoVO personInfo);
	
	int insertPersonInfoWithPersonResponse(PersonResponseVO person);
}
