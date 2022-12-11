package quocnguyen.demo.microservices.delivery.camunda;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import quocnguyen.demo.microservices.delivery.service.DeliveryService;

@Slf4j
@Configuration
@Profile("camunda") // Beans initialized in this class are available only when this profile is active
public class CamundaConfiguration {

	@PostConstruct
	void init() {
		log.info("Service is running with Camunda integration");
	}

	@Bean
	ProcessDeliveryHandler processDeliveryHandler() {
		return new ProcessDeliveryHandler();
	}

	@Bean
	DeliveryService deliveryService() {
		return new CamundaDemoDeliveryService();
	}

}
