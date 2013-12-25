package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Labelmaker {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] allowed = in.readString().toCharArray();
		Arrays.sort(allowed);
		long total = in.readLong();
		long level = 1;
		int length = 1;
		while (total / allowed.length > level) {
			level *= allowed.length;
			total -= level;
			length++;
		}
		char[] answer = new char[length];
		total--;
		for (int i = 0; i < length; i++) {
			answer[i] = allowed[((int) (total / level))];
			total %= level;
			level /= allowed.length;
		}
		out.printLine("Case #" + testNumber + ":", new String(answer));
    }
}
