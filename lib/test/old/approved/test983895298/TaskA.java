package old.approved.test983895298;

import net.egork.misc.MiscUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		char[] s = in.readString().toCharArray();
		for (char ch : s) {
			if (!MiscUtils.isVowel(ch)) {
				out.print('.');
				out.print(Character.toLowerCase(ch));
			}
		}
		out.println();
	}
}

