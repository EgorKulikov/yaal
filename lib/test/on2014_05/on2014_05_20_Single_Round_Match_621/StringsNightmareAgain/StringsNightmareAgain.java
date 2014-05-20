package on2014_05.on2014_05_20_Single_Round_Match_621.StringsNightmareAgain;



import net.egork.misc.ArrayUtils;
import net.egork.string.SuffixAutomaton;

import java.util.Arrays;

public class StringsNightmareAgain {
    public long UniqueSubstrings(int a, int b, int c, int d, int n) {
		char[] s = ArrayUtils.createArray(n, 'a');
		for (int i = 0; i < a; i++) {
			b = (int) (((long)b * c + d) % n);
			s[b] = 'b';
		}
		SuffixAutomaton automaton = new SuffixAutomaton(new String(s));
		int[] first = new int[automaton.size];
		Arrays.fill(first, -1);
		int[] order = new int[s.length + 1];
		order[0] = 0;
		for (int i = 0; i < s.length; i++)
			order[i + 1] = automaton.to[automaton.findEdge(order[i], s[i])];
		for (int i = 0; i <= s.length; i++) {
			int current = order[i];
			while (current != -1 && first[current] == -1) {
				first[current] = i;
				current = automaton.link[current];
			}
		}
		int[] last = new int[automaton.size];
		Arrays.fill(last, -1);
		for (int i = s.length; i >= 0; i--) {
			int current = order[i];
			while (current != -1 && last[current] == -1) {
				last[current] = i;
				current = automaton.link[current];
			}
		}
		long answer = 0;
		for (int i = 1; i < automaton.size; i++) {
			int maxLength = Math.min(automaton.length[i], last[i] - first[i]);
			int minLength = automaton.length[automaton.link[i]];
			answer += Math.max(0, maxLength - minLength);
		}
		return answer;
    }
}
