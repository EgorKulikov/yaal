package net.egork;

import net.egork.collections.map.EHashMap;
import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskH {

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[] from = new String[count];
		String[] to = new String[count];
		IOUtils.readStringArrays(in, from, to);
		Map<String, String> map = new EHashMap<String, String>();
		for (int i = 0; i < count; i++)
			map.put(from[i], to[i]);
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			String url = in.readString();
			String resolved = resolve(url, map);
			out.printLine(resolved);
		}
    }

	private String resolve(String url, Map<String, String> map) {
		Set<String> visited = new EHashSet<String>();
		while (map.containsKey(url) && !visited.contains(url)) {
			visited.add(url);
			url = map.get(url);
		}
		if (visited.contains(url))
			url = "NULL";
		for (String s : visited)
			map.put(s, url);
		return url;
	}
}
