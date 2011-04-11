package net.egork.y2011.m4.d9.coci;

import net.egork.arrays.ArrayUtils;
import net.egork.arrays.ArrayWrapper;
import net.egork.misc.Factory;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Gitara implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int toneCount = in.readInt();
		in.readInt();
		@SuppressWarnings({"unchecked"})
		NavigableSet<Integer>[] picked = new NavigableSet[6];
		ArrayUtils.fill(ArrayWrapper.wrap(picked), new Factory<NavigableSet<Integer>>() {
			public NavigableSet<Integer> create() {
				return new TreeSet<Integer>();
			}
		});
		int movements = 0;
		for (int i = 0; i < toneCount; i++) {
			int string = in.readInt() - 1;
			int fret = in.readInt();
			while (!picked[string].isEmpty() && picked[string].last() > fret) {
				movements++;
				picked[string].remove(picked[string].last());
			}
			if (picked[string].isEmpty() || picked[string].last() != fret) {
				movements++;
				picked[string].add(fret);
			}
		}
		out.println(movements);
	}
}

