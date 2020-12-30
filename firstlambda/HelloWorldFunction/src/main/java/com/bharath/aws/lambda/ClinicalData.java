package com.bharath.aws.lambda;

public class ClinicalData {
	
	public String getBp() {
		return bp;
	}

	public void setBp(String bp) {
		this.bp = bp;
	}

	public String getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(String heartRate) {
		this.heartRate = heartRate;
	}

	private String bp;
	private String heartRate;

}
