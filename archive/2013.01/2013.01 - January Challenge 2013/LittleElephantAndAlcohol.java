package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LittleElephantAndAlcohol {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int sampleSize = in.readInt();
		int threshold = in.readInt();
		int[] level = IOUtils.readIntArray(in, count);
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < (1 << count); i++) {
			if (Integer.bitCount(i) >= answer)
				continue;
			boolean good = true;
			for (int j = 0; j <= count - sampleSize; j++) {
				int max = 0;
				for (int k = j; k < j + sampleSize; k++)
					max = Math.max(max, level[k] + (i >> k & 1));
				int countMax = 0;
				for (int k = j; k < j + sampleSize; k++) {
					if (max == level[k] + (i >> k & 1))
						countMax++;
				}
				if (countMax >= threshold) {
					good = false;
					break;
				}
			}
			if (good)
				answer = Integer.bitCount(i);
		}
		if (answer != Integer.MAX_VALUE)
			out.printLine(answer);
		else
			out.printLine(-1);
	}
}
