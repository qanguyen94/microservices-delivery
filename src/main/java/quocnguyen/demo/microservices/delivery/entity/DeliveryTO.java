package quocnguyen.demo.microservices.delivery.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import quocnguyen.demo.microservices.delivery.type.DeliveryStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DeliveryTO {
	private String id;
	private String orderId;
	private String deliveryAddress;
	private DeliveryStatus deliveryStatus;
}
