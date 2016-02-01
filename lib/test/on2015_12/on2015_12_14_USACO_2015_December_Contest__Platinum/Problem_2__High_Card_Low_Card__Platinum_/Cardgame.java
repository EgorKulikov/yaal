package on2015_12.on2015_12_14_USACO_2015_December_Contest__Platinum.Problem_2__High_Card_Low_Card__Platinum_;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class Cardgame {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] cards = IOUtils.readIntArray(in, n);
		MiscUtils.decreaseByOne(cards);
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < 2 * n; i++) {
			set.add(i);
		}
		for (int i : cards) {
			set.remove(i);
		}
		List<Integer> bessie = new ArrayList<>(set);
		int[] fromStart = new int[n + 1];
		int answer = 0;
		NavigableSet<Integer> besRem = new TreeSet<>(bessie);
		for (int i1 = 0; i1 < cards.length; i1++) {
			int i = cards[i1];
			Integer besMove = besRem.higher(i);
			if (besMove != null) {
				answer++;
				besRem.remove(besMove);
			}
			fromStart[i1 + 1] = answer;
		}
		besRem = new TreeSet<>(bessie);
		answer = 0;
		int[] fromEnd = new int[n + 1];
		for (int i1 = cards.length - 1; i1 >= 0; i1--) {
			int i = cards[i1];
			Integer besMove = besRem.lower(i);
			if (besMove != null) {
				answer++;
				besRem.remove(besMove);
			}
			fromEnd[i1] = answer;
		}
		answer = 0;
		for (int i = 0; i <= n; i++) {
			answer = Math.max(answer, fromStart[i] + fromEnd[i]);
		}
		out.printLine(answer);
	}
}
