package quocnguyen.demo.microservices.delivery.camunda;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

@Slf4j
@ExternalTaskSubscription("ProcessDelivery") // create a subscription for this topic name
public class ProcessDeliveryHandler implements ExternalTaskHandler {

	@SneakyThrows
	@Override
	public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

		// There is no specific handling yet when receiving the event from Camunda
		// Just write log to verify the integration with Camunda is successful
		String orderId = externalTask.getVariable("orderId");
		log.info("Start delivering process for order with id {}", orderId);

		VariableMap variables = Variables.createVariables();
		variables.put("orderId", orderId);
		variables.put("status", "successful");

		// To be able to see the process running in Camunda. Let make the process stay here for a while
		Thread.sleep(5000);

		// complete the external task
		externalTaskService.complete(externalTask, variables);
		log.info("Delivering process for order with id {} has been processed successfully", orderId);
	}

}
