package info.filmtong.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import info.filmtong.movie.service.MemberClassService;
import info.filmtong.movie.vo.ClassInfoVO;
import info.filmtong.movie.vo.MemberInfoClassInfoMapVO;
import info.filmtong.movie.vo.MemberInfoVO;

@RestController
public class MemberClassController {

	@Autowired
	private MemberClassService memberClassService;
	
	@GetMapping("/member-infos")
	public List<MemberInfoVO> getMemberInfos(MemberInfoVO memberInfo) {
		return memberClassService.selectMemberInfos(memberInfo);
	} 
	
	@GetMapping("/member-infos/{miId}")
	public boolean existMemberInfos(@PathVariable String miId, MemberInfoVO memberInfo) {
		memberInfo.setMiId(miId);
		return memberClassService.existsMemberInfoByMiId(memberInfo);
	} 
	
	@GetMapping("/class-infos")
	public List<ClassInfoVO> getClassInfos(ClassInfoVO classInfo) {
		return memberClassService.selectClassInfos(classInfo);
	}
	
	@GetMapping("/mcmaps")
	public List<MemberInfoClassInfoMapVO> getMemberInfoClassInfoMap(MemberInfoClassInfoMapVO memberClassMap) {
		return memberClassService.selectMemberInfoClassInfoMap(memberClassMap);
	}
	
	@PostMapping("/member-infos")
	public int insertMemberInfo(@RequestBody List<MemberInfoVO> memberInfo) {
		return memberClassService.insertMemberInfo(memberInfo);

	}
	
	@PostMapping("/class-infos")
	public int insertClassInfo(@RequestBody List<ClassInfoVO> classInfo) {
		return memberClassService.insertClassInfo(classInfo);
	}
	
	@PostMapping("/mcmaps")
	public int insertMemberInfoClassInfoMap(@RequestBody MemberInfoClassInfoMapVO memberClassMap) {
		return memberClassService.insertMemberInfoClassInfoMap(memberClassMap);
	}
	
}