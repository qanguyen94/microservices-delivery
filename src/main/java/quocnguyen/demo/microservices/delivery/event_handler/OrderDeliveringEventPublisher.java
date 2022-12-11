package quocnguyen.demo.microservices.delivery.event_handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import quocnguyen.demo.microservices.delivery.entity.DeliveryTO;

@Slf4j
@RequiredArgsConstructor
public class OrderDeliveringEventPublisher {

	private static final String TOPIC_EXCHANGE_NAME = "order";
	private static final String ORDER_DELIVERING_ROUTING_KEY = "order.delivering";

	private final RabbitTemplate rabbitTemplate;

	public void publish(DeliveryTO deliveryTO) {
		rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ORDER_DELIVERING_ROUTING_KEY, deliveryTO.getId());
	}

}