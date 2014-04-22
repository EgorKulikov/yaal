package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		double cost = in.readDouble();
		double rate = in.readDouble();
		double target = in.readDouble();
		double answer = target / 2;
		double totalRate = 2;
		double totalTime = 0;
		while (totalTime < answer) {
			totalTime += cost / totalRate;
			totalRate += rate;
			answer = Math.min(answer, target / totalRate + totalTime);
		}
		out.printLine("Case #" + testNumber + ":", answer);
    }
}
