import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class HeadOverMeals implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int plateCount = in.readInt();
		int[] plates = IOUtils.readIntArray(in, plateCount);
		int answer = plateCount;
		for (int i = plates.length - 1; i >= 0; i--) {
			if (plates[i] == answer)
				answer--;
		}
		out.println(answer);
	}
}

