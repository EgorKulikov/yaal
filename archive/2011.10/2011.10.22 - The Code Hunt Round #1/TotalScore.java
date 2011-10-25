import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TotalScore {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int won = in.readInt();
		in.readInt();
		int tied = in.readInt();
		int result = 3 * won + tied;
		out.println(result);
	}
}
