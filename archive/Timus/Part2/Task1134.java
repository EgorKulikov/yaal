package Timus.Part2;

import net.egork.arrays.ArrayWrapper;
import net.egork.collections.CollectionUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1134 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int cardCount = in.readInt();
		int cardTaken = in.readInt();
		ArrayWrapper<Integer> cards = ArrayWrapper.wrap(in.readIntArray(cardTaken));
		boolean allowTwo = false;
		for (int i = 0; i <= cardCount; i++) {
			int count = CollectionUtils.count(cards, i);
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

