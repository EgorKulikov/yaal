package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ProjectEuler1MultiplesOf3And5 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt() - 1;
		long answer = get(number / 3) * 3 + get(number / 5) * 5 - get(number / 15) * 15;
		out.printLine(answer);
    }

	private long get(long n) {
		return n * (n + 1) / 2;
	}
}
