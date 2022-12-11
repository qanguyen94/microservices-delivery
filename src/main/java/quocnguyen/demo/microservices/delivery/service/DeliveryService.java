package quocnguyen.demo.microservices.delivery.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quocnguyen.demo.microservices.delivery.entity.DeliveryTO;
import quocnguyen.demo.microservices.delivery.event_handler.DeliveryEventPublisher;
import quocnguyen.demo.microservices.delivery.repository.DeliveryRepository;
import static quocnguyen.demo.microservices.delivery.type.DeliveryStatus.ON_GOING;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryService {

	private final DeliveryRepository deliveryRepository;
	private final DeliveryEventPublisher deliveryEventPublisher;

	public String deliver(String orderId) {
		DeliveryTO deliveryTO = DeliveryTO.builder()
				.orderId(orderId)
				.deliveryAddress("12 Nguyen Van Linh, Da Nang, Viet Nam")
				.deliveryStatus(ON_GOING)
				.build();
		String deliveryId = deliveryRepository.create(deliveryTO);
		DeliveryTO createdDeliveryTO = deliveryRepository.getByDeliveryId(deliveryId);
		deliveryEventPublisher.publish(createdDeliveryTO);
		return deliveryId;
	}

	public DeliveryTO getByDeliveryId(String deliveryId) {
		return deliveryRepository.getByDeliveryId(deliveryId);
	}

	public DeliveryTO getByOrderId(String orderId) {
		return deliveryRepository.getByOrderId(orderId);
	}
}
