package on2016_01.on2016_01_31_Grand_Prix_of_Asia_2016.C___Jump;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = IOUtils.readIntArray(in, n);
		int[] steps = new int[20001];
		Arrays.fill(steps, Integer.MAX_VALUE - 1);
		steps[0] = 0;
		boolean[] edge = new boolean[20001];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				edge[2 * a[i] - 2 * a[j]] = true;
			}
		}
		IntList places = new IntArrayList();
		for (int i = 0; i <= 20000; i++) {
			if (edge[i]) {
				places.add(i);
			}
		}
		int[] moves = places.toArray();
		int[] queue = new int[20001];
		int size = 1;
		for (int i = 0; i < size; i++) {
			int current = queue[i];
			for (int j : moves) {
				int candidate = current - j;
				if (candidate >= 0 && steps[candidate] == Integer.MAX_VALUE - 1) {
					steps[candidate] = steps[current] + 2;
					queue[size++] = candidate;
				}
				candidate = current + j;
				if (candidate <= 20000 && steps[candidate] == Integer.MAX_VALUE - 1) {
					steps[candidate] = steps[current] + 2;
					queue[size++] = candidate;
				}
			}
		}
		int q = in.readInt();
		for (int i = 0; i < q; i++) {
			int s = in.readInt();
			int t = in.readInt();
			int answer = steps[Math.abs(s - t)];
			for (int j : a) {
				int ss = 2 * j - s;
				answer = Math.min(answer, steps[Math.abs(ss - t)] + 1);
			}
			if (answer == Integer.MAX_VALUE - 1) {
				answer = -1;
			}
			out.printLine(answer);
		}
	}
}
