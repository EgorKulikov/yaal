package net.egork;

public class BrightLamps {
    public int maxBrightness(String init, int[] a, int K) {
		int oddAnswer = 0;
		int evenAnswer = 0;
		for (int i = 0; i < K; i++) {
			int sum = 0;
			int min = Integer.MAX_VALUE;
			int zeroes = 0;
			for (int j = i; j < a.length; j += K) {
				sum += a[j];
				min = Math.min(min, a[j]);
				if (init.charAt(j) == '0') {
					zeroes++;
				}
			}
			oddAnswer += sum - min;
			evenAnswer += sum - min;
			if (zeroes % 2 == 0) {
				evenAnswer += min;
			} else {
				oddAnswer += min;
			}
		}
		return Math.max(oddAnswer, evenAnswer);
    }
}
