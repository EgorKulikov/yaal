package on2012_05.on2012_4_5.taskg;



import net.egork.collections.Pair;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TaskG {
	long[] powers = new long[10000];

	{
		powers[0] = 1;
		for (int i = 1; i < powers.length; i++)
			powers[i] = powers[i - 1] * 42;
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		Map<Long, Integer> index = new HashMap<Long, Integer>();
		Set<Pair<Integer, Integer>> edges = new HashSet<Pair<Integer, Integer>>();
		int count;
		for (count = 0; ; count++) {
			String word = in.readString();
			if ("--".equals(word))
				break;
			long baseHash = hash(word);
			for (int i = 0; i < word.length(); i++) {
				long curBaseHash = baseHash - word.charAt(i) * powers[i];
				for (char c = 'a'; c <= 'z'; c++) {
					if (word.charAt(i) == c)
						continue;
					long curHash = curBaseHash + c * powers[i];
					if (index.containsKey(curHash))
						edges.add(Pair.makePair(count, index.get(curHash)));
				}
			}
			index.put(baseHash, count);
		}
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (Pair<Integer, Integer> edge : edges)
			setSystem.join(edge.first, edge.second);
		while (!in.isExhausted()) {
			String from = in.readString();
			String to = in.readString();
			long hashFrom = hash(from);
			long hashTo = hash(to);
			if (index.containsKey(hashFrom) && index.containsKey(hashTo) && setSystem.get(index.get(hashFrom)) == setSystem.get(index.get(hashTo)))
				out.printLine("Yes");
			else
				out.printLine("No");
		}
	}

	private long hash(String word) {
		long baseHash = 0;
		for (int i = 0; i < word.length(); i++)
			baseHash += word.charAt(i) * powers[i];
		return baseHash;
	}
}
