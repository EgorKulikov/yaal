package net.egork.timus;

import net.egork.utils.exit.Exit;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Task1003 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		if (n == -1) {
			Exit.exit(in, out);
			return;
		}
		n = in.readInt();
		Map<Integer, Integer> leftEnd = new HashMap<Integer, Integer>();
		Map<Integer, Boolean> leftOddity = new HashMap<Integer, Boolean>();
		for (int i = 0; i < n; i++) {
			int left = in.readInt();
			int right = in.readInt();
			boolean oddity = "odd".equals(in.readString());
			while (leftEnd.containsKey(left)) {
				if (leftEnd.containsKey(left)) {
					int otherRight = leftEnd.get(left);
					boolean otherOddity = leftOddity.get(left);
					if (otherRight == right) {
						if (leftOddity.get(left) == oddity) {
							right = -1;
							break;
						} else {
							out.println(i);
							for (int j = i + 1; j < n; j++) {
								in.readInt();
								in.readInt();
								in.readString();
							}
							return;
						}
					}
					if (otherRight < right) {
						left = otherRight + 1;
						oddity = oddity ^ otherOddity;
					} else {
						leftEnd.put(left, right);
						leftOddity.put(left, oddity);
						left = right + 1;
						right = otherRight;
						oddity ^= otherOddity;
					}
				}
			}
			if (right == -1)
				continue;
			leftEnd.put(left, right);
			leftOddity.put(left, oddity);
		}
		out.println(n);
	}
}

