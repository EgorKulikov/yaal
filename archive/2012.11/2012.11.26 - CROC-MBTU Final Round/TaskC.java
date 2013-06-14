package net.egork;

import net.egork.collections.map.Counter;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int genreCount = in.readInt();
		int[] genres = IOUtils.readIntArray(in, count);
		Counter<Integer> counter = new Counter<Integer>();
		counter.add(genres[0]);
		for (int i = 1; i < count; i++) {
			if (genres[i] != genres[i - 1]) {
				counter.add(genres[i]);
				for (int j = i; j < count; j++) {
					if (genres[i] != genres[j]) {
						if (genres[j] == genres[i - 1])
							counter.add(genres[i]);
						break;
					}
				}
			}
		}
		long delta = -1;
		int answer = 0;
		for (int i = 1; i <= genreCount; i++) {
			if (counter.get(i) > delta) {
				delta = counter.get(i);
				answer = i;
			}
		}
		out.printLine(answer);
	}
}
