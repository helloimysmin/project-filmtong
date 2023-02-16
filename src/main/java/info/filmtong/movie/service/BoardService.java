package info.filmtong.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.filmtong.movie.mapper.BoardMapper;
import info.filmtong.movie.vo.BoardVO;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	public List<BoardVO> selectBoards(BoardVO board) {
		return boardMapper.selectBoards(board);
	}
	
	public BoardVO selectBoardByNo(int no) {
		return boardMapper.selectBoardByNo(no);
	}
	
	public int insertBoard(BoardVO board) {
		return boardMapper.insertBoard(board);
	}
	
	public int updateBoard(BoardVO board) {
		return boardMapper.updateBoard(board);
	}
	
	public int deleteBoard(int no) {
		return boardMapper.deleteBoard(no);
	}
}
