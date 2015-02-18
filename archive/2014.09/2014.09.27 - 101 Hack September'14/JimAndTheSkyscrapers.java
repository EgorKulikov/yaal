package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class JimAndTheSkyscrapers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] heights = IOUtils.readIntArray(in, count);
		int[] stack = new int[count];
		int[] qty = new int[count];
		int top = -1;
		long answer = 0;
		for (int i : heights) {
			while (top >= 0 && i > stack[top]) {
				top--;
			}
			if (top >= 0 && stack[top] == i) {
				answer += 2 * qty[top];
				qty[top]++;
			} else {
				stack[++top] = i;
				qty[top] = 1;
			}
		}
		out.printLine(answer);
    }
}
