package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		char[] first = in.readString().toCharArray();
		char[] second = in.readString().toCharArray();
		double total = 0;
		for (char c = 'A'; c <= 'Z'; c++) {
			long sum = 0;
			for (int i = 0; i < length; i++) {
				if (first[i] == c)
					sum += i + 1;
				if (second[i] == c)
					total += sum * (length - i);
			}
			sum = 0;
			for (int i = length - 1; i >= 0; i--) {
				if (second[i] == c)
					total += sum * (i + 1);
				if (first[i] == c)
					sum += length - i;
			}
		}
		double pairCount = 0;
		for (int i = 0; i < length; i++)
			pairCount += (long)(length - i) * (length - i);
		out.printLine(total / pairCount);
	}
}
