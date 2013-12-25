package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	char[][] patterns = {
		"RLLR".toCharArray(),
		"RLR".toCharArray(),
		"RRRR".toCharArray(),
		"RR".toCharArray()
	};

	char[][] replace = {
		"LRRL".toCharArray(),
		"LRRRL".toCharArray(),
		"RLRRRRLR".toCharArray(),
		"LRRRRL".toCharArray()
	};

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int targetSquare = in.readInt();
		int targetPerimeter = in.readInt();
		int perimeter = 6;
		if (perimeter > targetPerimeter || targetPerimeter % 2 != 0) {
			out.printLine("NO");
			return;
		}
		char[] answer = new char[targetPerimeter];
		Arrays.fill(answer, 0, 6, 'R');
		for (int square = 1; square < targetSquare; square++) {
			if (targetPerimeter - perimeter > 4 * (targetSquare - square)) {
				out.printLine("NO");
				return;
			}
			boolean found = false;
			for (int k = 0; k < patterns.length; k++) {
				int delta = replace[k].length - patterns[k].length;
				if (targetPerimeter - perimeter <= 4 * (targetSquare - square - 1) + delta && targetPerimeter - perimeter >= delta) {
					int max = 0;
					int at = -1;
					if (k == 1) {
						int start = 0;
						for (int i = 0; start < perimeter; i++) {
							int current = i;
							if (current >= perimeter)
								current -= perimeter;
							if (answer[current] == 'R' ^ (((i - start) & 1) == 0)) {
								if (i - start > max) {
									max = i - start;
									at = start;
								}
								if (answer[current] == 'R')
									start = i;
								else
									start = i + 1;
							}
						}
					}
					for (int i = 0; i < perimeter; i++) {
						if (i != at && at != -1)
							continue;
						boolean good = true;
						for (int j = 0; j < patterns[k].length; j++) {
							int current = i + j;
							if (current >= perimeter)
								current -= perimeter;
							if (answer[current] != patterns[k][j]) {
								good = false;
								break;
							}
						}
						if (good) {
							found = true;
							if (patterns[k].length != replace[k].length && i + patterns[k].length < perimeter)
								System.arraycopy(answer, i + patterns[k].length, answer, i + replace[k].length, perimeter - (i + patterns[k].length));
							perimeter += delta;
							for (int j = 0; j < replace[k].length; j++) {
								int current = i + j;
								if (current >= perimeter)
									current -= perimeter;
								answer[current] = replace[k][j];
							}
							break;
						}
					}
					if (found)
						break;
				}
			}
			if (!found) {
				out.printLine("NO");
				return;
			}
		}
		if (perimeter == targetPerimeter)
			out.printLine(answer);
		else
			out.printLine("NO");
    }
}
