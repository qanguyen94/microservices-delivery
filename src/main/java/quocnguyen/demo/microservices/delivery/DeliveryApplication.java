package quocnguyen.demo.microservices.delivery;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import quocnguyen.demo.microservices.delivery.config.WebSecurityConfiguration;

@Slf4j
@Import({WebSecurityConfiguration.class})
@SpringBootApplication
public class DeliveryApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication application = new SpringApplication(DeliveryApplication.class);
		printStartupInfo(application.run(args).getEnvironment());
	}

	static void printStartupInfo(Environment environment) throws IOException {
	}

}
