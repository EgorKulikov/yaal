package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class RacingHorses {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] skill = IOUtils.readIntArray(in, count);
		Arrays.sort(skill);
		int answer = Integer.MAX_VALUE;
		for (int i = 1; i < count; i++)
			answer = Math.min(answer, skill[i] - skill[i - 1]);
		out.printLine(answer);
	}
}
