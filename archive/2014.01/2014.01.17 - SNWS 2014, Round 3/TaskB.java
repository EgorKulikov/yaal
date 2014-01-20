package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.map.CPPTreeMap;
import net.egork.collections.map.EHashMap;
import net.egork.datetime.Date;
import net.egork.datetime.Time;
import net.egork.misc.Factory;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Map<String, List<String>> map = new CPPTreeMap<String, List<String>>(new Factory<List<String>>() {
			public List<String> create() {
				return new ArrayList<String>();
			}
		});
		Map<Pair<String, String>, Long> lastCall = new EHashMap<Pair<String, String>, Long>();
		for (int i = 0; i < count; i++) {
			Date date = Date.parse(in.readString(), "YYYY/MM/DD");
			Time time = Time.parse(in.readString(), "HH:MM");
			int length = in.readInt();
			String from = in.readString();
			String to = in.readString();
			map.get(from);
			map.get(to);
			long callStart = (long)date.asInt() * 60 * 24 + time.totalMinutes();
			Pair<String, String> key = Pair.makePair(from, to);
			if (lastCall.containsKey(key) && callStart <= lastCall.get(key) + 60 * 24) {
				map.get(from).add(to);
				map.get(to).add(from);
			}
			long callEnd = callStart + length;
			key = Pair.makePair(to, from);
			if (!lastCall.containsKey(key) || lastCall.get(key) < callEnd)
				lastCall.put(key, callEnd);
		}
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			Collections.sort(entry.getValue());
			out.print(entry.getKey() + ":");
			if (entry.getValue().isEmpty())
				out.printLine();
			else {
				out.print(' ');
				out.printLine(entry.getValue().toArray());
			}
		}
	}
}
