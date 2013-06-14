package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[] numbers = IOUtils.readStringArray(in, count);
		String prefix = numbers[0];
		for (String number : numbers) {
			if (prefix.length() > number.length())
				prefix = prefix.substring(0, number.length());
			for (int i = 0; i < prefix.length(); i++) {
				if (prefix.charAt(i) != number.charAt(i)) {
					prefix = prefix.substring(0, i);
					break;
				}
			}
		}
		out.printLine(prefix.length());
	}
}
