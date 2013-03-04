package April2011.CodeforcesBetaRound67;

import net.egork.collections.ArrayUtils;
import net.egork.collections.map.CPPMap;
import net.egork.misc.Factory;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		String name = in.readString();
		Map<String, Integer> friends = new CPPMap<String, Integer>(new Factory<Integer>() {
			public Integer create() {
				return 0;
			}
		});
		int eventCount = in.readInt();
		for (int i = 0; i < eventCount; i++) {
			String event = in.readLine();
			Pattern pattern;
			int type;
			if (event.matches("(\\w+) posted on (\\w+)'s wall")) {
				pattern = Pattern.compile("(\\w+) posted on (\\w+)'s wall");
				type = 3;
			} else if (event.matches("(\\w+) commented on (\\w+)'s post")) {
				pattern = Pattern.compile("(\\w+) commented on (\\w+)'s post");
				type = 2;
			} else {
				pattern = Pattern.compile("(\\w+) likes (\\w+)'s post");
				type = 1;
			}
			Matcher matcher = pattern.matcher(event);
			matcher.find();
			String first = matcher.group(1);
			String second = matcher.group(2);
			if (first.equals(name))
				friends.put(second, friends.get(second) + type * 5);
			else if (second.equals(name))
				friends.put(first, friends.get(first) + type * 5);
			else {
				friends.get(first);
				friends.get(second);
			}
		}
		final String[] friendName = new String[friends.size()];
		final int[] friendCoefficient = new int[friends.size()];
		int index = 0;
		for (Map.Entry<String, Integer> entry : friends.entrySet()) {
			friendName[index] = entry.getKey();
			friendCoefficient[index++] = entry.getValue();
		}
		Integer[] order = ArrayUtils.order(friendName.length, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (friendCoefficient[o1] != friendCoefficient[o2])
					return friendCoefficient[o2] - friendCoefficient[o1];
				return friendName[o1].compareTo(friendName[o2]);
			}
		});
		for (int i : order)
			out.println(friendName[i]);
	}
}

