package net.egork.timus;

import net.egork.collections.MultiSet;
import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class Task1164 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		MultiSet<Character> secret = new MultiSet<Character>();
		int m = in.readInt();
		int n = in.readInt();
		int p = in.readInt();
		for (int i = 0; i < m * n; i++)
			secret.add(in.readCharacter());
		for (int i = 0; i < p; i++) {
			for (char c : in.readString().toCharArray())
				secret.remove(c);
		}
		for (char c = 'A'; c <= 'Z'; c++) {
			for (int i = secret.get(c); i > 0; i--)
				out.print(c);
		}
		out.println();
	}
}

