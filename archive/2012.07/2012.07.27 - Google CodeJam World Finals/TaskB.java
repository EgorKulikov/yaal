package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	private int total;
	private int activities;
	private double[] all;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println(testNumber);
		int count = in.readInt();
		activities = in.readInt();
		Rational[] probability = new Rational[count];
		int[] max = new int[count];
		for (int i = 0; i < count; i++) {
			String p = in.readString();
			max[i] = in.readInt();
			String[] tokens = p.split("/");
			probability[i] = new Rational(Long.parseLong(tokens[0]), Long.parseLong(tokens[1]));
		}
		total = (int) ArrayUtils.sumArray(max);
		all = new double[total];
		int index = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < max[i]; j++)
				all[index++] = probability[i].value();
		}
		Arrays.sort(all);
		double answer = 1;
		double[] gammas = new double[activities + 1];
		int left = 0;
		int right = activities;
		while (right - left > 4) {
			int midLeft = (2 * left + right) / 3;
			int midRight = (2 * right + left) / 3;
			if (calculate(midLeft) > calculate(midRight))
				left = midLeft;
			else
				right = midRight;
		}
		for (int i = left; i <= right; i++) {
			double gamma = calculate(i);
			answer = Math.min(answer, gamma);
		}
		out.printLine("Case #" + testNumber + ":", answer);
	}

	private double calculate(int i) {
		double alpha = 1;
		double beta = 0;
		double gamma = 0;
		for (int j = 0; j < i; j++) {
			double p = all[total - j - 1];
			gamma += beta * p;
			beta = (alpha + beta) * (1 - p);
			alpha *= p;
		}
		for (int j = total - (activities - i); j < total; j++) {
			double p = all[total - j - 1];
			gamma += beta * p;
			beta = (alpha + beta) * (1 - p);
			alpha *= p;
		}
		return gamma;
	}
}
