package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int from = in.readInt();
		in.readInt();
		int girlFriendCount = in.readInt();
		int boyFriendCount = in.readInt();
		long gcd = IntegerUtils.gcd(boyFriendCount, girlFriendCount);
		long count = (girlFriendCount + boyFriendCount) / gcd;
		long times = Math.max((from + count - 1) / count, gcd);
		out.println(times * count + " " + times * boyFriendCount / gcd + " " + times * girlFriendCount / gcd);
	}
}
