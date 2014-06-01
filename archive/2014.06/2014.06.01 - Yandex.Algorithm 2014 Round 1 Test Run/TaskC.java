package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] lengths = IOUtils.readIntArray(in, count);
		Arrays.sort(lengths);
		for (int i = count - 1; i >= 3; i--) {
			if (lengths[i] < lengths[i - 1] + lengths[i - 2] + lengths[i - 3]) {
				for (int j = i - 3; j >= 0; j--) {
					if (lengths[j] != lengths[i]) {
						out.printLine(lengths[i] + lengths[i - 1] + lengths[i - 2] + lengths[j]);
						return;
					}
				}
				break;
			}
		}
		out.printLine(-1);
    }
}
