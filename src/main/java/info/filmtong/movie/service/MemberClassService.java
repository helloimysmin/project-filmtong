package info.filmtong.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import info.filmtong.movie.mapper.MemberClassMapper;
import info.filmtong.movie.vo.ClassInfoVO;
import info.filmtong.movie.vo.MemberInfoClassInfoMapVO;
import info.filmtong.movie.vo.MemberInfoVO;

@Service
public class MemberClassService {

	@Autowired
	MemberClassMapper memberClassMapper;
	
	public List<MemberInfoVO> selectMemberInfos(MemberInfoVO memberInfo) {
		return memberClassMapper.selectMemberInfos(memberInfo);
	}
	
	public List<ClassInfoVO> selectClassInfos(ClassInfoVO classInfo) {
		return memberClassMapper.selectClassInfos(classInfo);
	}
	
	public List<MemberInfoClassInfoMapVO> selectMemberInfoClassInfoMap(MemberInfoClassInfoMapVO memberClassMap) {
		return memberClassMapper.selectMemberInfoClassInfoMap(memberClassMap);
	}
	
	public int insertMemberInfo(List<MemberInfoVO> memberInfos) {
		int result = 0;
		for(MemberInfoVO memberInfo : memberInfos) {
			if(memberClassMapper.selectMemberInfoByMiNum(memberInfo)!= null) {
				throw new RuntimeException("멤버가 중복되었습니다");
			}
			result += memberClassMapper.insertMemberInfo(memberInfo);
		}
		return result;
	}
	
	public boolean existsMemberInfoByMiId(MemberInfoVO memberInfo) {
		return memberClassMapper.selectMemberInfoByMiNum(memberInfo) != null;
	}
	
	public int insertClassInfo(List<ClassInfoVO> classInfos) {
		int result = 0;
		for(ClassInfoVO classInfo : classInfos) {
			if(memberClassMapper.selectClassInfoByCiName(classInfo) != null) {
				throw new RuntimeException("이미 존재하는 강의명입니다.");
			}
			result += memberClassMapper.insertClassInfo(classInfo);
		}
		return result;
	}
	
	public int insertMemberInfoClassInfoMap(MemberInfoClassInfoMapVO memberClassMap) {
		return memberClassMapper.insertMemberInfoClassInfoMap(memberClassMap);
	}
}