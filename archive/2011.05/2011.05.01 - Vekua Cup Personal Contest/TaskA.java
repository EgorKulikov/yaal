import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		String string = in.readString();
		if (string.indexOf("bob") != -1)
			out.println(0);
		else if (string.indexOf("bb") != -1 || string.indexOf("bo") != -1 || string.indexOf("ob") != -1 || string.matches(".*b.b.*"))
			out.println(1);
		else if (string.indexOf("b") != -1 || string.indexOf("o") != -1)
			out.println(2);
		else
			out.println(3);
	}
}

