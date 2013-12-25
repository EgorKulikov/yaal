package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CoinsGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int countJars = in.readInt();
		int countCoins = in.readInt();
		int requiredCoins = in.readInt();
		int guaranteedCoins = countCoins / countJars * countJars;
		if (requiredCoins <= guaranteedCoins) {
			out.printLine("Case #" + testNumber + ":", requiredCoins);
			return;
		}
		for (int i = countJars; i >= 1; i--) {
			int eachFull = countCoins / i;
			int remaining = countCoins - eachFull * i;
			long capacity = (long)eachFull * (countJars - i);
			if (capacity >= remaining) {
				out.printLine("Case #" + testNumber + ":", requiredCoins + countJars - i);
				return;
			}
		}
		throw new RuntimeException();
    }
}
