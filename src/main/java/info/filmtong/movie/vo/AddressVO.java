package info.filmtong.movie.vo;

import java.util.List;

import lombok.Data;

@Data
public class AddressVO {

	private List<SidoVO> sidoList;
	private List<GugunVO> gugunList;
	private List<DongVO> dongList;
}
