package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int balance = in.readInt();
		int answer = balance;
		answer = Math.max(answer, balance / 10);
		answer = Math.max(answer, balance / 10 - balance / 10 % 10 + balance % 10);
		out.printLine(answer);
	}
}
