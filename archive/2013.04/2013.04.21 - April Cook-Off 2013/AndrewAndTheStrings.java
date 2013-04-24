package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AndrewAndTheStrings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int maxDistance = in.readInt();
		char[] first = in.readString().toCharArray();
		char[] second = in.readString().toCharArray();
		int answer = 0;
		for (int i = 0; i < length; i++)
			answer += calculate(first, second, length, maxDistance, i);
		for (int i = 1; i < length; i++)
			answer += calculate(second, first, length, maxDistance, i);
		out.printLine(answer);
    }

	private int calculate(char[] first, char[] second, int length, int maxDistance, int shift) {
		int curDistance = 0;
		int end = 0;
		int result = 0;
		for (int i = 0; i < length - shift; i++) {
			while (end < length - shift) {
				if (first[end] != second[end + shift]) {
					if (curDistance == maxDistance)
						break;
					curDistance++;
				}
				end++;
			}
			result += end - i;
			if (first[i] != second[i + shift])
				curDistance--;
		}
		return result;
	}
}
