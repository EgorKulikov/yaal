import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TaskH implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int teamCount = in.readInt();
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>() {
			@Override
			public List<Integer> get(Object key) {
				if (!containsKey(key))
					put((String) key, new ArrayList<Integer>());
				return super.get(key);
			}
		};
		String[][] teams = new String[teamCount][3];
		for (int i = 0; i < teamCount; i++) {
			for (int j = 0; j < 3; j++)
				map.get(teams[i][j] = in.readString()).add(i);
		}
		Map<String, Integer> number = new TreeMap<String, Integer>();
		Queue<String> queue = new ArrayDeque<String>();
		if (map.containsKey("Isenbaev")) {
			queue.add("Isenbaev");
			number.put("Isenbaev", 0);
			while (!queue.isEmpty()) {
				String current = queue.poll();
				for (int teamIndex : map.get(current)) {
					for (String teamMate : teams[teamIndex]) {
						if (!number.containsKey(teamMate)) {
							number.put(teamMate, number.get(current) + 1);
							queue.add(teamMate);
						}
					}
				}
			}
		}
		for (String contestant : map.keySet()) {
			if (!number.containsKey(contestant))
				number.put(contestant, null);
		}
		for (Map.Entry<String, Integer> entry : number.entrySet()) {
			out.println(entry.getKey() + " " + (entry.getValue() == null ? "undefined" : entry.getValue()));
		}
	}
}

