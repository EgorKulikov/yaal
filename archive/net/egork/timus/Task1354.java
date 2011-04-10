package net.egork.timus;

import net.egork.string.StringHash;
import net.egork.string.StringUtils;
import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class Task1354 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String sample = in.readString();
		StringHash hash = new StringHash(sample);
		StringHash reverseHash = new StringHash(StringUtils.reverse(sample));
		for (int i = 1; i < sample.length(); i++) {
			if (hash.hash(i, (i + sample.length()) / 2) == reverseHash.hash(0, (i + sample.length()) / 2 - i)) {
				out.println(sample + StringUtils.reverse(sample.substring(0, i)));
				return;
			}
		}
		out.println(sample + StringUtils.reverse(sample));
	}
}

