package info.filmtong.movie.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileUtil {

	private static final String BASE_PATH;
	static {
		String osName = System.getProperty("os.name");
		if(osName.toUpperCase().contains("WINDOW")) {
			BASE_PATH = "C:";
		}else {
			BASE_PATH = "";
		}
	}

	
	public String saveFile(MultipartFile file) {
		String name = file.getOriginalFilename();	//확장자 끄집어 내기 위해서 하는 것
		int lastIndex = name.lastIndexOf(".");
		String extName = name.substring(lastIndex);
		String fileName =  UUID.randomUUID().toString() + extName;
		File saveFile = new File(BASE_PATH + "/file-upload/" + fileName);
		try {
			file.transferTo(saveFile);
			return fileName;
		} catch (IllegalStateException e) {
			log.error("file upload error=>{}",e);
		} catch (IOException e) {
			log.error("file upload error=>{}",e);
		}
		return null;
	}
}