package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class PizzaDelivery {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] distance = IOUtils.readIntArray(in, count);
        int[] fuel = IOUtils.readIntArray(in, count);
        int[] fills = new int[1001];
        Arrays.fill(fills, Integer.MAX_VALUE);
        fills[0] = 0;
        for (int i : fuel) {
            for (int j = 0; j <= 1000 - i; j++) {
                if (fills[j] != Integer.MAX_VALUE)
                    fills[j + i] = Math.min(fills[j + i], fills[j] + 1);
            }
        }
        int answer = 0;
        for (int i : distance)
            answer += fills[2 * i];
        out.printLine(answer);
    }
}
