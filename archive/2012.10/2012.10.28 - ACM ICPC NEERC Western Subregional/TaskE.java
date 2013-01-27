package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int userCount = in.readInt();
		int hubCount = in.readInt();
		int[] ports = IOUtils.readIntArray(in, hubCount);
		int[] order = ArrayUtils.order(ports);
		ArrayUtils.reverse(order);
		int sum = 0;
		for (int i = 0; i < hubCount; i++) {
			sum += ports[order[i]];
			if (sum >= 2 * i + userCount) {
				out.printLine(i + 1);
				for (int j = 0; j < i; j++)
					out.print(order[j] + 1, "");
				out.printLine(order[i] + 1);
				return;
			}
		}
		out.printLine("Epic fail");
	}
}
