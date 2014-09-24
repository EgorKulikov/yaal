package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ConsecutiveSubsequences {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int modulo = in.readInt();
		int[] sequence = IOUtils.readIntArray(in, count);
		int[] answer = new int[modulo];
		answer[0] = 1;
		long result = 0;
		int sum = 0;
		for (int i : sequence) {
			sum += i;
			sum %= modulo;
			result += answer[sum]++;
		}
		out.printLine(result);
    }
}
