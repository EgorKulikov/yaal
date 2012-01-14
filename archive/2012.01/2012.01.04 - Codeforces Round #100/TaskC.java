package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] radius = IOUtils.readIntArray(in, count);
		Map<Integer, Integer> index = new HashMap<Integer, Integer>();
		final int[] perRadius = new int[count];
		int sizeCount = 0;
		for (int i : radius) {
			if (!index.containsKey(i)) {
				radius[sizeCount] = i;
				index.put(i, sizeCount++);
			}
			perRadius[index.get(i)]++;
		}
		Queue<Integer> queue = new PriorityQueue<Integer>(sizeCount, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return perRadius[o2] - perRadius[o1];
			}
		});
		for (int i = 0; i < sizeCount; i++)
			queue.add(i);
		int answer = 0;
		int[][] result = new int[count / 3][3];
		while (queue.size() > 2) {
			for (int i = 0; i < 3; i++)
				result[answer][i] = queue.poll();
			for (int i : result[answer]) {
				if (--perRadius[i] != 0)
					queue.add(i);
			}
			for (int i = 0; i < 3; i++)
				result[answer][i] = radius[result[answer][i]];
			Arrays.sort(result[answer]);
			int temp = result[answer][0];
			result[answer][0] = result[answer][2];
			result[answer][2] = temp;
			answer++;
		}
		out.printLine(answer);
		for (int i = 0; i < answer; i++)
			out.printLine(result[i][0], result[i][1], result[i][2]);
	}
}
