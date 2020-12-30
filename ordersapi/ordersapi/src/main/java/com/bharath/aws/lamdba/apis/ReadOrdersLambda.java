package com.bharath.aws.lamdba.apis;

import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.bharath.aws.lamdba.apis.dto.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadOrdersLambda {

	private final ObjectMapper objectMapper = new ObjectMapper();
	private AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.defaultClient();

	public APIGatewayProxyResponseEvent getOrders(APIGatewayProxyRequestEvent request)
			throws JsonMappingException, JsonProcessingException {
		ScanResult scanResult = dynamoDB.scan(new ScanRequest().withTableName(System.getenv("ORDERS_TABLE")));
		List<Order> orders = scanResult
				.getItems().stream().map(item -> new Order(Integer.parseInt(item.get("id").getN()),
						item.get("itemName").getS(), Integer.parseInt(item.get("quantity").getN())))
				.collect(Collectors.toList());

		String jsonOutput = objectMapper.writeValueAsString(orders);
		return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonOutput);

	}
}
