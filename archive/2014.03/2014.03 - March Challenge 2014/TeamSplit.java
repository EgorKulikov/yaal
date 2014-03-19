package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TeamSplit {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long a = in.readInt();
		long b = in.readInt();
		long c = in.readInt();
		int d = in.readInt();
		int[] qty = new int[1000000];
		for (int i = 0; i < count; i++) {
			qty[d]++;
			d = (int) ((a * d * d + b * d + c) % 1000000);
		}
		long answer = 0;
		int sign = 1;
		for (int i = 999999; i >= 0; i--) {
			if ((qty[i] & 1) == 1) {
				answer += sign * i;
				sign = -sign;
			}
		}
		out.printLine(answer);
    }
}
