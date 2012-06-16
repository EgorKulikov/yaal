package net.egork;

import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;
import java.math.BigInteger;

public class FirstNonPalindrome {
	private StringHash direct;
	private StringHash reverse;

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String s = in.readString();
		if (s.length() == 1) {
			out.println(0);
			return;
		}
		direct = new SimpleStringHash(s);
		reverse = new SimpleStringHash(StringUtils.reverse(s));
		if (s.charAt(0) != s.charAt(1)) {
			int firstCycleEnd = -1;
			for (int i = 2; i < s.length(); i++) {
				if (s.charAt(i) != s.charAt(i - 2)) {
					firstCycleEnd = i;
					break;
				}
			}
			long result = 0;
			long power = 1;
			for (int i = s.length(); i >= 2; i--) {
				if (i % 2 == 0 || firstCycleEnd != -1 && firstCycleEnd <= i) {
					if (isPalindrome(0, i, s.length())) {
						if (i != s.length())
							result += 2 * power;
					} else
						result += power;
				} else if (firstCycleEnd != -1)
					result += (firstCycleEnd - i + 2) * power;
				power *= 100007;
			}
			BigInteger printResult = BigInteger.valueOf(result);
			if (result < 0)
				printResult = printResult.add(BigInteger.valueOf(2).pow(64));
			out.println(printResult);
		} else {
			int firstCycleEnd = -1;
			for (int i = 1; i < s.length(); i++) {
				if (s.charAt(i) != s.charAt(i - 1)) {
					firstCycleEnd = i;
					break;
				}
			}
			if (firstCycleEnd == -1) {
				out.println(0);
				return;
			}
			long result = 0;
			long power = 1;
			for (int i = s.length(); i >= 2; i--) {
				if (firstCycleEnd <= i) {
					if (isPalindrome(0, i, s.length())) {
						if (i != s.length())
							result += 2 * power;
					} else
						result += power;
				} else if (firstCycleEnd != -1)
					result += (firstCycleEnd - i + 2) * power;
				power *= 100007;
			}
			BigInteger printResult = BigInteger.valueOf(result);
			if (result < 0)
				printResult = printResult.add(BigInteger.valueOf(2).pow(64));
			out.println(printResult);
		}
	}

	private boolean isPalindrome(int from, int to, int length) {
		return direct.hash(from, to) == reverse.hash(length - to, length - from);
	}
}
