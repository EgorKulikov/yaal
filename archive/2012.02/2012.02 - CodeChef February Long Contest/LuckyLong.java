package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LuckyLong {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] number = in.readString().toCharArray();
		int result = 0;
		for (char c : number) {
			if (c != '4' && c != '7')
				result++;
		}
		out.printLine(result);
	}
}
