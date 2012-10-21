package net.egork;

public class Pillars {
	public double getExpectedLength(int w, int x, int y) {
		double[] distance = new double[x + y + 1];
		for (int i = 0; i <= x + y; i++)
			distance[i] = Math.sqrt(w * w + (double)(i - x) * (i - x));
		double answer = 0;
		double current = 0;
		for (int i = x + 1; i <= x + y; i++)
			current += distance[i];
		for (int i = x; i >= 1; i--) {
			current -= distance[i + y];
			current += distance[i];
			answer += current;
		}
		return answer / x / y;
	}


}

