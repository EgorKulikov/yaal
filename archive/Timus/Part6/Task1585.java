package Timus.Part6;

import net.egork.collections.MultiSet;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1585 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		MultiSet<String> penguins = new MultiSet<String>();
		for (int i = 0; i < n; i++)
			penguins.add(in.readLine());
		int maxCount = 0;
		String penguin = null;
		for (String type : penguins) {
			int currentCount = penguins.get(type);
			if (currentCount > maxCount) {
				maxCount = currentCount;
				penguin = type;
			}
		}
		out.println(penguin);
	}
}

