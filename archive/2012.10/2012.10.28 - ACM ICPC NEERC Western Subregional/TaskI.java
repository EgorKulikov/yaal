package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[] words = IOUtils.readStringArray(in, count);
		Arrays.sort(words);
		long[] answer = new long[count];
		int[] length = new int[count];
		for (int i = 0; i < count; i++) {
			answer[i] = 1L << (40 - words[i].length());
			length[i] = words[i].length();
		}
		for (int i = 40; i >= 1; i--) {
			int start = 0;
			long sum = 0;
			String prefix = "";
			for (int j = 0; j < count; j++) {
				if (words[j].length() >= i && words[j].substring(0, i).equals(prefix)) {
					sum += answer[j];
					continue;
				}
				if (j - start > 1 && sum > (1L << (40 - i))) {
					answer[start] = 1L << (40 - i);
					length[start] = i;
					for (int k = start + 1; k < j; k++)
						answer[k] = 0;
				}
				if (words[j].length() >= i) {
					start = j;
					sum = answer[j];
					prefix = words[j].substring(0, i);
				} else {
					start = j + 1;
					sum = 0;
					prefix = "";
				}
			}
			if (count - start > 1 && sum > (1L << (40 - i))) {
				answer[start] = 1L << (40 - i);
				length[start] = i;
				for (int k = start + 1; k < count; k++)
					answer[k] = 0;
			}
		}
		long total = 0;
		int newSize = 0;
		for (long i : answer) {
			total += i;
			if (i != 0)
				newSize++;
		}
		out.printLine(total);
		out.printLine(newSize);
		for (int i = 0; i < count; i++) {
			if (answer[i] != 0)
				out.printLine(words[i].substring(0, length[i]));
		}
	}
}
