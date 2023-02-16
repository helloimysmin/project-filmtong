package info.filmtong.movie.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import info.filmtong.movie.vo.AddressVO;
import info.filmtong.movie.vo.DongVO;
import info.filmtong.movie.vo.GugunVO;
import info.filmtong.movie.vo.SidoVO;

@RestController
public class AddressController {

	@Autowired
	private List<SidoVO> sidoList;
	
	@Autowired
	private List<GugunVO> gugunList;
	
	@Autowired
	private List<DongVO> dongList;
	
	@GetMapping("/sido")
	public List<SidoVO> getSidoList() {
		return sidoList;
	}
	
	@GetMapping("/gugun/{siNum}")
	public List<GugunVO> getGugunList(@PathVariable("siNum") int siNum) {
		List<GugunVO> tmpGugunList = new ArrayList<>();
		for(GugunVO gugun : gugunList) {
			if(gugun.getSiNum() == siNum) {
				tmpGugunList.add(gugun);
			}
		}
		return tmpGugunList;
	}
	
	/*
	 * @GetMapping("/gugun2/{siNum}") public List<GugunVO>
	 * getGugunListWithLamda(@PathVariable("siNum") int siNum) { return
	 * gugunList.stream().filter(gugun -> gugun.getSiNum() ==
	 * siNum).collect(Collectors.toList()); }
	 */
	@GetMapping("/dong/{guNum}")
	public List<DongVO> getDongList(@PathVariable("guNum") int guNum) {
		List<DongVO> tmpDongList = new ArrayList<>();
		for(DongVO dong : dongList) {
			if(dong.getGuNum() == guNum) {
				tmpDongList.add(dong);
			}
		}
		return tmpDongList;
	}
	
	@GetMapping("/address")
	public AddressVO getAddressList(AddressVO addressVO) {
		addressVO.setSidoList(sidoList);
		addressVO.setGugunList(gugunList);
		addressVO.setDongList(dongList);
		return addressVO;
	}
}
