package quocnguyen.demo.microservices.delivery.repository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.stereotype.Repository;
import quocnguyen.demo.microservices.delivery.entity.DeliveryTO;
import quocnguyen.demo.microservices.delivery.exception.NotFoundException;
import quocnguyen.demo.microservices.delivery.type.DeliveryStatus;

@Repository
public class DeliveryRepository {

	private int currentId = 1;
	private final Map<String, DeliveryTO> repository = new HashMap<>();
	private final List<String> deliveryAddresses = new ArrayList<>();

	public DeliveryRepository() {
		initializeDeliveryAddresses();
		initializeDeliveries();
	}

	public String create(DeliveryTO deliveryTO) {
		currentId++;
		String deliveryId = "delivery-" + currentId;
		deliveryTO.setId(deliveryId);
		repository.put(deliveryId, deliveryTO);
		return deliveryId;
	}

	public DeliveryTO getByDeliveryId(String deliveryId) {
		return repository.get(deliveryId);
	}

	public DeliveryTO getByOrderId(String orderId) {
		return repository.keySet()
				.stream()
				.map(repository::get)
				.filter(delivery -> delivery.getOrderId().equals(orderId))
				.reduce((d1, d2) -> {
					throw new IllegalStateException(MessageFormat.format("Found more than one delivery with order id {0}", orderId));
				})
				.orElseThrow(() -> {
					throw new NotFoundException(MessageFormat.format("Found no delivery with order id {0}", orderId));
				});
	}

	private void initializeDeliveries() {
		Random random = new Random();
		for (; currentId <= 5; currentId++) {
			int index = random.nextInt(deliveryAddresses.size());
			String randomAddress = deliveryAddresses.get(index);
			DeliveryTO initialDelivery = DeliveryTO.builder()
					.id("delivery-" + currentId)
					.orderId("order-" + currentId)
					.deliveryAddress(randomAddress)
					.deliveryStatus(DeliveryStatus.DELIVERED)
					.build();
			repository.put(initialDelivery.getId(), initialDelivery);
		}
	}

	private void initializeDeliveryAddresses() {
		deliveryAddresses.add("123 Phan Chau Trinh, Da Nang, Viet Nam");
		deliveryAddresses.add("06 Hai Phong, Ha Noi, Viet Nam");
		deliveryAddresses.add("65 Le Duan, Hai Phong, Viet Nam");
		deliveryAddresses.add("564 Nguyen Chi Thanh, Bac Ninh, Viet Nam");
	}

}
