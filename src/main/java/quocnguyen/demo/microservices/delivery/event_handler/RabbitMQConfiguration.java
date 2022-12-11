package quocnguyen.demo.microservices.delivery.event_handler;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import quocnguyen.demo.microservices.delivery.repository.DeliveryRepository;
import quocnguyen.demo.microservices.delivery.service.DeliveryService;

@Slf4j
@Configuration
@Profile({"rabbitmq", "rabbitmq-k8s"}) // This publisher is available only when at least one of these profiles is active
public class RabbitMQConfiguration {

	private static final String TOPIC_EXCHANGE_NAME = "order";
	private static final String PUBLISHING_QUEUE = "order-delivering";
	private static final String LISTENING_QUEUE = "order-created";

	@PostConstruct
	void init() {
		log.info("Service is running with RabbitMQ integration");
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(TOPIC_EXCHANGE_NAME);
	}

	@Bean
	Queue queue() {
		return new Queue(PUBLISHING_QUEUE, false);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("order.delivering.#");
	}

	@Bean
	MessageListenerAdapter listenerAdapter(OrderCreatedEventReceiver orderEventReceiver) {
		return new MessageListenerAdapter(orderEventReceiver, "onEvent");
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(LISTENING_QUEUE);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	DeliveryService deliveryService(DeliveryRepository deliveryRepository,
									OrderDeliveringEventPublisher deliveryEventPublisher) {
		return new RabbitMQDemoDeliveryService(deliveryRepository, deliveryEventPublisher);
	}

	@Bean
	OrderCreatedEventReceiver orderCreatedEventReceiver(DeliveryService deliveryService) {
		return new OrderCreatedEventReceiver(deliveryService);
	}

	@Bean
	OrderDeliveringEventPublisher orderDeliveringEventPublisher(RabbitTemplate rabbitTemplate) {
		return new OrderDeliveringEventPublisher(rabbitTemplate);
	}

}
