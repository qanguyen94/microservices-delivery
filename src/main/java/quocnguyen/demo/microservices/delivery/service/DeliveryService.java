package quocnguyen.demo.microservices.delivery.service;

import quocnguyen.demo.microservices.delivery.entity.DeliveryTO;

public interface DeliveryService {

	String deliver(String orderId);

	DeliveryTO getByDeliveryId(String deliveryId);

	DeliveryTO getByOrderId(String orderId);

}
