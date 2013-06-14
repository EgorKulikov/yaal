package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int count = in.readInt();
		int[] answer = new int[count + 1];
		for (int i = 0; i < rowCount; i++) {
			int length = in.readInt();
			int[] price = IOUtils.readIntArray(in, length);
			int[] maxCost = new int[length + 1];
			int sumLeft = 0;
			for (int j = 0; j <= length; j++) {
				int sumRight = 0;
				for (int k = length; k >= j; k--) {
					maxCost[j + length - k] = Math.max(maxCost[j + length - k], sumLeft + sumRight);
					if (k != 0)
						sumRight += price[k - 1];
				}
				if (j != length)
					sumLeft += price[j];
			}
			for (int j = count; j >= 0; j--) {
				for (int k = 1; k <= j && k <= length; k++)
					answer[j] = Math.max(answer[j], answer[j - k] + maxCost[k]);
			}
		}
		out.printLine(answer[count]);
	}
}
