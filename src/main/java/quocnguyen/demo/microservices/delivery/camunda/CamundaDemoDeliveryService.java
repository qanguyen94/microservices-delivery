package quocnguyen.demo.microservices.delivery.camunda;

import lombok.RequiredArgsConstructor;
import quocnguyen.demo.microservices.delivery.entity.DeliveryTO;
import quocnguyen.demo.microservices.delivery.service.DeliveryService;

@RequiredArgsConstructor
public class CamundaDemoDeliveryService implements DeliveryService {

	@Override
	public String deliver(String orderId) {
		return "Deliver is on the way...";
	}

	@Override
	public DeliveryTO getByDeliveryId(String deliveryId) {
		return null;
	}

	@Override
	public DeliveryTO getByOrderId(String orderId) {
		return null;
	}
}
