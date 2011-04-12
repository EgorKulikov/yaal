package Timus.Part2;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1191 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int gap = in.readInt();
		int tramCount = in.readInt();
		int[] trams = in.readIntArray(tramCount);
		for (int tram : trams)
			gap -= gap % tram;
		out.println(gap == 0 ? "YES" : "NO");
	}
}

