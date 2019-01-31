package com.timeinc.tcs.token;

public class TokenMessage extends RecordLayout {
	
	public enum Function {
		ENCIPHER, DECIPHER
	}
	
	public static final String SUCCESS_CODE = "0000";
	
	public static TokenMessage newInstance() {
		return new TokenMessage();
	}
	
	public static TokenMessage deserialize(String serialized) {
		int startPosition = 0;
		TokenMessage tm = TokenMessage.newInstance();
		for (Segment segment : tm.getSegments()) {
			int endPosition = segment.getLength() + startPosition;
			segment.setValue(serialized.substring(startPosition, endPosition).trim());
			startPosition = endPosition;
		}
		return tm;
	}
	
	private TokenMessage() {
		super();
		segments.add(new Segment(8));
		segments.add(new Segment(8));
		segments.add(new Segment(8));
		segments.add(new Segment(20));
		segments.add(new Segment(20));
		segments.add(new Segment(1));
		segments.add(new Segment(4));
		segments.add(new Segment(31)); // filler
	}
	
	public TokenMessage setLayoutName(String layoutName) {
		segments.get(0).setValue(layoutName);
		return this;
	}
	
	public TokenMessage setFunctionName(String functionName) {
		segments.get(1).setValue(functionName);
		return this;
	}
	
	public TokenMessage setSystemId(String systemId) {
		segments.get(2).setValue(systemId);
		return this;
	}
	
	public TokenMessage setClearText(String clearText) {
		segments.get(3).setValue(clearText);
		return this;
	}
	
	public TokenMessage setCipherText(String cipherText) {
		segments.get(4).setValue(cipherText);
		return this;
	}
	
	public TokenMessage setEncryptionFlag(String encryptionFlag) {
		segments.get(5).setValue(encryptionFlag);
		return this;
	}
	
	public TokenMessage setReturnCode(String returnCode) {
		segments.get(6).setValue(returnCode);
		return this;
	}
	
	public String getLayoutName() {
		return segments.get(0).getValue();
	}
	
	public String getFunctionName() {
		return segments.get(1).getValue();
	}
	
	public String getSystemId() {
		return segments.get(2).getValue();
	}
	
	public String getClearText() {
		return segments.get(3).getValue();
	}
	
	public String getCipherText() {
		return segments.get(4).getValue();
	}
	
	public String getEncryptionFlag() {
		return segments.get(5).getValue();
	}
	
	public String getReturnCode() {
		return segments.get(6).getValue();
	}
}
