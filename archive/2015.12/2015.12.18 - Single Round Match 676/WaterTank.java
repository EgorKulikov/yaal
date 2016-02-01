package net.egork;

public class WaterTank {
	public double minOutputRate(int[] t, int[] x, int C) {
		double left = 0;
		double right = 1000000;
		for (int i = 0; i < 100; i++) {
			double middle = (left + right) / 2;
			double filled = 0;
			boolean good = true;
			for (int j = 0; j < t.length; j++) {
				filled += (x[j] - middle) * t[j];
				if (filled > C) {
					good = false;
					break;
				}
				filled = Math.max(filled, 0);
			}
			if (good)
				right = middle;
			else
				left = middle;
		}
		return (left + right) / 2;
	}
}
