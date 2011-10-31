import net.egork.collections.Pair;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.StringWrapper;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		System.err.println("Case #" + testNumber);
		int wordCount = in.readInt();
		int orderCount = in.readInt();
		String[] words = IOUtils.readStringArray(in, wordCount);
		boolean[][] contains = new boolean[wordCount][26];
		for (int i = 0; i < wordCount; i++) {
			for (char c : StringWrapper.wrap(words[i]))
				contains[i][c - 'a'] = true;
		}
		char[][] orders = IOUtils.readTable(in, orderCount, 26);
		out.print("Case #" + testNumber + ":");
		for (char[] order : orders) {
			int[] type = new int[wordCount];
			for (int i = 0; i < wordCount; i++)
				type[i] = words[i].length();
			int[] result = new int[wordCount];
			for (char c : order) {
				Set<Integer> nonZero = new HashSet<Integer>();
				Pair<Integer, Integer>[] newType = new Pair[wordCount];
				for (int i = 0; i < wordCount; i++) {
					int mask = 0;
					for (int j = 0; j < words[i].length(); j++) {
						mask <<= 1;
						if (words[i].charAt(j) == c)
							mask++;
					}
					if (mask != 0)
						nonZero.add(type[i]);
					newType[i] = Pair.makePair(type[i], mask);
				}
				for (int i = 0; i < wordCount; i++) {
					if (newType[i].second == 0 && nonZero.contains(newType[i].first))
						result[i]++;
				}
				Map<Pair<Integer, Integer>, Integer> index = new HashMap<Pair<Integer, Integer>, Integer>();
				int typeCount = 0;
				for (Pair<Integer, Integer> t : newType) {
					if (!index.containsKey(t))
						index.put(t, typeCount++);
				}
				for (int i = 0; i < wordCount; i++)
					type[i] = index.get(newType[i]);
			}
			out.print(" " + words[SequenceUtils.maxIndex(Array.wrap(result))]);
		}
		out.println();
	}
}

