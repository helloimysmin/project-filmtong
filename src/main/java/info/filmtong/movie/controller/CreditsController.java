package info.filmtong.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import info.filmtong.movie.service.CreditsService;
import info.filmtong.movie.vo.credits.CreditsResponseVO;

@RestController
public class CreditsController {

	@Autowired
	private CreditsService creditsService;
	
	@GetMapping("/credits/{id}")
	public CreditsResponseVO getCredits(@PathVariable("id") int id) {
		return creditsService.getApiCreditsRest(id);
	}
}
