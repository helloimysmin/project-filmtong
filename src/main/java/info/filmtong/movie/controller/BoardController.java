package info.filmtong.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import info.filmtong.movie.service.BoardService;
import info.filmtong.movie.vo.BoardVO;

@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boards")
	public List<BoardVO> getBoards(BoardVO board) {
		return boardService.selectBoards(board);
	}
	
	@GetMapping("/board/{no}")
	public BoardVO getBoard(@PathVariable("no") int no) {
		return boardService.selectBoardByNo(no);
	}
	
	@PostMapping("/boards")
	public int insertBoard(@RequestBody BoardVO board) {
		return boardService.insertBoard(board);
	}
	
	@PostMapping("/board/{no}")
	public int updateBoard(@RequestBody BoardVO board, @PathVariable("no") int no) {
		board.setNo(no);
		return boardService.updateBoard(board);
	}
	
	@DeleteMapping("/board/{no}")
	public int deleteBoard(@PathVariable("no") int no) {
		return boardService.deleteBoard(no);
	}
}
