package net.egork;

import net.egork.collections.map.EHashMap;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[] original = IOUtils.readStringArray(in, count);
		String[] permutation = IOUtils.readStringArray(in, count);
		String[] encrypted = IOUtils.readStringArray(in, count);
		Map<String, Integer> index = new EHashMap<String, Integer>();
		for (int i = 0; i < count; i++)
			index.put(permutation[i], i);
		int[] p = new int[count];
		for (int i = 0; i < count; i++)
			p[i] = index.get(original[i]);
		String[] answer = new String[count];
		for (int i = 0; i < count; i++)
			answer[i] = encrypted[p[i]];
		out.printLine(answer);
    }
}
