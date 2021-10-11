package com.bharath.aws.lambda.sqs;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;

public class ClaimManagementLamdba {

	public void handler(SQSEvent event) {
		event.getRecords().forEach(message -> {
			System.out.println(message.getBody());
		});
	}
}
