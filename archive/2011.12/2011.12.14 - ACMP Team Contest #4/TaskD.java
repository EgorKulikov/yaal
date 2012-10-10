package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class TaskD {
	private Map<Integer, Integer> result = new HashMap<Integer, Integer>();

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int from = in.readInt();
		int to = in.readInt();
		long answer = 0;
		result.put(2, 0);
		for (int i = from; i <= to; i++)
			answer += go(i);
		out.printLine(answer);
	}

	private int go(int number) {
		if (result.containsKey(number))
			return result.get(number);
		int answer;
		if ((number & 1) == 0)
			answer = 1 + go(number >> 1);
		else
			answer = 1 + go(3 * number + 1);
		result.put(number, answer);
		return answer;
	}
}
