package on2012_04.on2012_3_23.tied;



import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tied {
	Crossing[] stack;
	int size = 0;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int segmentCount = in.readInt();
		in.readInt();
		in.readInt();
		int[] fx = new int[count];
		int[] fy = new int[count];
		IOUtils.readIntArrays(in, fx, fy);
		int[] x = new int[segmentCount + 1];
		int[] y = new int[segmentCount + 1];
		IOUtils.readIntArrays(in, x, y);
		List<Crossing> crossings = new ArrayList<Crossing>();
		Crossing last = null;
		@SuppressWarnings("unchecked")
		Integer[] order = ListUtils.order(Array.wrap(fy));
		Integer[] reverseOrder = order.clone();
		Collections.reverse(Array.wrap(reverseOrder));
		for (int i = 0; i < segmentCount; i++) {
			if (y[i] == y[i + 1])
				continue;
			Integer[] currentOrder;
			if (y[i] < y[i + 1])
				currentOrder = order;
			else
				currentOrder = reverseOrder;
			for (int j : currentOrder) {
				if (fy[j] >= y[i] && fy[j] <= y[i + 1] || fy[j] <= y[i] && fy[j] >= y[i + 1]) {
					double xx = ((double)x[i] * Math.abs(fy[j] - y[i + 1]) + (double)x[i + 1] * Math.abs(fy[j] - y[i])) / Math.abs(y[i] - y[i + 1]);
					Crossing next = new Crossing(j, xx < fx[j], currentOrder == order);
					if (!next.equals(last))
						crossings.add(last = next);
				}
			}
		}
		stack = new Crossing[crossings.size()];
		simplify(crossings, (1 << count) - 1);
		crossings = new ArrayList<Crossing>();
		for (int i = 0; i < size; i++)
			crossings.add(stack[i]);
		int answer = count;
		for (int i = 0; i < (1 << count); i++) {
			simplify(crossings, i);
			if (size == 0)
				answer = Math.min(answer, count - Integer.bitCount(i));
		}
		out.printLine(answer);
	}

	private void simplify(List<Crossing> crossings, int mask) {
		size = 0;
		for (Crossing crossing : crossings) {
			if (size == 0 && !crossing.left || (mask >> crossing.type & 1) == 0)
				continue;
			if (size != 0 && crossing.type == stack[size - 1].type && crossing.left == stack[size - 1].left) {
				size--;
				continue;
			}
			stack[size++] = crossing;
		}
		while (size != 0 && !stack[size - 1].left)
			size--;
	}

	static class Crossing {
		final int type;
		final boolean left;
		final boolean up;

		Crossing(int type, boolean left, boolean up) {
			this.type = type;
			this.left = left;
			this.up = up;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Crossing)) return false;

			Crossing crossing = (Crossing) o;

			if (left != crossing.left) return false;
			if (type != crossing.type) return false;
			if (up != crossing.up) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = type;
			result = 31 * result + (left ? 1 : 0);
			result = 31 * result + (up ? 1 : 0);
			return result;
		}
	}
}
