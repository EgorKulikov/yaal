import net.egork.collections.Pair;
import net.egork.collections.map.CPPMap;
import net.egork.io.IOUtils;
import net.egork.misc.Factory;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class TaskF implements Solver {
	private static final long MOD = 1000000007;

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int length = in.readInt();
		int virusCount = in.readInt();
		String[] viruses = IOUtils.readStringArray(in, virusCount);
		Set<Pair<String, Integer>> prefixes = new HashSet<Pair<String, Integer>>();
		for (String virus : viruses) {
			for (int i = 0; i <= virus.length(); i++) {
				for (int j = 0; j <= i; j++)
					prefixes.add(Pair.makePair(virus.substring(0, i), j));
			}
		}
		Map<Pair<String, Integer>, Integer> convert = new HashMap<Pair<String, Integer>, Integer>();
		for (String virus : viruses) {
			for (int i = 0; i <= virus.length(); i++) {
				String sample = virus.substring(0, i);
				for (String other : viruses) {
					int index = sample.lastIndexOf(other);
					if (index == -1)
						continue;
					int position = index + other.length();
					for (int j = index; j < position; j++) {
						Pair<String, Integer> key = Pair.makePair(sample, j);
						if (convert.containsKey(key) && convert.get(key) >= position)
							break;
						convert.put(key, position);
					}
				}
			}
		}
		Map<Pair<String, Integer>, List<Pair<String, Integer>>> step = new HashMap<Pair<String, Integer>, List<Pair<String, Integer>>>();
		for (Pair<String, Integer> prefix : prefixes) {
			ArrayList<Pair<String, Integer>> value = new ArrayList<Pair<String, Integer>>();
			step.put(prefix, value);
			for (char ch = 'a'; ch <= 'z'; ch++) {
				String sample = prefix.first + ch;
				for (int i = 0; i <= prefix.second; i++) {
					Pair<String, Integer> key = Pair.makePair(sample.substring(i), prefix.second - i);
					if (prefixes.contains(key)) {
						if (convert.containsKey(key))
							key = Pair.makePair(key.first, convert.get(key));
						value.add(key);
						break;
					}
				}
			}
		}
		Map<Pair<String, Integer>, Long> result = new HashMap<Pair<String, Integer>, Long>();
		result.put(Pair.makePair("", 0), 1L);
		Factory<Long> factory = new Factory<Long>() {
			public Long create() {
				return 0L;
			}
		};
		for (int i = 0; i < length; i++) {
			Map<Pair<String, Integer>, Long> next = new CPPMap<Pair<String, Integer>, Long>(factory);
			for (Map.Entry<Pair<String, Integer>, Long> entry : result.entrySet()) {
				long value = entry.getValue() % MOD;
				for (Pair<String, Integer> to : step.get(entry.getKey()))
					next.put(to, next.get(to) + value);
			}
			result = next;
		}
		long answer = 0;
		for (Map.Entry<Pair<String, Integer>, Long> entry : result.entrySet()) {
			if (entry.getKey().first.length() == entry.getKey().second)
				answer += entry.getValue();
		}
		answer %= MOD;
		out.println(answer);
	}
}

