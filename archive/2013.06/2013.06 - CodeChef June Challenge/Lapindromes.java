package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Lapindromes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		char[] first = s.substring(0, s.length() / 2).toCharArray();
		char[] second = s.substring((s.length() + 1) / 2).toCharArray();
		Arrays.sort(first);
		Arrays.sort(second);
		if (Arrays.equals(first, second))
			out.printLine("YES");
		else
			out.printLine("NO");
    }
}
