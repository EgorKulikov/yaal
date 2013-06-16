package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long money = in.readLong();
		long[] bets = IOUtils.readLongArray(in, in.readInt());
		bets = Arrays.copyOf(bets, 37);
		Arrays.sort(bets);
		double answer = 0;
		for (int i = 1; i < 37; i++) {
			for (int j = i; j <= 37; j++) {
				long max = j == 37 ? Long.MAX_VALUE / 100 : bets[j] - 1;
				if (max < bets[j - 1])
					continue;
				long required = 0;
				long win = 0;
				for (int k = 0; k < j; k++) {
					required += bets[j - 1] - bets[k];
					if (k < i)
						win += bets[j - 1] - bets[k];
				}
				required += j - i;
				if (required > money)
					continue;
				long toAdd = Math.min(max - bets[j - 1], (money - required) / j);
				required += toAdd * j;
				win += toAdd * i;
				answer = Math.max(answer, 36d / i * win - required);
			}
		}
		out.printLine("Case #" + testNumber + ":", answer);
    }
}
