package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SkyScraper {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int maxWeight = in.readInt();
		int[] weight = IOUtils.readIntArray(in, count);
		boolean[] subSetWeight = new boolean[1 << count];
		for (int i = 0; i < subSetWeight.length; i++) {
			long totalWeight = 0;
			for (int j = 0; j < count; j++) {
				if ((i >> j & 1) == 1)
					totalWeight += weight[j];
			}
			subSetWeight[i] = totalWeight <= maxWeight;
		}
		int[] answer = new int[1 << count];
		int[] last = new int[1 << count];
		int length = answer.length / 2;
		for (int i = 1; i < length; i++) {
			int lastCow = Integer.highestOneBit(i);
			int otherMask = i - lastCow;
			answer[i] = Integer.MAX_VALUE;
			for (int j = otherMask; ; j = (j - 1) & otherMask) {
				if (answer[i] > answer[j] && subSetWeight[i - j]) {
					answer[i] = answer[j] + 1;
					last[i] = i - j;
				}
				if (j == 0)
					break;
			}
		}
		int otherMask = length - 1;
		int i = answer.length - 1;
		answer[i] = Integer.MAX_VALUE;
		for (int j = otherMask; ; j = (j - 1) & otherMask) {
			if (answer[i] > answer[j] && subSetWeight[i - j]) {
				answer[i] = answer[j] + 1;
				last[i] = i - j;
			}
			if (j == 0)
				break;
		}
		out.printLine(answer[i]);
		while (i != 0) {
			out.print(Integer.bitCount(last[i]));
			for (int j = 0; j < count; j++) {
				if ((last[i] >> j & 1) == 1)
					out.print(" " + (j + 1));
			}
			out.printLine();
			i -= last[i];
		}
	}
}
