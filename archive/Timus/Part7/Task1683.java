package Timus.Part7;

import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Task1683 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		List<Integer> result = new ArrayList<Integer>();
		while (n != 1) {
			result.add(n / 2);
			n -= n / 2;
		}
		out.println(result.size());
		IOUtils.printCollection(result, out);
	}
}

