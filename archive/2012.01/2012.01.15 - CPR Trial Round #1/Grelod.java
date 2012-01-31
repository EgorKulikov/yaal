package net.egork;

import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class Grelod {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Map<String, String> permutation = new HashMap<String, String>();
		for (int i = 0; i < count; i++) {
			String key = in.readString();
			String value = in.readString();
			permutation.put(key, value);
		}
		String[] names = permutation.keySet().toArray(new String[count]);
		Arrays.sort(names);
		List<String> answer = new ArrayList<String>();
		for (String name : names) {
			while (!permutation.get(name).equals(name)) {
				String current = permutation.get(name);
				String minimum = current;
				while (!current.equals(name)) {
					minimum = MiscUtils.min(minimum, current);
					current = permutation.get(current);
				}
				answer.add(name + " <=> " + minimum);
				String value1 = permutation.get(name);
				String value2 = permutation.get(minimum);
				permutation.put(name, value2);
				permutation.put(minimum, value1);
			}
		}
		out.printLine(answer.size());
		for (String swap : answer)
			out.printLine(swap);
	}
}
