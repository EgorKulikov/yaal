package net.egork.timus;

import net.egork.collections.MultiSet;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;

public class Task1563 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		MultiSet<String> shops = new MultiSet<String>();
		for (int i = 0; i < n; i++)
			shops.add(in.readLine());
		out.println(shops.size() - shops.entryCount());
	}
}

