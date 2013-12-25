package net.egork;

import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] number = IOUtils.readIntArray(in, count * 2);
		int[] qty = new int[100];
		int[] first = new int[100];
		int[] next = new int[count * 2];
		Arrays.fill(first, -1);
		for (int i = 0; i < 2 * count; i++) {
			next[i] = first[number[i]];
			first[number[i]] = i;
			qty[number[i]]++;
		}
		int[] answer = new int[2 * count];
		int startSingle = 2;
		int startOdd = 1;
		IntSet left = new IntHashSet();
		IntSet right = new IntHashSet();
		for (int i = 0; i < 100; i++) {
			int curStart = startSingle;
			if (qty[i] > 1)
				curStart = startOdd;
			for (int j = first[i]; j != -1; j = next[j]) {
				answer[j] = curStart;
				if (curStart == 1)
					left.add(i);
				else
					right.add(i);
				curStart = 3 - curStart;
			}
			if (qty[i] == 1)
				startSingle = 3 - startSingle;
			else if (qty[i] % 2 == 1)
				startOdd = 3 - startOdd;
		}
		out.printLine(left.size() * right.size());
		out.printLine(answer);
	}
}
