package net.egork;

import net.egork.collections.map.CPPMap;
import net.egork.misc.Factory;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Map;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Map<String, Integer> map = new CPPMap<String, Integer>(new Factory<Integer>() {
			public Integer create() {
				return 0;
			}
		});
		for (int i = 0; i < count; i++) {
			String name = in.readString();
			map.put(name, Math.max(map.get(name), in.readInt() * 2));
		}
		Integer[] scores = map.values().toArray(new Integer[map.size()]);
		Arrays.sort(scores);
		count = map.size();
		out.printLine(map.size());
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			out.print(entry.getKey() + " ");
			int more = count + Arrays.binarySearch(scores, entry.getValue() + 1) + 1;
			if (more * 100 <= count)
				out.printLine("pro");
			else if (more * 10 <= count)
				out.printLine("hardcore");
			else if (more * 5 <= count)
				out.printLine("average");
			else if (more * 2 <= count)
				out.printLine("random");
			else
				out.printLine("noob");
		}
	}
}
