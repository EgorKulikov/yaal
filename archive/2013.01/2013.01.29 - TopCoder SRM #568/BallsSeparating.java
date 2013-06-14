package net.egork;

public class BallsSeparating {
    public int minOperations(int[] red, int[] green, int[] blue) {
		int count = red.length;
		if (count <= 2)
			return -1;
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (i == j)
					continue;
				for (int k = 0; k < count; k++) {
					if (i == k || j == k)
						continue;
					int current = 0;
					for (int l = 0; l < count; l++) {
						if (l == i)
							current += green[l] + blue[l];
						else if (l == j)
							current += red[l] + blue[l];
						else if (l == k)
							current += red[l] + green[l];
						else
							current += red[l] + green[l] + blue[l] - Math.max(red[l], Math.max(green[l], blue[l]));
					}
					answer = Math.min(answer, current);
				}
			}
		}
		return answer;
    }
}
