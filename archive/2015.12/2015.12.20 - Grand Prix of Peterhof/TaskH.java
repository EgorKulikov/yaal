package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] l = new int[n];
		int[] r = new int[n];
		IOUtils.readIntArrays(in, l, r);
		double[] d = new double[n];
		for (int i = 0; i < n; i++) {
			d[i] = 1d / (r[i] - l[i]);
		}
		double answer = 0;
		double val = 0;
		int max = -101;
		int secondMax = -101;
		for (int i = 0; i < n; i++) {
			if (l[i] > max) {
				secondMax = max;
				max = l[i];
			} else {
				secondMax = Math.max(secondMax, l[i]);
			}
		}
		int limit = -101;
		int bLimit = -101;
		for (int i = 0; i < n; i++) {
			if (r[i] > bLimit) {
				limit = bLimit;
				bLimit = r[i];
			} else {
				limit = Math.max(limit, r[i]);
			}
		}
		max = Math.min(max, limit);
//		int steps = 41500;
//		double delta = 0.5d / steps;
//		int qty = 0;
//		for (int i = 0; i < n; i++) {
//			if (r[i] > secondMax && l[i] <= secondMax) {
//				qty++;
//			}
//		}
//		if (qty == 1) {
//			for (int i = 0; i < n; i++) {
//				if (l[i] <= secondMax && r[i] > secondMax) {
//					val += 1d / (r[i] - l[i]);
//				}
//			}
//		}
//		answer = val * secondMax;
		for (int i = secondMax; i < max; i++) {
			Polynom polynom = new Polynom();
			for (int j = 0; j < n; j++) {
				if (l[j] <= i && r[j] > i) {
					polynom = polynom.multiply(-l[j], d[j]);
				}
			}
			for (int j = 0; j < n; j++) {
				if (l[j] <= i && r[j] > i) {
					Polynom current = polynom.divide(-l[j], 1);
					answer += current.value(i + 1) - current.value(i);
				}
			}
		}
//		for (int i = secondMax * steps + 1; i <= max * steps; i++) {
//			double main = 1;
//			double add = 0;
//			int at = (i - 1) / steps;
//			if (i <= 0) {
//				at--;
//			}
//			double x = i / ((double) steps);
//			for (int j = 0; j < n; j++) {
//				if (r[j] > at && l[j] <= at) {
//					double xdl = x - l[j];
//					main *= xdl * d[j];
//					add += 1 / xdl;
//				}
//			}
//			double cVal = add * main;
//			answer += cVal * x * ((i & 1) == 0 ? 2 : 4);
//			val = cVal;
//		}
//		if (max == secondMax && qty == 2) {
//			for (int i = 0; i < n; i++) {
//				if (l[i] <= max && r[i] > max) {
//					val += 1d / (r[i] - l[i]);
//				}
//			}
//			answer = val * secondMax;
//		}
		for (int i = max; i < limit; i++) {
			Polynom polynom = new Polynom();
			for (int j = 0; j < n; j++) {
				if (l[j] <= i && r[j] > i) {
					polynom = polynom.multiply(-l[j], d[j]);
				}
			}
			for (int j = 0; j < n; j++) {
				if (l[j] <= i && r[j] > i) {
					Polynom c1 = polynom.divide(-l[j], 1);
					for (int k = 0; k < n; k++) {
						if (l[k] <= i && r[k] > i && j != k) {
							Polynom current = c1.divide(-l[k], 1).multiply(-r[k], 1);
							answer += current.value(i) - current.value(i + 1);
						}
					}
				}
			}
		}
//		for (int i = max * steps + 1; i <= limit * steps; i++) {
//			double main = 1;
//			double add1 = 0;
//			double add2 = 0;
//			double sub = 0;
//			int at = (i - 1) / steps;
//			if (i <= 0) {
//				at--;
//			}
//			double x = i / ((double) steps);
//			for (int j = 0; j < n; j++) {
//				if (r[j] > at) {
//					double xdl = x - l[j];
//					main *= xdl * d[j];
//					double dxdl = 1 / xdl;
//					double ca1 = dxdl;
//					add1 += ca1;
//					double ca2 = (r[j] - x) * dxdl;
//					add2 += ca2;
//					sub += ca1 * ca2;
//				}
//			}
//			double cVal = main * (add1 * add2 - sub);
//			answer += cVal * x * ((i & 1) == 0 ? 2 : 4);
//			val = cVal;
//		}
//		answer -= val * limit;
		out.printLine(answer);
	}

	static class Polynom {
		double[] a;

		Polynom() {
			a = new double[2];
			a[1] = 1;
		}

		Polynom(double[] a) {
			this.a = a;
		}

		Polynom multiply(double delta, double div) {
			double[] b = new double[a.length + 1];
			b[0] = delta * a[0] * div;
			for (int i = 1; i < a.length; i++) {
				b[i] = (delta * a[i] + a[i - 1]) * div;
			}
			b[a.length] = a[a.length - 1] * div;
			return new Polynom(b);
		}

		Polynom divide(double delta, double div) {
			double[] b = new double[a.length - 1];
			b[b.length - 1] = a[a.length - 1] / div;
			for (int i = b.length - 2; i >= 0; i--) {
				b[i] = a[i + 1] / div - delta * b[i + 1];
			}
			return new Polynom(b);
		}

		public double value(int x) {
			double current = x;
			double answer = 0;
			for (int i = 0; i < a.length; i++) {
				answer += a[i] * current / (i + 1);
				current *= x;
			}
			return answer;
		}
	}
}
