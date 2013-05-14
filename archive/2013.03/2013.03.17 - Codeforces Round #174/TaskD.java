package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] values = IOUtils.readLongArray(in, count);
		int[] twoPower = new int[count];
		long[] oddPart = new long[count];
		for (int i = 0; i < count; i++) {
			oddPart[i] = values[i];
			while ((oddPart[i] & 1) == 0) {
				oddPart[i] >>= 1;
				twoPower[i]++;
			}
		}
		int[] answer = new int[count];
		for (int i = 0; i < count; i++) {
			answer[i] = 1;
			for (int j = 0; j < i; j++) {
				if ((twoPower[i] < i - j || twoPower[i] == twoPower[j] + i - j) && answer[j] >= answer[i] && oddPart[j] % oddPart[i] == 0)
					answer[i] = answer[j] + 1;
			}
		}
		int result = count;
		for (int i : answer)
			result = Math.min(result, count - i);
		out.printLine(result);
    }
}
