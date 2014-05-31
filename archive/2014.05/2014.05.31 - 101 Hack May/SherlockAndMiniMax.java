package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class SherlockAndMiniMax {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int min = in.readInt();
		int max = in.readInt();
		int best = -1;
		int at = -1;
		Arrays.sort(array);
		int candidate = Integer.MAX_VALUE;
		for (int i : array)
			candidate = Math.min(candidate, Math.abs(min - i));
		if (candidate > best || candidate == best && at > min) {
			at = min;
			best = candidate;
		}
		candidate = Integer.MAX_VALUE;
		for (int i : array)
			candidate = Math.min(candidate, Math.abs(max - i));
		if (candidate > best || candidate == best && at > max) {
			at = max;
			best = candidate;
		}
		for (int i = 1; i < count; i++) {
			int current = (array[i] + array[i - 1]) / 2;
			if (current < min || current > max) {
				continue;
			}
			candidate = current - array[i - 1];
			if (candidate > best || candidate == best && at > current) {
				at = current;
				best = candidate;
			}
		}
		out.printLine(at);
	}
}
