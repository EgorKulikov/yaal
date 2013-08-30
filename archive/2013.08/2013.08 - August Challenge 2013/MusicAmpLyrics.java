package net.egork;

import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.string.SuffixAutomaton;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class MusicAmpLyrics {
	int[] result;
	boolean[] isTerminal;
	private SuffixAutomaton automaton;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int wordCount = in.readInt();
		String[] words = IOUtils.readStringArray(in, wordCount);
		int length = in.readInt();
		String[] tokens = IOUtils.readStringArray(in, length);
/*		for (int i = 0; i < length; i++)
			tokens[i] += " ";
		String text = StringUtils.unite(tokens);
		long reverse = BigInteger.valueOf(113).modInverse(BigInteger.ONE.shiftLeft(64)).longValue();
		for (String word : words) {
			if (word.length() > text.length())
				continue;
			long baseHash = 0;
			long power = 1;
			for (int i = 0; i < word.length(); i++) {
				baseHash += power * word.charAt(i);
				power *= 113;
			}
			long currentHash = 0;
			power = 1;
			for (int i = 0; i < word.length(); i++) {
				currentHash += power * text.charAt(i);
				power *= 113;
			}
			power *= reverse;
			int answer = 0;
			if (currentHash == baseHash)
				answer++;
			for (int i = word.length(); i < text.length(); i++) {
				currentHash -= text.charAt(i - word.length());
				currentHash *= reverse;
				currentHash += power * text.charAt(i);
				if (currentHash == baseHash)
					answer++;
			}
			out.printLine(answer);
		}*/
		int[] answer = new int[wordCount];
		for (String token : tokens) {
		automaton = new SuffixAutomaton(token);
		result = new int[automaton.size];
		Arrays.fill(result, -1);
		isTerminal = new boolean[automaton.size];
		for (int i = automaton.last; i >= 0; i = automaton.link[i])
			isTerminal[i] = true;
		Graph graph = new Graph(automaton.size, automaton.edgeSize);
		int[] degree = new int[automaton.size];
		for (int i = 0; i < automaton.size; i++) {
			for (int j = automaton.first[i]; j >= 0; j = automaton.next[j]) {
				graph.addSimpleEdge(automaton.to[j], i);
				degree[i]++;
			}
		}
		int[] queue = new int[automaton.size];
		int size = 0;
		for (int i = 0; i < automaton.size; i++) {
			if (degree[i] == 0)
				queue[size++] = i;
		}
		for (int i = 0; i < size; i++) {
			int current = queue[i];
			calculate(current);
			for (int j = graph.firstOutbound(current); j != -1; j = graph.nextOutbound(j)) {
				int source = graph.destination(j);
				if (--degree[source] == 0)
					queue[size++] = source;
			}
		}
		int[][] arrayTo = new int[automaton.size][];
		int[][] labelTo = new int[automaton.size][];
		for (int i = 0; i < automaton.size; i++) {
			int deg = 0;
			for (int j = automaton.first[i]; j >= 0; j = automaton.next[j])
				deg++;
			arrayTo[i] = new int[deg];
			labelTo[i] = new int[deg];
			for (int j = automaton.first[i]; j >= 0; j = automaton.next[j]) {
				arrayTo[i][--deg] = automaton.to[j];
				labelTo[i][deg] = automaton.label[j];
			}
			ArrayUtils.orderBy(labelTo[i], arrayTo[i]);
		}
			for (int i1 = 0; i1 < words.length; i1++) {
				String word = words[i1];
				int position = 0;
				for (int i = 0; i < word.length() && position != -1; i++) {
					int pos = Arrays.binarySearch(labelTo[position], word.charAt(i));
					position = pos < 0 ? -1 : arrayTo[position][pos];
				}
				if (position != -1)
					answer[i1] += result[position];
			}
		}
		for (int i : answer)
			out.printLine(i);
    }

	private int calculate(int vertex) {
		if (result[vertex] != -1)
			return result[vertex];
		result[vertex] = isTerminal[vertex] ? 1 : 0;
		for (int i = automaton.first[vertex]; i >= 0; i = automaton.next[i])
			result[vertex] += calculate(automaton.to[i]);
		return result[vertex];
	}
}
