package on2014_06.on2014_06_15_IPSC.TaskB2;



import net.egork.collections.Pair;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskB2 {
	int r = 43;
	int s = 22;
	long mod = 1L << 32;
	List<Long> x;
	IntList c;
	int maxLength;
	boolean[] value = new boolean[2500];

	Map<Pair<IntList, Integer>, Integer> answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println(testNumber);
		int count = in.readInt();
		maxLength = count;
		IntList state = new IntArrayList();
		int at = -1;
		for (int i = 0; i < count; i++) {
			long value = in.readLong();
			if (value != 0) {
				at = i;
				if (Long.bitCount(value) != 1)
					throw new RuntimeException();
				state.add(Long.bitCount(value - 1));
			}
		}
		x = new ArrayList<>();
		for (int i = 0; i <= 42; i++)
			x.add(in.readLong());
		c = new IntArrayList();
		for (int i = 0; i <= 42; i++)
			c.add(0);
		for (int i = 0; i < 5000; i++)
			genRandom();
		for (int i = 0; i < 2500; i++)
			value[i] = x.get(44 + i * 2) % 10 == 0;
		answer = new HashMap<>();
		int result = 0;
		if (at != count - 1)
			result = Math.max(result, go(state, 0, true));
		if (at != 0)
			result = Math.max(result, go(state, 0, false));
		out.printLine(result);
    }

	private int go(IntList state, int step, boolean isLeft) {
		if (state.size() == maxLength) {
			boolean good = false;
			for (int i = 1; i < state.size(); i++) {
				if (state.get(i) == state.get(i - 1)) {
					good = true;
					break;
				}
			}
			if (!good)
				return 0;
		}
		Pair<IntList, Integer> key = Pair.makePair(state, (step + 1) * (isLeft ? 1 : -1));
		if (answer.containsKey(key))
			return answer.get(key);
		IntList left = new IntArrayList();
		boolean stateChanged = !isLeft;
		int leftScore = 0;
		for (int i = 0; i < state.size(); i++) {
			if (i + 1 < state.size() && state.get(i) == state.get(i + 1)) {
				left.add(state.get(i) + 1);
				leftScore += 1 << (state.get(i) + 1);
				i++;
				stateChanged = true;
			} else
				left.add(state.get(i));
		}
		if (stateChanged) {
			boolean both = false;
			if (x.get(43 + step * 2) % (maxLength - left.size()) != 0)
				both = true;
			left.add(value[step] ? 2 : 1);
			int toLeft = go(left, step + 1, true);
			if (both)
				toLeft = Math.max(toLeft, go(left, step + 1, false));
			leftScore += toLeft;
		}
		IntList right = new IntArrayList();
		stateChanged = isLeft;
		int rightScore = 0;
		for (int i = state.size() - 1; i >= 0; i--) {
			if (i > 0 && state.get(i) == state.get(i - 1)) {
				right.add(state.get(i) + 1);
				rightScore += 1 << (state.get(i) + 1);
				i--;
				stateChanged = true;
			} else
				right.add(state.get(i));
		}
		if (stateChanged) {
			boolean both = false;
			if (x.get(43 + step * 2) % (maxLength - right.size()) != maxLength - right.size() - 1)
				both = true;
			right.add(value[step] ? 2 : 1);
			right.inPlaceReverse();
			int toRight = go(right, step + 1, false);
			if (both)
				toRight = Math.max(toRight, go(right, step + 1, true));
			rightScore += toRight;
		}
		answer.put(key, Math.max(leftScore, rightScore));
		return Math.max(leftScore, rightScore);
	}

	long genRandom() {
		long next = x.get(x.size() - s) - x.get(x.size() - r) - c.get(x.size() - 1);
		if (next < 0)
			c.add(1);
		else
			c.add(0);
		if (next < 0)
			next += mod;
		x.add(next);
		return next;
	}

}
