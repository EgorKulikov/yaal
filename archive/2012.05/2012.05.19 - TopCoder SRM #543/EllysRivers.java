package net.egork;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class EllysRivers {
	public double getMin(int length, int walk, int[] width, int[] speed) {
		double answer = 0;
		for (int i = 0; i < width.length; i++)
			answer += (double) width[i] / speed[i];
		int[] count = new int[width.length];
		final double[] cost = new double[width.length + 1];
		for (int i = 0; i < width.length; i++) {
			cost[i] = (Math.sqrt((double)width[i] * width[i] + 1) - width[i]) / speed[i];
		}
		cost[width.length] = 1d / walk;
		Queue<Integer> queue = new PriorityQueue<Integer>(width.length + 1, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return Double.compare(cost[o1], cost[o2]);
			}
		});
		for (int i = 0; i <= width.length; i++)
			queue.add(i);
		for (int i = 0; i < length; i++) {
			int index = queue.poll();
			answer += cost[index];
			if (index != width.length) {
				count[index]++;
				cost[index] = (Math.sqrt((double)width[index] * width[index] + (count[index] + 1d) * (count[index] + 1)) - Math.sqrt((double)width[index] * width[index] + (double)count[index] * count[index])) / speed[index];
			}
			queue.add(index);
		}
		return answer;
	}


}

