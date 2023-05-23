package dev.zotware.apps.pts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@RestController
public class PlayThenSellApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayThenSellApplication.class, args);
	}

}
