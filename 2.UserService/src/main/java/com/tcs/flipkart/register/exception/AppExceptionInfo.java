package com.tcs.flipkart.register.exception;

import java.time.LocalDateTime;

public class AppExceptionInfo {

	private String exCode;
	private String exMsg;
	private LocalDateTime exDate;
	public String getExCode() {
		return exCode;
	}
	public void setExCode(String exCode) {
		this.exCode = exCode;
	}
	public String getExMsg() {
		return exMsg;
	}
	public void setExMsg(String exMsg) {
		this.exMsg = exMsg;
	}
	public LocalDateTime getExDate() {
		return exDate;
	}
	public void setExDate(LocalDateTime exDate) {
		this.exDate = exDate;
	}

	
}
