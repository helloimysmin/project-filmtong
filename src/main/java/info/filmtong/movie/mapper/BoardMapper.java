package info.filmtong.movie.mapper;

import java.util.List;

import info.filmtong.movie.vo.BoardVO;

public interface BoardMapper {

	List<BoardVO> selectBoards(BoardVO board);
	
	BoardVO selectBoardByNo(int no);
	
	int insertBoard(BoardVO board);
	
	int updateBoard(BoardVO board);
	
	int deleteBoard(int no);
}
