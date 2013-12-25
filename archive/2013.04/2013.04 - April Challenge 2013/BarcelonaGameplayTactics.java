package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BarcelonaGameplayTactics {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int passCount = in.readInt();
		int other = in.readInt();
		long messiWays = 1;
		long otherWays = 0;
		for (int i = 0; i < passCount; i++) {
			long nextMessiWays = otherWays;
			long nextOtherWays = (messiWays * other + otherWays * (other - 1)) % 1000000007;
			messiWays = nextMessiWays;
			otherWays = nextOtherWays;
		}
		out.printLine(messiWays);
    }
}
