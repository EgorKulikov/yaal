package net.egork;

import net.egork.collections.intcollection.IntHashMap;
import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int limit = in.readInt();
		int[] value = IOUtils.readIntArray(in, count);
		IntHashMap map = new IntHashMap();
		for (int i : value) {
			for (int j = 0; j <= limit; j++) {
				if (map.contains(i * j)) {
					map.put(i * j, Math.min(j, map.get(i * j)));
				} else {
					map.put(i * j, j);
				}
			}
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int request = in.readInt();
			int answer = limit + 1;
			for (int j : value) {
				for (int k = 0; k <= limit; k++) {
					if (map.contains(request - j * k)) {
						answer = Math.min(answer, k + map.get(request - j * k));
					}
				}
			}
			if (answer > limit) {
				answer = -1;
			}
			out.printLine(answer);
		}
	}
}
