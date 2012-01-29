package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Group {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[][] result = IOUtils.readIntTable(in, count, count);
		MiscUtils.decreaseByOne(result);
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (result[i][j] >= count) {
					out.printLine("No");
					return;
				}
			}
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++) {
					if (result[i][result[j][k]] != result[result[i][j]][k]) {
						out.printLine("No");
						return;
					}
				}
			}
		}
		int one = -1;
		for (int i = 0; i < count; i++) {
			boolean isOne = true;
			for (int j = 0; j < count && isOne; j++) {
				if (result[i][j] != j || result[j][i] != j)
					isOne = false;
			}
			if (isOne) {
				one = i;
				break;
			}
		}
		if (one == -1) {
			out.printLine("No");
			return;
		}
		for (int i = 0; i < count; i++) {
			boolean reverseFound = false;
			for (int j = 0; j < count && !reverseFound; j++) {
				if (result[i][j] == one && result[j][i] == one)
					reverseFound = true;
			}
			if (!reverseFound) {
				out.printLine("No");
				return;
			}
		}
		out.printLine("Yes");
	}
}
