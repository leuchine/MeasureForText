package com.leuchine.measure;

public interface Measure {
	public void read(int sentenceCount, String r, String h);

	public double compute();
}
