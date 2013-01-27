package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskJ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] add = new int[count];
		final int[] remove = new int[count];
		for (int i = 0; i < count; i++)
			add[i] = in.readInt();
		for (int i = 0; i < count; i++)
			remove[i] = in.readInt();
		long sum = 0;
		Queue<Integer> queue = new PriorityQueue<Integer>(count, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return remove[o2] - remove[o1];
			}
		});
		for (int i = 0; i < count; i++) {
			sum += add[i] - remove[i];
			queue.add(i);
			while (sum < 0)
				sum += remove[queue.poll()];
		}
		Integer[] answer = queue.toArray(new Integer[queue.size()]);
		Arrays.sort(answer);
		for (int i = 0; i < answer.length; i++)
			answer[i]++;
		out.printLine(answer.length);
		out.printLine(answer);
	}
}
