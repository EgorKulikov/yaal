package net.egork;

import net.egork.collections.map.EHashMap;
import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class SridharLikesTravelling {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt() - 1;
		if (count == 0) {
			out.printLine("$0");
			return;
		}
		String[] from = new String[count];
		String[] to = new String[count];
		String[] price = new String[count];
		IOUtils.readStringArrays(in, from, to, price);
		Map<String, Integer> index = new EHashMap<String, Integer>();
		for (int i = 0; i < count; i++)
			index.put(from[i], i);
		Set<String> destinations = new EHashSet<String>(Arrays.asList(to));
		int[] order = new int[count];
		for (int i = 0; i < count; i++) {
			if (!destinations.contains(from[i])) {
				order[0] = i;
				break;
			}
		}
		for (int i = 1; i < count; i++)
			order[i] = index.get(to[order[i - 1]]);
		for (int i : order)
			out.printLine(from[i], to[i], price[i]);
		int sum = 0;
		for (String s : price)
			sum += Integer.parseInt(s.substring(0, s.length() - 1));
		out.printLine(sum + "$");
	}
}
