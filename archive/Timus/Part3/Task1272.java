package Timus.Part3;

import net.egork.collections.IndependentSetSystem;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1272 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int islandCount = in.readInt();
		int tunnelCount = in.readInt();
		in.readInt();
		IndependentSetSystem system = new IndependentSetSystem(islandCount);
		for (int i = 0; i < tunnelCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			system.join(from, to);
		}
		out.println(system.getSetCount() - 1);
	}
}

