package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int letterCount = in.readInt();
		if (letterCount == 0)
			throw new UnknownError();
		int length = in.readInt();
		int count = in.readInt();
		char[][] valid = new char[count][];
		for (int i = 0; i < count; i++)
			valid[i] = in.readString().toCharArray();
		if (length < valid[0].length) {
//			out.printLine(IntegerUtils.power(letterCount, length));
			out.printLine(0);
			return;
		}
		if (valid[0].length == 1) {
			out.printLine(IntegerUtils.power(count, length));
			return;
		}
		int[] first = new int[count];
		int[] next = new int[count];
		Arrays.fill(first, -1);
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				boolean good = true;
				for (int k = 1; k < valid[i].length; k++) {
					if (valid[i][k] != valid[j][k - 1]) {
						good = false;
						break;
					}
				}
				if (good) {
					next[j] = first[i];
					first[i] = j;
				}
			}
		}
		long[] answer = new long[count];
		Arrays.fill(answer, 1);
		long[] current = new long[count];
		for (int i = valid[0].length; i < length; i++) {
			Arrays.fill(current, 0);
			for (int j = 0; j < count; j++) {
				for (int k = first[j]; k != -1; k = next[k])
					current[k] += answer[j];
			}
			long[] temp = current;
			current = answer;
			answer = temp;
		}
		long result = 0;
		for (long i : answer)
			result += i;
		out.printLine(result);
    }
}
