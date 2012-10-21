package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Collections;
import java.util.NavigableSet;
import java.util.TreeSet;

public class ConnectingSoldiers {
	NavigableSet<Integer>[] variants;

	{
		//noinspection unchecked
		variants = new NavigableSet[31];
		variants[0] = new TreeSet<Integer>(Collections.singleton(0));
		for (int i = 1; i <= 30; i++) {
			variants[i] = new TreeSet<Integer>();
			for (int j = 1; 2 * j <= i + 1; j++) {
				for (int k : variants[j - 1]) {
					for (int l : variants[i - j])
						variants[i].add(k + l + i + 1);
				}
			}
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int bound = in.readInt();
		NavigableSet<Integer> head = variants[count].headSet(bound, true);
		if (head.isEmpty())
			out.printLine(-1);
		else
			out.printLine(bound - head.last());
	}
}
