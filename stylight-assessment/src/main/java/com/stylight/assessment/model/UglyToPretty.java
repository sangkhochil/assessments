package com.stylight.assessment.model;

public class UglyToPretty {
	private String ugly;
	private String pretty;

	public UglyToPretty() {
	}

	public UglyToPretty(String ugly, String pretty) {
		this.ugly = ugly;
		this.pretty = pretty;
	}

	public String getUgly() {
		return ugly;
	}

	public void setUgly(String ugly) {
		this.ugly = ugly;
	}

	public String getPretty() {
		return pretty;
	}

	public void setPretty(String pretty) {
		this.pretty = pretty;
	}
}
