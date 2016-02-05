package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class Fraud {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String pattern = in.readString().replace('*', '.');
		int n = in.readInt();
		List<String> answer = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String number = in.readString();
			if (number.matches(pattern))
				answer.add(number);
		}
		out.printLine(answer.size());
		answer.forEach(out::printLine);
	}
}
