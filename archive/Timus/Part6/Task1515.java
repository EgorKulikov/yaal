package Timus.Part6;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1515 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int banknoteCount = in.readInt();
		int[] banknotes = in.readIntArray(banknoteCount);
		int sum = 0;
		for (int banknote : banknotes) {
			if (sum + 1 < banknote) {
				out.println(sum + 1);
				return;
			}
			sum += banknote;
		}
		out.println(sum + 1);
	}
}

