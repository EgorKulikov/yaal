import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class AreaOfPark {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int perimeter = in.readInt();
		int side = perimeter / 4;
		long area = (long)side * side;
		out.println(area);
	}
}
