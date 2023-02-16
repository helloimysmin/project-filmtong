package info.filmtong.movie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan
@EnableScheduling
@EnableAspectJAutoProxy
public class FilmtongApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmtongApplication.class, args);
	}

}