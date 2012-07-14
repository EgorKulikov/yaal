package net.egork;

import java.util.Arrays;

public class MergersDivOne {
	public double findMaximum(int[] revenues) {
		Arrays.sort(revenues);
		double current = revenues[0];
		for (int i = 1; i < revenues.length; i++)
			current = (current + revenues[i]) / 2;
		return current;
	}


}

