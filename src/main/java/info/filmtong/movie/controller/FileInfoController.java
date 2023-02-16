package info.filmtong.movie.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import info.filmtong.movie.service.FileInfoService;
import info.filmtong.movie.util.FileUtil;
import info.filmtong.movie.vo.FileInfoVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FileInfoController {

	@Autowired
	private FileInfoService fileInfoService;
	@Autowired
	private FileUtil fileUtil;
	
	@PostMapping("/file-infos")
	public int fileUpload(@ModelAttribute FileInfoVO fileInfo) throws IllegalStateException, IOException {
		return fileInfoService.insertFileInfo(fileInfo);
	}
	
	@PostMapping("/file-upload")
	public String fileUpload2(@ModelAttribute FileInfoVO fileInfo) throws IllegalStateException, IOException {
		log.debug("upload file=>{}", fileInfo.getFile());
		log.debug("upload file size=>{}", fileInfo.getFile().getSize());
		return fileUtil.saveFile(fileInfo.getFile());
	}
}