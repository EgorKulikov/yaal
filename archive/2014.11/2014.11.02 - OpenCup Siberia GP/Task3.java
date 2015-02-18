package net.egork;

import net.egork.string.StringHash;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task3 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		int[] z = StringUtils.zAlgorithm(s);
		for (long k = 2; ; k++) {
			boolean good = true;
			for (long l = k - 1; l < s.length(); l = l * k + (k - 1)) {
				for (long m = 1; m < k && m * (l + 1) < s.length(); m++) {
					if (z[((int) (m * (l + 1)))] < Math.min(l, s.length() - m * (l + 1))) {
						good = false;
						break;
					}
				}
			}
			if (good) {
				out.printLine(k);
				return;
			}
		}
    }
}
