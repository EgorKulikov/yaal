import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int cardCount = in.readInt();
		int[] count = new int[10002];
		for (int i = 0; i < cardCount; i++)
			count[in.readInt()]++;
		List<Integer> starts = new ArrayList<Integer>();
		List<Integer> ends = new ArrayList<Integer>();
		for (int i = 0; i <= 10000; i++) {
			if (count[i] < count[i + 1]) {
				for (int j = 0; j < count[i + 1] - count[i]; j++)
					starts.add(i);
			}
			if (count[i] > count[i + 1]) {
				for (int j = 0; j < count[i] - count[i + 1]; j++)
					ends.add(i);
			}
		}
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < ends.size(); i++)
			answer = Math.min(answer, ends.get(i) - starts.get(i));
		if (answer == Integer.MAX_VALUE)
			answer = 0;
		out.println("Case #" + testNumber + ": " + answer);
	}
}

