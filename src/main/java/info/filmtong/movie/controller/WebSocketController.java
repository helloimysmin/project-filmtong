package info.filmtong.movie.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebSocketController {

	@Autowired
	private SimpMessageSendingOperations simpMessage;
	
	@MessageMapping("/message")
	@PostMapping("/message")
	public void intoChat(@RequestBody Map<String, String> chat) {
		log.debug("chat=>{}", chat);
		simpMessage.convertAndSend("/topic/message", chat);
	}
}