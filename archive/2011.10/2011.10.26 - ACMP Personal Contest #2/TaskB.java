package net.egork;

import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String number = in.readString();
		if (number.matches("[ABCEHKMOPTXY][0-9][0-9][0-9][ABCEHKMOPTXY][ABCEHKMOPTXY]"))
			out.println("Yes");
		else
			out.println("No");
	}
}
