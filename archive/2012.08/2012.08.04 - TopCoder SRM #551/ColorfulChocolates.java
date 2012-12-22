package net.egork;

public class ColorfulChocolates {
	public int maximumSpread(String chocolates, int maxSwaps) {
		int count = chocolates.length();
		int answer = 0;
		for (int i = 0; i < count; i++) {
			for (int j = i; j < count; j++) {
				if (chocolates.charAt(i) != chocolates.charAt(j))
					continue;
				int total = 0;
				for (int k = i; k <= j; k++) {
					if (chocolates.charAt(k) == chocolates.charAt(i))
						total++;
				}
				if (total <= answer)
					continue;
				int swapsRequired = 0;
				int toLeft = 0;
				int toRight = total;
				for (int k = i; k <= j; k++) {
					if (chocolates.charAt(k) == chocolates.charAt(i)) {
						toLeft++;
						toRight--;
					} else
						swapsRequired += Math.min(toLeft, toRight);
				}
				if (swapsRequired <= maxSwaps)
					answer = total;
			}
		}
		return answer;
	}


}

