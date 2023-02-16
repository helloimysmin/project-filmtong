package info.filmtong.movie.mapper;

import java.util.List;

import info.filmtong.movie.vo.ClassInfoVO;
import info.filmtong.movie.vo.MemberInfoClassInfoMapVO;
import info.filmtong.movie.vo.MemberInfoVO;

public interface MemberClassMapper {

	List<MemberInfoVO> selectMemberInfos(MemberInfoVO memberInfo);
	
	List<ClassInfoVO> selectClassInfos(ClassInfoVO classInfo);
	
	List<MemberInfoClassInfoMapVO> selectMemberInfoClassInfoMap(MemberInfoClassInfoMapVO memberClassMap);
	
	MemberInfoVO selectMemberInfoByMiNum(MemberInfoVO memberInfo);
	
	ClassInfoVO selectClassInfoByCiName(ClassInfoVO classInfo);
	
	int insertMemberInfo(MemberInfoVO memberInfo);
	
	int insertClassInfo(ClassInfoVO classInfo);
	
	int insertMemberInfoClassInfoMap(MemberInfoClassInfoMapVO memberClassMap);
}