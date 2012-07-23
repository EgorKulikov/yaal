package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[][] preference = IOUtils.readIntTable(in, count, count);
		MiscUtils.decreaseByOne(preference);
		int[] alexPreference = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(alexPreference);
		int[][] position = new int[count][];
		for (int i = 0; i < count; i++)
			position[i] = createPosition(preference[i]);
		int[] alexPosition = createPosition(alexPreference);
		int best = -1;
		int second = -1;
		int[] answer = new int[count];
		int[] present = new int[count];
		Arrays.fill(present, -1);
		for (int i = 0; i < count; i++) {
			if (best == -1 || alexPosition[i] < alexPosition[best]) {
				second = best;
				best = i;
			} else if (second == -1 || alexPosition[i] < alexPosition[second])
				second = i;
			for (int j = 0; j < count; j++) {
				int current = best;
				if (current == j)
					current = second;
				if (present[j] == -1 || position[j][present[j]] > position[j][current]) {
					present[j] = current;
					answer[j] = i + 1;
				}
			}
		}
		out.printLine(Array.wrap(answer).toArray());
	}

	private int[] createPosition(int[] preference) {
		int[] position = new int[preference.length];
		for (int i = 0; i < preference.length; i++)
			position[preference[i]] = i;
		return position;
	}
}
