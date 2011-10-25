import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		char trump = in.readCharacter();
		char firstValue = in.readCharacter();
		char firstSuit = in.readCharacter();
		char secondValue = in.readCharacter();
		char secondSuit = in.readCharacter();
		if (firstSuit == trump && secondSuit != trump) {
			out.println("YES");
			return;
		}
		if (firstSuit != secondSuit) {
			out.println("NO");
			return;
		}
		String value = "6789TJQKA";
		if (value.indexOf(firstValue) > value.indexOf(secondValue))
			out.println("YES");
		else
			out.println("NO");
	}
}
