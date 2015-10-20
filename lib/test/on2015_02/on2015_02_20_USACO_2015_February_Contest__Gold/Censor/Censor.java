package on2015_02.on2015_02_20_USACO_2015_February_Contest__Gold.Censor;


import net.egork.collections.map.Indexer;
import net.egork.generated.collections.comparator.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.string.SuffixAutomaton;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Censor {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] text = in.readString().toCharArray();
		int count = in.readInt();
		String[] words = IOUtils.readStringArray(in, count);
		StringBuilder all = new StringBuilder();
		for (String word : words) {
			all.append('#');
			all.append(word);
		}
		SuffixAutomaton automaton = new SuffixAutomaton(all);
		int[] prefixes = new int[automaton.size];
		Arrays.fill(prefixes, -1);
		int[] nextNonEmpty = new int[automaton.size];
		Indexer<Long> indexer = new Indexer<>();
		indexer.get(0L);
		prefixes[0] = 0;
		StringHash[] hash = new StringHash[count];
		for (int i = 0; i < words.length; i++) {
			hash[i] = new SimpleStringHash(words[i]);
			int current = 0;
			for (int j = 0; j < words[i].length(); j++) {
				current = automaton.to[automaton.findEdge(current, words[i].charAt(j))];
				long key = hash[i].hash(0, j + 1);
				if (!indexer.containsKey(key)) {
					prefixes[current] = indexer.get(key);
				}
			}
		}
		final int[] length = new int[indexer.size()];
		int[] vertex = new int[length.length];
		int[] isTerminal = new int[length.length];
		Arrays.fill(nextNonEmpty, -1);
		Arrays.fill(isTerminal, -1);
		for (int i = 0; i < words.length; i++) {
			int current = 0;
			for (int j = 0; j < words[i].length(); j++) {
				current = automaton.to[automaton.findEdge(current, words[i].charAt(j))];
				int index = indexer.get(hash[i].hash(0, j + 1));
				length[index] = j + 1;
				vertex[index] = current;
			}
			isTerminal[indexer.get(hash[i].hash(0))] = i;
		}
		int[] first = new int[length.length];
		int[] next = new int[all.length()];
		int[] label = new int[all.length()];
		int[] to = new int[all.length()];
		Arrays.fill(first, -1);
		int id = 0;
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				int from = indexer.get(hash[i].hash(0, j));
				int destination = indexer.get(hash[i].hash(0, j + 1));
				next[id] = first[from];
				first[from] = id;
				label[id] = words[i].charAt(j) - 'a';
				to[id++] = destination;
			}
		}
		int[][] prefixAutomaton = new int[length.length][26];
		int[] order = ArrayUtils.createOrder(length.length);
		ArrayUtils.sort(order, new IntComparator() {
			@Override
			public int compare(int first, int second) {
				return length[first] - length[second];
			}
		});
		for (int i : order) {
			if (i != 0) {
				System.arraycopy(prefixAutomaton[getNonEmpty(vertex[i], nextNonEmpty, automaton, prefixes)], 0,
					prefixAutomaton[i], 0, 26);
			}
			for (int j = first[i]; j != -1; j = next[j]) {
				prefixAutomaton[i][label[j]] = to[j];
			}
		}
		char[] answer = new char[text.length];
		int ansLength = 0;
		int[] idAt = new int[text.length + 1];
		for (char c : text) {
			answer[ansLength++] = c;
			c -= 'a';
			idAt[ansLength] = prefixAutomaton[idAt[ansLength - 1]][c];
			if (isTerminal[idAt[ansLength]] != -1) {
				ansLength -= words[isTerminal[idAt[ansLength]]].length();
			}
		}
		out.printLine(Arrays.copyOf(answer, ansLength));
	}

	private int getNonEmpty(int at, int[] nextNonEmpty, SuffixAutomaton automaton, int[] prefixes) {
		if (nextNonEmpty[at] != -1) {
			return nextNonEmpty[at];
		}
		if (prefixes[automaton.link[at]] != -1) {
			return nextNonEmpty[at] = prefixes[automaton.link[at]];
		}
		return nextNonEmpty[at] = getNonEmpty(automaton.link[at], nextNonEmpty, automaton, prefixes);
	}
}
