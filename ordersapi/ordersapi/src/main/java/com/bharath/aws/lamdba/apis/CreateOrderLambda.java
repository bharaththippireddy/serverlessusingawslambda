package com.bharath.aws.lamdba.apis;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.bharath.aws.lamdba.apis.dto.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateOrderLambda {

	private final ObjectMapper objectMapper = new ObjectMapper();
	private final DynamoDB dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());
	
	public APIGatewayProxyResponseEvent createOrder(APIGatewayProxyRequestEvent request)
			throws JsonMappingException, JsonProcessingException {
		
		Order order = objectMapper.readValue(request.getBody(), Order.class);

		
		Table table = dynamoDB.getTable(System.getenv("ORDERS_TABLE"));
		Item item = new Item().withPrimaryKey("id", order.id)
				.withString("itemName", order.itemName)
				.withInt("quantity", order.quantity);
		table.putItem(item);
		
		return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Order ID:" + order.id);

	}
}
