package info.filmtong.movie.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VOGenerator {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("");
		String absoulutePath = path.toAbsolutePath().toString() + "\\src\\main\\java\\info\\filmtong\\movie\\vo\\";
		log.info("absoulutePath=>{}", absoulutePath);
		FileOutputStream fos = new FileOutputStream(absoulutePath + "TestVO.java");
		String fileText = "package info.filmtong.movie.vo.userInfo;\r\n"
				+ "\r\n"
				+ "import lombok.Data;\r\n"
				+ "\r\n"
				+ "@Data\r\n"
				+ "public class UserInfo2VO {\r\n"
				+ "}";
		byte[] fileTextBytes = fileText.getBytes();
		fos.write(fileTextBytes);
		fos.close();
	}
}