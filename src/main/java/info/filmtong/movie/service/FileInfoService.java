package info.filmtong.movie.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import info.filmtong.movie.mapper.FileInfoMapper;
import info.filmtong.movie.vo.FileInfoVO;

@Service
@PropertySource("classpath:env.properties")
public class FileInfoService {
	@Value("${file.path}")
	private String filePath;
	
	@Autowired
	private FileInfoMapper fileInfoMapper;

	public int insertFileInfo(FileInfoVO fileInfo) throws IllegalStateException, IOException {
		if(fileInfo.getFile()==null) {
			throw new RuntimeException("파일은 필수입니다.");
		}
		MultipartFile mf = fileInfo.getFile();
		String fiName = mf.getOriginalFilename();
		String fiPath = filePath + UUID.randomUUID().toString();
		fileInfo.setFiName(fiName);
		fileInfo.setFiPath(fiPath);
		File tmpFile = new File(fiPath);
		mf.transferTo(tmpFile);
		int result = fileInfoMapper.insertFileInfo(fileInfo);
		result += fileInfoMapper.insertFileInfo(fileInfo);
		return result;
	}
}