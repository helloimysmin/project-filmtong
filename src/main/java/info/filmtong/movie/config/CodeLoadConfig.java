package info.filmtong.movie.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import info.filmtong.movie.mapper.AddressMapper;
import info.filmtong.movie.vo.AddressVO;
import info.filmtong.movie.vo.DongVO;
import info.filmtong.movie.vo.GugunVO;
import info.filmtong.movie.vo.SidoVO;

@Configuration
public class CodeLoadConfig {
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Bean
	List<SidoVO> sidoList() {
		return addressMapper.selectSidoList(null);
	}
	
	@Bean
	List<GugunVO> gugunList() {
		return addressMapper.selectGugunList(null);
	}
	
	@Bean
	List<DongVO> dongList() {
		return addressMapper.selectDongList(null);
	}
}
