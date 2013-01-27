package net.egork;

public class CorrectMultiplication {
	public long getMinimum(int a, int b, int c) {
		long result = Long.MAX_VALUE;
		for (int i = 1; i * i <= 2 * c; i++) {
			long current = check(i, c / i, a, b, c);
			result = Math.min(result, current);
			current = check(i, c / i + 1, a, b, c);
			result = Math.min(result, current);
		}
		return result;
	}

	private long check(long A, long B, int a, int b, int c) {
		return Math.abs(Math.min(a, b) - Math.min(A, B)) + Math.abs(Math.max(a, b) - Math.max(A, B)) + Math.abs(c - A * B);
	}


}

