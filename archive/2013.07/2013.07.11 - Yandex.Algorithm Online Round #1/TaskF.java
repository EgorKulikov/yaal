package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int length = in.readInt();
		char[][] stickers = IOUtils.readTable(in, count, length);
		char[] target = in.readString().toCharArray();
		int[] leftEnd = new int[target.length + 1];
		int[] rightEnd = new int[target.length + 1];
		Arrays.fill(leftEnd, Integer.MAX_VALUE);
		Arrays.fill(rightEnd, Integer.MAX_VALUE);
		leftEnd[0] = 0;
		for (int i = 0; i < target.length; i++) {
			if (leftEnd[i] != Integer.MAX_VALUE && i + length <= target.length) {
				for (int j = 0; j < count; j++) {
					boolean good = true;
					for (int k = 0; k < length; k++) {
						if (target[i + k] != stickers[j][k]) {
							good = false;
							break;
						}
						leftEnd[i + k + 1] = Math.min(leftEnd[i + k + 1], leftEnd[i] + 1);
					}
					if (good)
						rightEnd[i + length] = Math.min(rightEnd[i + length], leftEnd[i] + 1);
				}
			}
			if (rightEnd[i] != Integer.MAX_VALUE) {
				for (int j = 0; j < count; j++) {
					for (int k = 0; k <= i && k < length; k++) {
						boolean good = true;
						for (int l = k; l < length && i + l - k < target.length; l++) {
							if (target[i + l - k] != stickers[j][l]) {
								good = false;
								break;
							}
							leftEnd[i + l - k + 1] = Math.min(leftEnd[i + l - k + 1], rightEnd[i] + 1);
						}
						if (good && i + length - k <= target.length)
							rightEnd[i + length - k] = Math.min(rightEnd[i + length - k], rightEnd[i] + 1);
					}
				}
			}
		}
		if (rightEnd[target.length] == Integer.MAX_VALUE)
			out.printLine("NO");
		else
			out.printLine(rightEnd[target.length]);
    }
}
