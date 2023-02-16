package info.filmtong.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import info.filmtong.movie.service.BoxOfficeService;
import info.filmtong.movie.vo.boxoffice.BoxOfficeVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BoxOfficeController {

	 @Autowired
	 private BoxOfficeService boxOfficeService;

	 @GetMapping("/box-offices") 
	 public List<BoxOfficeVO> getBoxOfficeList(@ModelAttribute BoxOfficeVO boxOffice) {
	    log.info("boxOffice=>{}", boxOffice);
	    List<BoxOfficeVO> list = boxOfficeService.selectBoxOfficeList();
	    log.info("list=>{}", list);
	    return list;
	  }

	 
//	@GetMapping("/test1")
//	public String getTest1(@RequestParam("a") String a) {
//		return a;
//	}
//	
//	@GetMapping("/test2")
//	public String getTest2(@RequestParam("a") String a) {
//		return a;
//	}
//	
//	@PostMapping("/test2")
//	public BoxOfficeVO getTest2(@RequestBody BoxOfficeVO boxOffice) {
//		return boxOffice;
//	}


}

