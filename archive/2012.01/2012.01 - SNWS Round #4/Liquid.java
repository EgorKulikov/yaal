package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.CollectionUtils;
import net.egork.collections.Pair;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.Queue;

public class Liquid {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] marks = IOUtils.readIntArray(in, count);
		Queue<Pair<Integer, Integer>> queue = new ArrayDeque<Pair<Integer, Integer>>();
		queue.add(Pair.makePair(0, 0));
		int max = CollectionUtils.maxElement(Array.wrap(marks));
		int[][] result = new int[max + 1][max + 1];
		ArrayUtils.fill(result, Integer.MAX_VALUE / 2);
		result[0][0] = 0;
		while (!queue.isEmpty()) {
			int first = queue.peek().first;
			int second = queue.poll().second;
			addAll(queue, result, first, second, marks);
			addAll(queue, result, second, first, marks);
		}
		int answer = Integer.MAX_VALUE / 2;
		int required = in.readInt();
		for (int i = 0; i <= max; i++)
			answer = Math.min(answer, Math.min(result[i][required], result[required][i]));
		if (answer == Integer.MAX_VALUE / 2)
			answer = 0;
		out.printLine(answer);
	}

	private void addAll(Queue<Pair<Integer, Integer>> queue, int[][] result, int first, int second, int[] marks) {
		int nextResult = result[first][second] + 1;
		for (int i : marks)
			add(queue, result, first, i, nextResult);
		for (int i : marks) {
			if (i > second && first + second >= i)
				add(queue, result, first - i + second, i, nextResult);
			if (first > i && second + first - i < result.length)
				add(queue, result, i, second + first - i, nextResult);
		}
		if (first + second < result.length)
			add(queue, result, 0, first + second, nextResult);
	}

	private void add(Queue<Pair<Integer, Integer>> queue, int[][] result, int first, int second, int nextResult) {
		if (result[first][second] != Integer.MAX_VALUE / 2)
			return;
		result[first][second] = result[second][first] = nextResult;
		queue.add(Pair.makePair(Math.min(first, second), Math.max(first, second)));
	}
}
