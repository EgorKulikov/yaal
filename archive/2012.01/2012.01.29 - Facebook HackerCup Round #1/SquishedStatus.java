package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class SquishedStatus {
	static final long MOD = 0xfaceb00cL;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int maxCode = in.readInt();
		Set<String> codes = new HashSet<String>();
		for (int i = 1; i <= maxCode; i++)
			codes.add(Integer.toString(i));
		String encoding = in.readString();
		long[] answer = new long[encoding.length() + 1];
		answer[0] = 1;
		for (int i = 0; i < encoding.length(); i++) {
			answer[i] %= MOD;
			for (int j = 1; j <= 3 && i + j <= encoding.length(); j++) {
				if (codes.contains(encoding.substring(i, i + j)))
					answer[i + j] += answer[i];
			}
		}
		answer[encoding.length()] %= MOD;
		out.printLine("Case #" + testNumber + ":", answer[encoding.length()]);
	}
}
