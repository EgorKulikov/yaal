package net.egork;

import net.egork.string.StringUtils;

public class FlippingBitsDiv1 {
    public int getmin(String[] S, int M) {
		char[] bits = StringUtils.unite(S).toCharArray();
		int count = bits.length;
		int last = count - count % M;
		if (last == count)
			last -= M;
		if (M * M <= count) {
			int answer = Integer.MAX_VALUE;
			for (int i = 0; i < (1 << M); i++) {
				int costFlipped = Integer.MAX_VALUE / 2;
				int costNotFlipped = 0;
				for (int j = last; j >= 0; j -= M) {
					int current = 0;
					for (int k = j; k < j + M && k < count; k++) {
						if (bits[k] != (i >> (k - j) & 1) + '0')
							current++;
					}
					int length = Math.min(M, count - j);
					int nextCostNotFlipped = Math.min(costNotFlipped + current, 1 + costFlipped + current);
					int nextCostFlipped = Math.min(costFlipped + length - current, 1 + costNotFlipped + length - current);
					costFlipped = nextCostFlipped;
					costNotFlipped = nextCostNotFlipped;
				}
				answer = Math.min(answer, Math.min(costFlipped, costNotFlipped));
			}
			return answer;
		} else {
			int parts = (count + M - 1) / M;
			int answer = Integer.MAX_VALUE;
			for (int i = 0; i < (1 << parts); i++) {
				int current = Integer.bitCount(i);
				for (int j = 0; j < M; j++) {
					int value = 0;
					int l = parts - 1;
					int ones = 0;
					for (int k = last + j; k >= 0; k -= M) {
						value ^= (i >> l & 1);
						if (k < count && bits[k] == ('0' + value))
							ones++;
						l--;
					}
					int zeroes = (last + j < count ? parts : parts - 1) - ones;
					current += Math.min(ones, zeroes);
				}
				answer = Math.min(answer, current);
			}
			return answer;
		}
    }
}
