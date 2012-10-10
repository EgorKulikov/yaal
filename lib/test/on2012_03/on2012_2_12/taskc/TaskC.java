package on2012_03.on2012_2_12.taskc;



import net.egork.collections.Pair;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class TaskC {
	Set<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> processed;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int left1 = in.readInt();
		int right1 = in.readInt();
		int left2 = in.readInt();
		int right2 = in.readInt();
		processed = new HashSet<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>>();
		out.printLine(go(left1, right1, left2, right2));
	}

	private int go(int left1, int right1, int left2, int right2) {
		if (left1 > right1 || left2 > right2)
			return 0;
		while (Integer.highestOneBit(left1) == Integer.highestOneBit(right1) && left1 != Integer.highestOneBit(left1)) {
			left1 -= Integer.highestOneBit(left1);
			right1 -= Integer.highestOneBit(right1);
		}
		while (Integer.highestOneBit(left2) == Integer.highestOneBit(right2) && left2 != Integer.highestOneBit(left2)) {
			left2 -= Integer.highestOneBit(left2);
			right2 -= Integer.highestOneBit(right2);
		}
		Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> key = Pair.makePair(Pair.makePair(left1, right1),
			Pair.makePair(left2, right2));
		if (processed.contains(key))
			return 0;
		processed.add(key);
		int answer = Math.max(0, Math.min(right1, right2) - Math.max(left1, left2) + 1);
		int first = Integer.highestOneBit(right1);
		int second = Integer.highestOneBit(right2);
		if (first == 1 && second == 1)
			return answer;
		if (first > second) {
			answer = Math.max(answer, go(left1, first - 1, left2, right2));
			answer = Math.max(answer, go(1, right1 - first, left2, right2));
		} else if (first < second) {
			answer = Math.max(answer, go(left1, right1, left2, second - 1));
			answer = Math.max(answer, go(left1, right1, 1, right2 - second));
		} else {
			answer = Math.max(answer, go(left1, first - 1, left2, first - 1));
			answer = Math.max(answer, go(left1, first - 1, 1, right2 - first));
			answer = Math.max(answer, go(1, right1 - first, left2, first - 1));
			answer = Math.max(answer, go(1, right1 - first, 1, right2 - first));
		}
		return answer;
	}
}
