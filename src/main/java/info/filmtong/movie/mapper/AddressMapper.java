package info.filmtong.movie.mapper;

import java.util.List;

import info.filmtong.movie.vo.DongVO;
import info.filmtong.movie.vo.GugunVO;
import info.filmtong.movie.vo.SidoVO;

public interface AddressMapper {

	List<SidoVO> selectSidoList(SidoVO sido);
	List<GugunVO> selectGugunList(GugunVO gugun);
	List<DongVO> selectDongList(DongVO dong);
}
