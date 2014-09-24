package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	static final int MOD = (int) (1e7 - 3);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] limit = in.readString().toCharArray();
		char[] number = in.readString().toCharArray();
		long less = 1;
		long more = 0;
		for (int i = limit.length - 1; i >= 0; i--) {
			char lDigit = limit[i];
			char nDigit = i + number.length >= limit.length ? number[i + number.length - limit.length] : '0';
			long nLess;
			long nMore;
			if (nDigit != '6' && nDigit != '9') {
				if (lDigit > nDigit) {
					nLess = less + more;
					nMore = 0;
				} else if (lDigit == nDigit) {
					nLess = less;
					nMore = more;
				} else {
					nLess = 0;
					nMore = less + more;
				}
			} else {
				if (lDigit < '6') {
					nLess = 0;
					nMore = 2 * less + 2 * more;
				} else if (lDigit == '6') {
					nLess = less;
					nMore = less + 2 * more;
				} else if (lDigit < '9') {
					nLess = less + more;
					nMore = less + more;
				} else {
					nLess = 2 * less + more;
					nMore = more;
				}
			}
			less = nLess % MOD;
			more = nMore % MOD;
		}
		out.printLine(less);
    }
}
