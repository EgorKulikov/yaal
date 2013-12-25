package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class HelloHello {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		double cost = in.readDouble();
		int minutes = in.readInt();
		int count = in.readInt();
		int answer = 0;
		for (int i = 1; i <= count; i++) {
			int length = in.readInt();
			double rate = in.readDouble();
			int activation = in.readInt();
			double realRate = rate + (double)activation / (length * minutes);
			if (realRate < cost) {
				cost = realRate;
				answer = i;
			}
		}
		out.printLine(answer);
    }
}
