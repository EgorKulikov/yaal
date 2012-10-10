package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int wishCount = in.readInt();
		int titleCount = in.readInt();
		int[][] values = new int[titleCount][];
		for (int i = 0; i < titleCount; i++)
			values[i] = IOUtils.readIntArray(in, in.readInt());
		int total = 0;
		for (int[] row : values)
			total += row.length;
		int[] all = new int[total];
		int index = 0;
		for (int[] row : values) {
			System.arraycopy(row, 0, all, index, row.length);
			index += row.length;
		}
		Arrays.sort(all);
		int smallest = all[total - wishCount];
		int[] shouldTake = new int[titleCount];
		int[] mayTake = new int[titleCount];
		int good = 0;
		int notBad = 0;
		for (int i = 0; i < titleCount; i++) {
			for (int j : values[i]) {
				if (j > smallest) {
					shouldTake[i]++;
					good++;
				} else if (j == smallest) {
					mayTake[i]++;
					notBad++;
				}
			}
		}
		int remaining = good + notBad - wishCount;
		double[] answer = new double[notBad - remaining + 1];
		answer[0] = 1;
		double[] next = new double[answer.length];
		double[][] c = new double[total + 1][total + 1];
		for (int i = 0; i <= total; i++) {
			c[i][0] = 1;
			for (int j = 1; j <= i; j++)
				c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
		}
		int mayTakeRemaining = notBad;
		int willTake = answer.length - 1;
		for (int i = 0; i < titleCount; i++) {
			Arrays.fill(next, 0);
			for (int j = 0; j < answer.length; j++) {
				for (int k = 0; k <= mayTake[i] && j + k < answer.length; k++) {
					if (mayTakeRemaining < mayTake[i] || notBad < j + k)
						continue;
					double probability = c[mayTake[i]][k] * c[mayTakeRemaining - mayTake[i]][willTake - j - k] / c[mayTakeRemaining][willTake - j];
					double win = c[mayTake[i]][k] / c[values[i].length][shouldTake[i] + k];
					next[j + k] += answer[j] * probability * win;
				}
			}
			double[] temp = answer;
			answer = next;
			next = temp;
			mayTakeRemaining -= mayTake[i];
		}
		out.printLine(answer[answer.length - 1]);
	}
}
