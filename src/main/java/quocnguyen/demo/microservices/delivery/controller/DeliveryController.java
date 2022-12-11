package quocnguyen.demo.microservices.delivery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quocnguyen.demo.microservices.delivery.entity.DeliveryTO;
import quocnguyen.demo.microservices.delivery.event_handler.RabbitMQDemoDeliveryService;
import quocnguyen.demo.microservices.delivery.service.DeliveryService;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

	private final DeliveryService deliveryService;

	@GetMapping
	public DeliveryTO getOrder(@RequestParam String orderId) {
		return deliveryService.getByOrderId(orderId);
	}

}
