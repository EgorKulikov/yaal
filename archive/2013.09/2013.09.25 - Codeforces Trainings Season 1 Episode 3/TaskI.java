package net.egork;

import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskI {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String value = in.readString();
		if ("0".equals(value))
			throw new UnknownError();
		String candidate = value.substring(0, (value.length() + 1) >> 1) +
			StringUtils.reverse(value.substring(0, value.length() >> 1));
		BigInteger answer;
		if (candidate.compareTo(value) >= 0)
			answer = new BigInteger(candidate).subtract(new BigInteger(value));
		else {
			char[] half = value.substring(0, (value.length() + 1) >> 1).toCharArray();
			for (int i = half.length - 1; i >= 0; i--) {
				if (half[i] == '9')
					half[i] = '0';
				else {
					half[i]++;
					break;
				}
			}
			candidate = new String(half) + StringUtils.reverse(new String(half).substring(0, value.length() >> 1));
			answer = new BigInteger(candidate).subtract(new BigInteger(value));
		}
		out.printLine(answer);
    }
}
