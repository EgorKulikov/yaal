package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LittleElephantAndBlocks {
	double[] factorial = new double[21];

	{
		factorial[0] = 1;
		for (int i = 1; i <= 20; i++)
			factorial[i] = factorial[i - 1] * i;
	}

	private int[] length;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int distance = in.readInt();
		length = new int[count];
		int[] color = new int[count];
		IOUtils.readIntArrays(in, length, color);
		double answer = 0;
		for (int i = 0; i < count; i++) {
			answer += Math.max(0, length[i] - distance);
			for (int j = i + 1; j < count; j++) {
				if (color[i] == color[j]) {
					answer += go(distance, 0, 0, i, j);
					break;
				}
			}
		}
		out.printLine(answer);
    }

	private double go(int distance, int step, int qty, int from, int to) {
		if (step == length.length) {
			int base = distance - Math.max(0, distance - length[from]) - Math.max(0, distance - length[to]);
			if (base < 0)
				return 0;
			return base * factorial[qty] * factorial[length.length - qty - 2] * (length.length - qty - 1) / factorial[length.length] * 2;
		}
		if (step == from || step == to || distance <= length[step])
			return go(distance, step + 1, qty, from, to);
		return go(distance, step + 1, qty, from, to) + go(distance - length[step], step + 1, qty + 1, from, to);
	}
}
