import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class PackingTheGoldenTriangles implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int boxCount = in.readInt();
		int[] lengths = IOUtils.readIntArray(in, boxCount);
		int bandCount = in.readInt();
		Pair<Integer, Integer>[] bands = IOUtils.readIntPairArray(in, bandCount);
		Arrays.sort(lengths);
		Arrays.sort(bands);
		int index = 0;
		int answer = 0;
		Queue<Integer> currentBands = new PriorityQueue<Integer>(bandCount);
		for (int length : lengths) {
			while (index < bandCount && 7 * length >= 11 * bands[index].first)
				currentBands.add(bands[index++].second);
			while (!currentBands.isEmpty() && 11 * currentBands.peek() < 7 * length)
				currentBands.poll();
			if (!currentBands.isEmpty()) {
				currentBands.poll();
				answer++;
			}
		}
		out.println(answer);
	}
}

