package Timus.Part8;

import net.egork.arrays.ArrayUtils;
import net.egork.arrays.ArrayWrapper;
import net.egork.collections.CollectionUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1723 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		char[] spell = in.readString().toCharArray();
		int[] count = new int[256];
		for (char letter = 'a'; letter <= 'z'; letter++) {
			count[letter] = CollectionUtils.count(ArrayWrapper.wrap(spell), letter);
		}
		out.println((char)ArrayUtils.maxIndex(ArrayWrapper.wrap(count)));
	}
}

