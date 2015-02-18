package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class NewYearsResolution {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] target = IOUtils.readIntArray(in, 3);
		int count = in.readInt();
		int[][] food = IOUtils.readIntTable(in, count, 3);
		int[] result = new int[3];
		for (int i = 0; i < (1 << count); i++) {
			Arrays.fill(result, 0);
			for (int j = 0; j < count; j++) {
				if ((i >> j & 1) == 1) {
					for (int k = 0; k < 3; k++) {
						result[k] += food[j][k];
					}
				}
			}
			boolean ok = true;
			for (int j = 0; j < 3; j++) {
				if (target[j] != result[j]) {
					ok = false;
				}
			}
			if (ok) {
				out.printLine("Case #" + testNumber + ": yes");
				return;
			}
		}
		out.printLine("Case #" + testNumber + ": no");
    }
}
