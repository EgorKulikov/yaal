package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int sausageCount = in.readInt();
		int tasterCount = in.readInt();
		int gcd = IntegerUtils.gcd(sausageCount, tasterCount);
		int answer = tasterCount - gcd;
		out.printLine(answer);
    }
}
