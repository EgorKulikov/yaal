package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] stack = new int[3 * count];
		int size = 0;
		for (int i = 0; i < count; i++) {
			int operation = in.readInt();
			if (operation == -1)
				out.printLine(stack[--size]);
			else if (operation > 0)
				stack[size++] = operation;
			else if (size < count - i - 1) {
				System.arraycopy(stack, 0, stack, size, size);
				size <<= 1;
			}
		}
	}
}
