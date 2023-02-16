package info.filmtong.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.filmtong.movie.mapper.PersonInfoMapper;
import info.filmtong.movie.vo.person.PersonInfoVO;
import info.filmtong.movie.vo.person.PersonResponseVO;

@Service
public class PersonInfoService {

	@Autowired
	private PersonInfoMapper personInfoMapper;
	
	public PersonInfoVO selectPersonInfoByPiId(int id) {
		return personInfoMapper.selectPersonInfoByPiId(id);
	}
	
	public int insertPersonInfo(PersonInfoVO personInfo) {
		return personInfoMapper.insertPersonInfo(personInfo);
	}
	
	public int insertPersonInfoWithPersonResponse(PersonResponseVO person) {
		return personInfoMapper.insertPersonInfoWithPersonResponse(person);
	}
	
}
