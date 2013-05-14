package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	private long[] answer;
	private int[] order;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		order = IOUtils.readIntArray(in, count - 1);
		MiscUtils.decreaseByOne(order);
		answer = new long[count];
		boolean can = go(0, count - 1);
		out.print("Case #" + testNumber + ": ");
		if (!can)
			out.printLine("Impossible");
		else
			out.printLine(Array.wrap(answer).toArray());
	}

	private boolean go(int from, int to) {
		if (from == to)
			return true;
		int position = -1;
		for (int i = from; i < to; i++) {
			if (order[i] == to) {
				position = i;
				break;
			}
		}
		for (int i = from; i < position; i++) {
			if (order[i] > position)
				return false;
		}
		if (!go(from, position) || !go(position + 1, to))
			return false;
		long delta = 0;
		for (int i = from; i < position; i++) {
			long aa = answer[i] * (to - position) - answer[position] * (to - i);// + answer[to] * (position - i);
			if (aa < 0)
				continue;
			aa += position - i - 1;
			delta = Math.max(delta, aa / (position - i));
		}
		for (int i = position + 1; i < to; i++) {
			long aa = answer[i] * (to - position) + answer[position] * (to - i);
			if (aa < 0)
				continue;
			aa += to - i;
			delta = Math.max(delta, aa / (to - i));
		}
		for (int i = from; i <= position; i++)
			answer[i] += delta;
		return true;
	}
}
