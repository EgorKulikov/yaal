package Timus.Part7;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1688 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long exitPrice = in.readInt();
		int dinnerCount = in.readInt();
		exitPrice *= 3;
		for (int i = 0; i < dinnerCount; i++) {
			exitPrice -= in.readInt();
			if (exitPrice < 0) {
				out.println("Free after " + (i + 1) + " times.");
				return;
			}
		}
		out.println("Team.GOV!");
	}
}

