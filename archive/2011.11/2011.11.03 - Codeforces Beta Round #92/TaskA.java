package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		boolean[] notSame = new boolean[s.length];
		notSame[0] = true;
		int same = s.length - 1;
		for (int i = s.length / 2 + 1; i <= s.length; i++) {
			if (IntegerUtils.isPrime(i)) {
				same--;
				notSame[i - 1] = true;
			}
		}
		for (char c = 'a'; c <= 'z'; c++) {
			if (CollectionUtils.count(Array.wrap(s), c) >= same) {
				out.printLine("YES");
				char[] result = new char[s.length];
				for (int i = 0; i < result.length; i++) {
					if (!notSame[i])
						result[i] = c;
					else {
						for (int j = 0; j < s.length; j++) {
							if (s[j] != 0 && s[j] != c) {
								result[i] = s[j];
								s[j] = 0;
								break;
							}
						}
						if (result[i] == 0)
							result[i] = c;
					}
				}
				out.printLine(new String(result));
				return;
			}
		}
		out.printLine("NO");
	}
}
