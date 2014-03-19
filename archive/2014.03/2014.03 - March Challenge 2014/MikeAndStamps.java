package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MikeAndStamps {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int offerCount = in.readInt();
		int[] inOffers = new int[count];
		for (int i = 0; i < offerCount; i++) {
			int size = in.readInt();
			for (int j = 0; j < size; j++) {
				int index = in.readInt() - 1;
				inOffers[index] += 1 << i;
			}
		}
		int[] forbidden = new int[offerCount];
		for (int i = 0; i < offerCount; i++) {
			for (int j : inOffers) {
				if ((j >> i & 1) == 1)
					forbidden[i] |= j;
			}
			forbidden[i] -= 1 << i;
		}
		int answer = 0;
		for (int i = 1; i < (1 << offerCount); i++) {
			if (Integer.bitCount(i) <= answer)
				continue;
			boolean good = true;
			for (int j = 0; j < offerCount; j++) {
				if ((i >> j & 1) == 1 && (i & forbidden[j]) != 0) {
					good = false;
					break;
				}
			}
			if (good)
				answer++;
		}
		out.printLine(answer);
    }
}
