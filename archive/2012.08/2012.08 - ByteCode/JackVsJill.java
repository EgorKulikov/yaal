package net.egork;

import net.egork.collections.comparators.ReverseComparator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.PriorityQueue;
import java.util.Queue;

public class JackVsJill {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int index = in.readInt();
		Queue<Integer> queue = new PriorityQueue<Integer>(index + 1, new ReverseComparator<Integer>());
		for (int i = 0; i < count; i++) {
			int number = in.readInt();
			if (number == -1)
				out.printLine(queue.peek());
			else {
				queue.add(number);
				if (queue.size() == index + 1)
					queue.poll();
			}
		}
	}
}
