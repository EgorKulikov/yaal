package net.egork;

import java.util.Arrays;

public class BitwiseAnd {
    public long[] lexSmallest(long[] subset, int N) {
		for (int i = 0; i < subset.length; i++) {
			for (int j = i + 1; j < subset.length; j++) {
				if ((subset[i] & subset[j]) == 0)
					return new long[0];
				for (int k = j + 1; k < subset.length; k++) {
					if (((subset[i] & subset[j]) & subset[k]) != 0)
						return new long[0];
				}
			}
		}
		int more = N - subset.length;
		int[][] uniqueBits = new int[subset.length][more];
		for (int i = 0; i < subset.length; i++) {
			long remaining = subset[i];
			for (int j = 0; j < subset.length; j++) {
				if (i != j)
					remaining &= ~subset[j];
			}
			if (Long.bitCount(remaining) < more)
				return new long[0];
			int at = 0;
			for (int j = 0; j < 60 && at < more; j++) {
				if ((remaining >> j & 1) != 0)
					uniqueBits[i][at++] = j;
			}
		}
		long remaining = (1L << 60) - 1;
		for (long element : subset)
			remaining &= ~element;
		if (Long.bitCount(remaining) < more * (more - 1) / 2)
			return new long[0];
		int[][] freeBits = new int[more][more];
		int at = 0;
		int atColumn = 1;
		for (int j = 0; j < 60 && at < more - 1; j++) {
			if ((remaining >> j & 1) != 0) {
				freeBits[at][atColumn] = j;
				freeBits[atColumn][at] = j;
				atColumn++;
				if (atColumn == more) {
					at++;
					atColumn = at + 1;
				}
			}
		}
		long[] answer = Arrays.copyOf(subset, N);
		for (int i = 0; i < more; i++) {
			for (int j = 0; j < subset.length; j++)
				answer[i + subset.length] += 1L << uniqueBits[j][i];
			for (int j = 0; j < more; j++) {
				if (i != j)
					answer[i + subset.length] += 1L << freeBits[i][j];
			}
		}
		Arrays.sort(answer);
		return answer;
    }
}
