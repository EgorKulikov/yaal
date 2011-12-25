package net.egork;

public class MagicBlizzard {
	public double expectation(int[] range, int[] amount) {
		double answer = 0;
		int[] countSquares = new int[10001];
		for (int i = 0; i <= 10000; i++)
			countSquares[i] = (2 * i + 1) * (2 * i + 1);
		for (int i = 0; i <= 10000; i++) {
			double d = 0;
			double m = 0;
			for (int j = 0; j < range.length; j++) {
				if (range[j] >= i) {
					double p = 1. / countSquares[range[j]];
					d += (p - p * p) * amount[j];
					m += p * amount[j];
				}
			}
			long count = i == 0 ? 1 : (2 * i + 1) * (2 * i + 1) - (2 * i - 1) * (2 * i - 1);
			answer += count * (d + m * m);
		}
		return answer;
	}


}

