package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private int index;
	private char[] sequence;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		sequence = in.readString().toCharArray();
		index = 0;
		int answer = go();
		if (index != sequence.length || answer == -1)
			out.printLine("Not Possible");
		else
			out.printLine("2^" + answer + "*2^" + answer);
	}

	private int go() {
		if (index == sequence.length)
			return -1;
		if (sequence[index++] != '2')
			return 0;
		if (sequence.length - index < 4)
			return -1;
		if (sequence[index] != '2') {
			boolean good = false;
			for (int i = 1; i < 4 && !good; i++) {
				if (sequence[index + i] != sequence[index])
					good = true;
			}
			if (!good)
				return -1;
		}
		int result = 0;
		for (int i = 0; i < 4; i++) {
			int call = go();
			if (call == -1)
				return -1;
			result = Math.max(result, call);
		}
		return result + 1;
	}
}
