package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] radii = IOUtils.readIntArray(in, count);
		Arrays.sort(radii);
		int left = 0;
		int right = count / 3;
		while (left < right) {
			int middle = (left + right + 1) / 2;
			int j = 0;
			int k = count - middle;
			int good = 0;
			for (int i = middle; i < count - middle && k < count; i++) {
				if (3 * radii[i] > 2 * radii[k])
					break;
				if (3 * radii[j] <= 2 * radii[i]) {
					good++;
					j++;
					k++;
				}
			}
			if (good == middle)
				left = middle;
			else
				right = middle - 1;
		}
		out.printLine(left);
    }
}
