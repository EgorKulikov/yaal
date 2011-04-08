package net.egork.timus;

import net.egork.arrays.Array;
import net.egork.arrays.ArrayUtils;
import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class Task1134 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int cardCount = in.readInt();
		int cardTaken = in.readInt();
		Array<Integer> cards = Array.create(in.readIntArray(cardTaken));
		boolean allowTwo = false;
		for (int i = 0; i <= cardCount; i++) {
			int count = ArrayUtils.count(cards, i);
			if (count > 2 || count == 2 && !allowTwo) {
				out.println("NO");
				return;
			}
			if (count == 0)
				allowTwo = true;
			if (count == 2)
				allowTwo = false;
		}
		if (!allowTwo)
			out.println("NO");
		else
			out.println("YES");
	}
}

