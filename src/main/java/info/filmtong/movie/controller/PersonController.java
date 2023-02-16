package info.filmtong.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import info.filmtong.movie.service.PersonService;
import info.filmtong.movie.service.PersonTotalService;
import info.filmtong.movie.vo.person.PersonResponseVO;

@RestController
public class PersonController {

	@Autowired
	private PersonTotalService personTotalService;
	
	@GetMapping("/person-info/{id}")
	public PersonResponseVO getPersonInfo(@PathVariable("id") int id) {
		return personTotalService.getPersonResponse(id);
	}
}
