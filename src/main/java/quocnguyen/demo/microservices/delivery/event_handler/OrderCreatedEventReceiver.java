package quocnguyen.demo.microservices.delivery.event_handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quocnguyen.demo.microservices.delivery.service.DeliveryService;

@Slf4j
@RequiredArgsConstructor
public class OrderCreatedEventReceiver {

	private final DeliveryService deliveryService;

	public void onEvent(String orderId) {
		log.info("Receive order with id {}. Start delivering", orderId);
		deliveryService.deliver(orderId);
	}

}