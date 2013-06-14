package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] fromLeft = IOUtils.readIntArray(in, count);
		int[] fromRight = IOUtils.readIntArray(in, count);
		int[] answer = new int[count];
		Arrays.fill(answer, -1);
		int[] value = new int[count];
		int[] possible = new int[count];
		for (int i = 1; i <= count; i++) {
			Arrays.fill(possible, 0);
			int size = 0;
			int start = 0;
			for (int j = 0; j < count; j++) {
				if (answer[j] != -1) {
					int position = -Arrays.binarySearch(value, 0, size, answer[j]) - 1;
					value[position] = answer[j];
					if (position == size) {
						size++;
						for (int k = j - 1; k >= start; k--) {
							if (fromLeft[k] == size) {
								possible[k]++;
								break;
							}
						}
						start = j + 1;
					}
				}
			}
			size++;
			for (int k = count - 1; k >= start; k--) {
				if (fromLeft[k] == size) {
					possible[k]++;
					break;
				}
			}
			size = 0;
			start = count - 1;
			for (int j = count - 1; j >= 0; j--) {
				if (answer[j] != -1) {
					int position = -Arrays.binarySearch(value, 0, size, answer[j]) - 1;
					value[position] = answer[j];
					if (position == size) {
						size++;
						for (int k = j + 1; k <= start; k++) {
							if (fromRight[k] == size) {
								possible[k]++;
								break;
							}
						}
						start = j - 1;
					}
				}
			}
			size++;
			for (int k = 0; k <= start; k++) {
				if (fromRight[k] == size) {
					possible[k]++;
					break;
				}
			}
			boolean found = false;
			for (int j = 0; j < count; j++) {
				if (possible[j] == 2) {
					answer[j] = i;
					found = true;
					break;
				}
			}
		}
		out.print("Case #" + testNumber + ": ");
		out.printLine(answer);
    }
}
