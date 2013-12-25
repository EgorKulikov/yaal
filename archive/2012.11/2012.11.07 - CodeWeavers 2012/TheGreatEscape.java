package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TheGreatEscape {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		long distance = in.readLong();
		int count = in.readInt();
		int[] jumps = IOUtils.readIntArray(in, count);
		NavigableSet<Long> positions = new TreeSet<Long>();
		positions.add(1L);
		Map<Long, List<Long>> answer = new HashMap<Long, List<Long>>();
		answer.put(1L, Collections.singletonList(1L));
		while (!positions.isEmpty()) {
			long current = positions.pollFirst();
			List<Long> curAnswer = answer.get(current);
			for (int i : jumps) {
				if (distance % (current * i) == 0 && (!answer.containsKey(current * i) || answer.get(current * i).size() > curAnswer.size() + 1 || answer.get(current * i).size() == curAnswer.size() + 1 && compare(answer.get(current * i), curAnswer) > 0)) {
					positions.add(current * i);
					List<Long> list = new ArrayList<Long>(curAnswer);
					list.add(current * i);
					answer.put(current * i, list);
				}
			}
		}
		if (!answer.containsKey(distance)) {
			out.printLine(-1);
			return;
		}
		out.printLine(answer.get(distance).size() - 1);
		out.printLine(answer.get(distance).toArray());
	}

	private int compare(List<Long> first, List<Long> second) {
		for (int i = 0; i < second.size(); i++) {
			long difference = first.get(i) - second.get(i);
			if (difference < 0)
				return -1;
			if (difference > 0)
				return 1;
		}
		return 0;
	}
}
