package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		int answer = process(numbers);
		ArrayUtils.reverse(numbers);
		answer = Math.max(answer, process(numbers));
		out.printLine(answer);
    }

	private int process(int[] numbers) {
		int[] stack = new int[numbers.length];
		int size = 0;
		int result = 0;
		for (int i : numbers) {
			while (size > 0 && i > stack[size - 1])
				size--;
			if (size != 0)
				result = Math.max(result, stack[size - 1] ^ i);
			stack[size++] = i;
		}
		return result;
	}
}
