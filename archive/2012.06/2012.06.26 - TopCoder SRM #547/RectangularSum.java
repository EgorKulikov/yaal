package net.egork;

public class RectangularSum {
	public long minimalArea(int height, int width, long S) {
		long answer = Long.MAX_VALUE;
		for (long i = 1; i * i <= 2 * S; i++) {
			if (2 * S % i == 0) {
				if (canDo(2 * S / i, i, height, width))
					answer = Math.min(answer, i);
				if (canDo(i, 2 * S / i, height, width))
					answer = Math.min(answer, 2 * S / i);
			}
		}
		if (answer == Long.MAX_VALUE)
			answer = -1;
		return answer;
	}

	private boolean canDo(long remaining, long product, long height, long width) {
		for (long i = 1; i * i <= product; i++) {
			if (product % i == 0) {
				if (canDo(remaining, i, product / i, height, width) || canDo(remaining, product / i, i, height, width))
					return true;
			}
		}
		return false;
	}

	private boolean canDo(long remaining, long r, long c, long height, long width) {
		if (r > height || c > width)
			return false;
		remaining -= c - 1 + (r - 1) * width;
		if (remaining % 2 != 0)
			return false;
		remaining /= 2;
		if (remaining < 0)
			return false;
		long a = remaining / width;
		long b = remaining % width;
		if (a + r > height || b + c > width)
			return false;
		return true;
	}


}

