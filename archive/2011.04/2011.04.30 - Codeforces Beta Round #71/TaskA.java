import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int y100 = in.readInt();
		int y10 = in.readInt();
		while (true) {
			if (y100 >= 2 && y10 >= 2) {
				y100 -= 2;
				y10 -= 2;
			} else if (y100 >= 1 && y10 >= 12) {
				y100--;
				y10 -= 12;
			} else if (y10 >= 22) {
				y10 -= 22;
			} else {
				out.println("Hanako");
				return;
			}
			if (y10 >= 22) {
				y10 -= 22;
			} else if (y100 >= 1 && y10 >= 12) {
				y100--;
				y10 -= 12;
			} else if (y100 >= 2 && y10 >= 2) {
				y100 -= 2;
				y10 -= 2;
			} else {
				out.println("Ciel");
				return;
			}
		}
	}
}

