package net.egork;

import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int length = in.readInt();
		if (length % 3 == 2)
			length++;
		int inches = length / 3;
		int feet = inches / 12;
		inches %= 12;
		out.println(feet + " " + inches);
	}
}
