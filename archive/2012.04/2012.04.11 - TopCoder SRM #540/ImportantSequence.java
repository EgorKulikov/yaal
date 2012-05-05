package net.egork;

public class ImportantSequence {
	public int getCount(int[] B, String operators) {
		int plusPosition = operators.indexOf('+');
		if (plusPosition == -1)
			return -1;
		long from = 0;
		long to = B[plusPosition];
		long delta = 0;
		int coefficient = 1;
		for (int i = plusPosition - 1; i >= 0; i--) {
			if (operators.charAt(i) == '-')
				delta += B[i];
			else {
				coefficient = -coefficient;
				delta = B[i] - delta;
			}
			if (coefficient >= 0)
				from = Math.max(from, -delta);
			else
				to = Math.min(to, delta);
		}
		delta = 0;
		coefficient = 1;
		for (int i = plusPosition; i < operators.length(); i++) {
			if (operators.charAt(i) == '-')
				delta -= B[i];
			else {
				coefficient = -coefficient;
				delta = B[i] - delta;
			}
			if (coefficient >= 0)
				from = Math.max(from, -delta);
			else
				to = Math.min(to, delta);
		}
		if (to <= from)
			return 0;
		return (int) (to - from - 1);
	}


}

