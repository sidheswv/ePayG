package com.timeinc.tcs.token;

public class TokenizerViewModel {

	public static class Builder {
		
		private String returnCode = "";
		private String function = "";
		private String clearText = "";
		private String cipherText = "";
		private String encryption = "";
		
		public Builder() {}
		
		public TokenizerViewModel build() {
			return new TokenizerViewModel(this);
		}
		
		public Builder returnCode(String value) {
			returnCode = value;
			return this;
		}
		
		public Builder function(String value) {
			function = value;
			return this;
		}
		
		public Builder clearText(String value) {
			clearText = value;
			return this;
		}
		
		public Builder cipherText(String value) {
			cipherText = value;
			return this;
		}
		
		public Builder encryption(String value) {
			encryption = value;
			return this;
		}
	}

	private final String returnCode;
	private final String function;
	private final String clearText;
	private final String cipherText;
	private final String encryption;

	private TokenizerViewModel(Builder builder) {
		this.returnCode = builder.returnCode;
		this.function = builder.function;
		this.clearText = builder.clearText;
		this.cipherText = builder.cipherText;
		this.encryption = builder.encryption;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public String getFunction() {
		return function;
	}

	public String getClearText() {
		return clearText;
	}

	public String getCipherText() {
		return cipherText;
	}

	public String getEncryption() {
		return encryption;
	}
	
	public boolean isError() {
		return !(TokenMessage.SUCCESS_CODE.equals(returnCode));
	}
}
