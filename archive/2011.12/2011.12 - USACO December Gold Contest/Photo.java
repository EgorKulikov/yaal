package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Photo {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[][] order = IOUtils.readIntTable(in, 5, count);
		@SuppressWarnings({"unchecked"})
		final
		Map<Integer, Integer>[] map = new Map[5];
		for (int i = 0; i < 5; i++) {
			map[i] = new HashMap<Integer, Integer>();
			for (int j = 0; j < count; j++)
				map[i].put(order[i][j], j);
		}
		Integer[] answer = new Integer[count];
		for (int i = 0; i < count; i++)
			answer[i] = order[0][i];
		Arrays.sort(answer, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				int result = 0;
				for (int i = 0; i < 5; i++) {
					if (map[i].get(o1) < map[i].get(o2))
						result--;
					else
						result++;
				}
				return result;
			}
		});
		for (int value : answer)
			out.printLine(value);
	}
}
