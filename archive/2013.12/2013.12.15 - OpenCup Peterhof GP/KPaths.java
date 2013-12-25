package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class KPaths {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		List<IntList> answer = new ArrayList<IntList>();
		answer.add(new IntArrayList());
		answer.add(new IntArrayList());
		answer.get(0).add(2);
		count--;
		for (int i = 0; count != 0; i++) {
			IntList list = new IntArrayList();
			for (int j = i + 2; j >= 2; j--)
				list.add(j);
			answer.add(list);
			if ((count >> i & 1) == 1) {
				answer.get(0).add(i + 3);
				count -= 1 << i;
			}
		}
		out.printLine(answer.size());
		for (IntList list : answer) {
			list.inPlaceSort();
			out.print(list.size() + " ");
			out.printLine(list);
		}
    }
}
