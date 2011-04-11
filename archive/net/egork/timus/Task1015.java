package net.egork.timus;

import net.egork.collections.CPPMap;
import net.egork.collections.CollectionUtils;
import net.egork.io.IOUtils;
import net.egork.misc.Factory;
import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Task1015 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int dieCount = in.readInt();
		@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection"})
		CPPMap<List<Integer>, List<Integer>> dice = new CPPMap<List<Integer>, List<Integer>>(new Factory<List<Integer>>() {
			public List<Integer> create() {
				return new ArrayList<Integer>();
			}
		});
		for (int i = 0; i < dieCount; i++) {
			int left = in.readInt();
			int right = in.readInt();
			int top = in.readInt();
			int face = in.readInt();
			int bottom = in.readInt();
			int back = in.readInt();
			List<Integer> die = new ArrayList<Integer>(3);
			boolean type = false;
			die.add(Math.min(left, right));
			type ^= Math.min(left, right) == left;
			die.add(Math.min(top, bottom));
			type ^= Math.min(top, bottom) == top;
			die.add(Math.min(face, back));
			type ^= Math.min(face, back) == face;
			if (type)
				Collections.reverse(die);
			while (die.get(0) != 1)
				CollectionUtils.rotate(die);
			for (int j = 0; j < 3; j++) {
				if (die.get(j) == Math.min(left, right))
					die.add(Math.max(left, right));
				else if (die.get(j) == Math.min(top, bottom))
					die.add(Math.max(top, bottom));
				else if (die.get(j) == Math.min(face, back))
					die.add(Math.max(face, back));
			}
			dice.get(die).add(i + 1);
		}
		List<List<Integer>> types = new ArrayList<List<Integer>>(dice.values());
		Collections.sort(types, new Comparator<List<Integer>>() {
			public int compare(List<Integer> o1, List<Integer> o2) {
				return o1.get(0) - o2.get(0);
			}
		});
		out.println(types.size());
		for (List<Integer> type : types)
			IOUtils.printCollection(type, out);
	}
}

