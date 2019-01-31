package com.timeinc.tcs.epay2;

import java.util.ArrayList;
import java.util.List;

public abstract class RecordLayout {
	
	public static final char PADDING_CHARACTER = ' ';
	
	public static class Segment {
		
		private final char[] paddedValue;
		
		private String name;
		private String value;
		
		public Segment(int length) {
			paddedValue = new char[length];
		}
		
		public Segment(int length, String name, String value) {
			paddedValue = new char[length];
			this.name = name;
			this.value = value;
		}

		public int getLength() {
			return paddedValue.length;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			if (value == null) {
				return;
			} else if (value.length() > paddedValue.length) {
				this.value = value.substring(0, paddedValue.length);
				System.err.println("WARNING: value " 
								+ value 
								+ " was truncated to " 
								+ this.value);
			} else {
				this.value = value;
			}
		}
		
		@Override
		public String toString() {

			for (int i=0; i < paddedValue.length; i++) {
				paddedValue[i] = PADDING_CHARACTER;
			}
			
			if (value != null) {
				for (int i=0; i < value.length(); i++) {
					paddedValue[i] = value.charAt(i);
				}
			} 
				
			return new String(paddedValue);
		}
	}
	
	protected final List<Segment> segments;
	
	protected RecordLayout() {
		segments = new ArrayList<Segment>();
	}

	public final List<Segment> getSegments() {
		return segments;
	}
	
	public final int length() {
		int len = 0;
		if (!segments.isEmpty()) {
			for (Segment segment : segments) {
				len += segment.getLength();
			}
		} 
		return len;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(segments.size());
		for (Segment segment : segments) {
			sb.append(segment.toString());
		}
		return sb.toString();
	}
}
