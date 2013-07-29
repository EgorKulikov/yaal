package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Taxi {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long totalDistance = in.readLong();
		long parkDistance = in.readLong();
		int count = in.readInt();
		long[] fuel = IOUtils.readLongArray(in, count);
		Arrays.sort(fuel);
		int answer = 0;
		int special = Arrays.binarySearch(fuel, totalDistance - parkDistance);
		if (special < 0)
			special = -special - 1;
		if (special == count) {
			out.printLine(0);
			return;
		}
		totalDistance -= parkDistance;
		for (int i = count - 1; i >= 0; i--) {
			if (fuel[i] >= totalDistance + 2 * parkDistance || fuel[special] >= totalDistance + 2 * parkDistance) {
				out.printLine(answer + 1);
				return;
			}
			if (i == special)
				continue;
			if (fuel[i] >= 2 * parkDistance) {
				out.printLine(answer + 2);
				return;
			}
			answer++;
			parkDistance -= Math.max(0, fuel[i] - parkDistance);
		}
		if (fuel[special] >= totalDistance + 2 * parkDistance) {
			out.printLine(answer + 1);
			return;
		}
		out.printLine(0);
    }
}
