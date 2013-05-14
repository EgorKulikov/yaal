package net.egork;

import net.egork.collections.set.TreapSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;

public class Lucky {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		final int[] qty = new int[count];
		TreapSet<Integer> set = new TreapSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (qty[o1] != qty[o2])
					return qty[o2] - qty[o1];
				return o1 - o2;
			}
		});
		for (int i = 0; i < count; i++)
			set.add(i);
		for (int i = 0; i < queryCount; i++) {
			char type = in.readCharacter();
			if (type == 'Q') {
				int index = set.get(12);
				out.printLine(index + 1, qty[index]);
			} else {
				int index = in.readInt() - 1;
				int delta = in.readInt();
				if (type == 'R')
					delta = -delta;
				set.remove(index);
				qty[index] += delta;
				if (qty[index] < 0)
					qty[index] = 0;
				set.add(index);
			}
		}
    }
}
