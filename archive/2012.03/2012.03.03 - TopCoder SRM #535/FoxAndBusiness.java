package net.egork;

import java.util.Arrays;

public class FoxAndBusiness {
	public double minimumCost(int K, int totalWork, int[] a, int[] p) {
		double[] realCost = new double[p.length];
		for (int i = 0; i < p.length; i++)
			realCost[i] = p[i] + 3600. / a[i];
		double left = 0;
		double right = 1e18;
		double[] values = new double[p.length];
		for (int i = 0; i < 1000; i++) {
			double current = (left + right) / 2;
			for (int j = 0; j < p.length; j++)
				values[j] = (realCost[j] - current) * a[j];
			Arrays.sort(values);
			double sum = 0;
			for (int j = 0; j < K; j++)
				sum += values[j];
			if (sum <= 0)
				right = current;
			else
				left = current;
		}
		return totalWork * left;
	}


}

