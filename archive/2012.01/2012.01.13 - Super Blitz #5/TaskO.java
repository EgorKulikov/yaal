package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class TaskO {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String numbers = in.readLine();
		if ("-1".equals(numbers))
			throw new UnknownError();
		String[] tokens = numbers.split(" ");
		int[] array = new int[tokens.length - 1];
		for (int i = 0; i < array.length; i++)
			array[i] = Integer.parseInt(tokens[i]);
		Set<Integer> set = new HashSet<Integer>(Array.wrap(array));
		int answer = 0;
		for (int i : array) {
			if (set.contains(2 * i))
				answer++;
		}
		out.printLine(answer);
	}
}
