package com.leuchine.measure;

import java.util.Arrays;

public class WindowdiffMeasure {
	private int[] reference;

	private int[] hypothesis;

	private int windowSize;

	public void read(int sentenceCount, String r, String h) {
		reference = new int[sentenceCount + 1];
		hypothesis = new int[sentenceCount + 1];
		String[] rnumbers = r.split(" ");
		String[] hnumbers = h.split(" ");
		int[] rsplit = new int[rnumbers.length + 2];
		int[] hsplit = new int[hnumbers.length + 2];
		for (int i = 1; i <= rnumbers.length; i++) {
			rsplit[i] = Integer.parseInt(rnumbers[i - 1]);
		}
		for (int i = 1; i <= hnumbers.length; i++) {
			hsplit[i] = Integer.parseInt(hnumbers[i - 1]);
		}
		hsplit[0] = rsplit[0] = 1;
		hsplit[hnumbers.length + 1] = rsplit[rnumbers.length + 1] = sentenceCount + 1;
		int order = 1;
		for (int i = 1; i < rsplit.length; i++) {
			for (int j = rsplit[i - 1]; j < rsplit[i]; j++) {
				reference[j] = order;
			}
			order++;
		}
		order = 1;
		for (int i = 1; i < hsplit.length; i++) {
			for (int j = hsplit[i - 1]; j < hsplit[i]; j++) {
				hypothesis[j] = order;
			}
			order++;
		}
		int sum = 0;
		int count = 0;
		for (int i = 1; i < rsplit.length; i++) {
			sum = sum + rsplit[i] - rsplit[i - 1];
			count++;
		}
		this.windowSize = (int) ((double) sum / count) * 2;
		System.out.println(Arrays.toString(this.reference));
		System.out.println(Arrays.toString(this.hypothesis));
		System.out.println(windowSize);
	}

	public double compute() {
		int count = 0;
		int disagreement = 0;
		for (int i = 1; (i + this.windowSize) < this.reference.length; i++) {
			count++;
			int flag1, flag2;
			flag1 = this.reference[i + this.windowSize] - this.reference[i];
			flag2 = this.hypothesis[i + this.windowSize] - this.hypothesis[i];
			if (flag1 != flag2)
				disagreement++;
		}
		return (double) disagreement / count;
	}

	public static void main(String[] args) {
		WindowdiffMeasure test=new WindowdiffMeasure();
		test.read(10, "3 5 7", "2 4 8");
		System.out.println(test.compute());
	}
}
