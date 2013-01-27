package net.egork;

import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int power = -1;
		int value;
		while (true) {
			value = in.read();
			if (value == -1 || value == '\n' || value == '\r')
				break;
			power++;
		}
		if (power == 0) {
			out.println(1);
			return;
		}
		if (power == 1) {
			out.println(46);
			return;
		}
		out.print(power * 45);
		for (int i = 0; i < power - 2; i++)
			out.print(0);
		out.println(1);
	}
}
