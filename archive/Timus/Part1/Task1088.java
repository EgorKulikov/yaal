package Timus.Part1;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1088 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int alyoshaDistance = in.readInt();
		int ilyaDistance = in.readInt();
		int rootDistance = in.readInt();
		int alyoshaVertex = in.readInt() - 1;
		int ilyaVertex = in.readInt() - 1;
		int timeRemaining = in.readInt();
		int currentDepth = Math.max(ilyaDistance, alyoshaDistance);
		ilyaVertex >>= currentDepth;
		alyoshaVertex >>= currentDepth;
		while (ilyaVertex != alyoshaVertex) {
			ilyaVertex >>= 1;
			alyoshaVertex >>= 1;
			currentDepth++;
		}
		out.println(timeRemaining >= 2 * currentDepth - ilyaDistance - alyoshaDistance ? "YES" : "NO");
	}
}

