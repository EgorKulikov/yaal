package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskA {
	List<int[]> answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] size = ArrayUtils.createOrder(count + 1);
		IntList[] last = new IntList[count + 1];
		for (int i = 1; i <= count; i++) {
			last[i] = new IntArrayList();
			last[i].add(0);
		}
		for (int i = 1; i < count; i++) {
			int k = 1;
			for (int j = 3 * i + 1; j <= count; j += 2 * i + 1) {
				if (size[j] == size[i] + k)
					last[j].add(i);
				else if (size[j] > size[i] + k) {
					size[j] = size[i] + k;
					last[j] = new IntArrayList();
					last[j].add(i);
				}
				k++;
			}
		}
		answer = new ArrayList<>();
		IntList current = new IntArrayList();
		process(count, last, current);
		for (int[] a : answer)
			Arrays.sort(a);
		Collections.sort(answer, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				for (int i = 0; i < o1.length; i++) {
					if (o1[i] != o2[i])
						return o1[i] - o2[i];
				}
				return 0;
			}
		});
		out.printLine(answer.size(), size[count]);
		for (int[] a : answer)
			out.printLine(a);
    }

	private void process(int at, IntList[] last, IntList current) {
		if (at == 0) {
			answer.add(current.toArray());
			return;
		}
		for (int i : last[at].toArray()) {
			int qty = at / (2 * i + 1);
			for (int j = 0; j < qty; j++)
				current.add(2 * i + 1);
			process(i, last, current);
			for (int j = 0; j < qty; j++)
				current.popBack();
		}
	}
}
