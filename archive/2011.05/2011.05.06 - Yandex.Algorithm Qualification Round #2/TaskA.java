import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int index = in.readInt() - 1;
		int power = 1;
		while (index >= 5 * power) {
			index -= 5 * power;
			power <<= 1;
		}
		String[] names = {"Sheldon", "Leonard", "Penny", "Rajesh", "Howard"};
		out.println(names[index / power]);
	}
}

