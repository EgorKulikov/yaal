import net.egork.misc.MiscUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class ISpyYou implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		while (true) {
			int ch = in.read();
			if (ch == -1)
				return;
			if (MiscUtils.isVowel((char) ch)) {
				in.read();
				in.read();
			}
			out.print((char) ch);
		}
	}
}

