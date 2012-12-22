package on2011_11.on2011_10_28.task1837;



import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class Task1837 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int teamCount = in.readInt();
		String[][] teams = IOUtils.readStringTable(in, teamCount, 3);
		Map<String, Integer> answer = new TreeMap<String, Integer>();
		for (String[] team : teams) {
			for (String contestant : team)
				answer.put(contestant, null);
		}
		if (answer.containsKey("Isenbaev"))
			answer.put("Isenbaev", 0);
		Queue<String> queue = new ArrayDeque<String>();
		queue.add("Isenbaev");
		while (!queue.isEmpty()) {
			String current = queue.poll();
			for (String[] team : teams) {
				if (CollectionUtils.count(Array.wrap(team), current) != 0) {
					for (String contestant : team) {
						if (answer.get(contestant) == null) {
							answer.put(contestant, answer.get(current) + 1);
							queue.add(contestant);
						}
					}
				}
			}
		}
		for (Map.Entry<String, Integer> entry : answer.entrySet()) {
			if (entry.getValue() == null)
				out.printLine(entry.getKey(), "undefined");
			else
				out.printLine(entry.getKey(), entry.getValue());
		}
	}
}
