import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int setCount = in.readInt();
		int[][] containsIn = new int[201][setCount - 1];
		int[][] setJoins = new int[setCount * (setCount - 1) / 2][];
		int[] index = new int[201];
		for (int i = 0; i < setJoins.length; i++) {
			setJoins[i] = IOUtils.readIntArray(in, in.readInt());
			for (int number : setJoins[i])
				containsIn[number][index[number]++] = i;
		}
		if (setCount == 2) {
			out.println(1 + " " + setJoins[0][0]);
			out.print((setJoins[0].length - 1) + " ");
			IOUtils.printCollection(Array.wrap(setJoins[0]).subSequence(1), out);
			return;
		}
		boolean[] accounted = new boolean[201];
		for (int i = 1; i <= 200; i++) {
			if (accounted[i] || index[i] == 0)
				continue;
			int first = containsIn[i][0];
			int second = containsIn[i][1];
			Arrays.sort(setJoins[first]);
			Arrays.sort(setJoins[second]);
			List<Integer> set = new ArrayList<Integer>();
			int index1 = 0;
			int index2 = 0;
			while (index1 < setJoins[first].length && index2 < setJoins[second].length) {
				if (setJoins[first][index1] == setJoins[second][index2]) {
					set.add(setJoins[first][index1]);
					accounted[setJoins[first][index1]] = true;
					index1++;
					index2++;
				} else if (setJoins[first][index1] < setJoins[second][index2])
					index1++;
				else
					index2++;
			}
			out.print(set.size() + " ");
			IOUtils.printCollection(set, out);
		}
	}
}

