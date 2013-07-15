package net.egork;

import net.egork.collections.map.EHashMap;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] professor = new int[count];
		int[] assistant = new int[count];
		IOUtils.readIntArrays(in, professor, assistant);
		int[] order = ArrayUtils.order(professor);
		Map<Integer, Integer> nextFree = new EHashMap<Integer, Integer>();
		for (int i = count - 1; i >= 0; i--) {
			int current = get(nextFree, assistant[order[i]]);
			assistant[order[i]] = current;
		}
		int left = 0;
		int right = Integer.MAX_VALUE / 2;
		boolean[] isProfessor = new boolean[count];
		while (left < right) {
			int middle = (left + right) / 2;
			if (can(middle, professor, assistant, isProfessor, order))
				right = middle;
			else
				left = middle + 1;
		}
		can(left, professor, assistant, isProfessor, order);
		int[] start = new int[count];
		int current = 1;
		int[] assistantOrder = ArrayUtils.order(assistant);
		for (int i = count - 1; i >= 0; i--) {
			if (!isProfessor[assistantOrder[i]])
				start[assistantOrder[i]] = current++;
		}
		for (int i = 0; i < count; i++) {
			if (isProfessor[i]) {
				start[i] = current;
				current += professor[i];
			}
		}
		out.printLine(left);
		for (int i = 0; i < count; i++) {
			out.printLine(isProfessor[i] ? 'B' : 'A', start[i]);
		}
    }

	private boolean can(int days, int[] professor, int[] assistant, boolean[] isProfessor, int[] order) {
		long totalProfessor = 0;
		for (int i = order.length - 1; i >= 0; i--) {
			int j = order[i];
			if (assistant[j] < days) {
				totalProfessor++;
				isProfessor[j] = false;
			} else {
				totalProfessor += professor[j];
				isProfessor[j] = true;
			}
		}
		return totalProfessor <= days;
	}

	private int get(Map<Integer, Integer> nextFree, int current) {
		if (!nextFree.containsKey(current)) {
			nextFree.put(current, current + 1);
			return current;
		}
		int result = get(nextFree, nextFree.get(current));
		nextFree.put(current, result);
		return result;
	}
}
