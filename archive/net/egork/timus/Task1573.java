package net.egork.timus;

import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Task1573 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int[] quantity = in.readIntArray(3);
		Map<String, Integer> index = new HashMap<String, Integer>();
		index.put("Blue", 0);
		index.put("Red", 1);
		index.put("Yellow", 2);
		boolean[] present = new boolean[3];
		for (int i = in.readInt(); i > 0; i--) {
			present[index.get(in.readString())] = true;
		}
		int answer = 1;
		for (int i = 0; i < 3; i++) {
			if (present[i])
				answer *= quantity[i];
		}
		out.println(answer);
	}
}

