package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		char[] order = in.readString().toCharArray();
		long index = in.readLong() - 1;
		char[] answer = new char[order.length];
		for (int i = 0; i < order.length; i++) {
			long step = IntegerUtils.factorial(order.length - i - 1);
			for (int j = 0; j < order.length; j++) {
				if (answer[j] != 0)
					continue;
				if (index >= step)
					index -= step;
				else {
					answer[j] = order[i];
					break;
				}
			}
		}
		out.println("Case " + testNumber + ": " + new String(answer));
	}
}
