package com.leuchine.measure;

import java.util.HashSet;

public class PrecisionRecall implements Measure {
	private HashSet<Integer> reference = null;

	private HashSet<Integer> hypothesis = null;

	private double precision;

	private double recall;

	private double f1;

	public PrecisionRecall() {
		this.reference = new HashSet<Integer>();
		this.hypothesis = new HashSet<Integer>();
	}

	public void read(int sentenceCount, String r, String h) {
		String[] rnumbers = r.split(" ");
		String[] hnumbers = h.split(" ");
		for (int i = 0; i < rnumbers.length; i++) {
			reference.add(Integer.parseInt(rnumbers[i]));
		}
		for (int i = 0; i < hnumbers.length; i++) {
			hypothesis.add(Integer.parseInt(hnumbers[i]));
		}
	}

	public double compute() {
		int referenceLength = reference.size();
		reference.retainAll(hypothesis);
		this.precision = (double) reference.size() / hypothesis.size();
		this.recall = (double) reference.size() / referenceLength;
		this.f1 = 2 * this.precision * this.recall
				/ (this.precision + this.recall);
		return 0;
	}

	public double getPrecision() {
		return this.precision;
	}

	public double getRecall() {
		return this.recall;
	}

	public double getF1() {
		return this.f1;
	}

	public static void main(String[] args) {
		PrecisionRecall test = new PrecisionRecall();
		test.read(0, "1 2 4 5 6", "1 2 3 4 5");
		test.compute();
		System.out.println("Precision: " + test.getPrecision());
		System.out.println("Recall: " + test.getRecall());
		System.out.println("F1: "+test.getF1());
	}
}
