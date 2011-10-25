package April2011.UVaHugeEasyContestII;

import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskT implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int n = in.readInt();
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i * i < n; i++) {
			int x = n - i * i;
			if (x % i == 0)
				result.add(x);
		}
		Collections.sort(result);
		out.print("Case " + testNumber + ": ");
		IOUtils.printCollection(result, out);
	}
}

