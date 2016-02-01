package on2015_12.on2015_12_25_Educational_Codeforces_Round_4.D___The_Union_of_k_Segments;



import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int[] l = new int[n];
		int[] r = new int[n];
		IOUtils.readIntArrays(in, l, r);
		int[] at = new int[2 * n];
		int[] delta = new int[2 * n];
		System.arraycopy(l, 0, at, 0, n);
		System.arraycopy(r, 0, at, n, n);
		Arrays.fill(delta, 0, n, 1);
		Arrays.fill(delta, n, 2 * n, -1);
		int[] order = ArrayUtils.createOrder(2 * n);
		ArrayUtils.sort(order, (a, b) -> at[a] != at[b] ? Integer.compare(at[a], at[b]) : delta[b] - delta[a]);
		int start = -1;
		List<IntIntPair> answer = new ArrayList<>();
		int current = 0;
		for (int i : order) {
			current += delta[i];
			if (current == k && delta[i] == 1) {
				start = at[i];
			} else if (current == k - 1 && delta[i] == -1) {
				answer.add(new IntIntPair(start, at[i]));
			}
		}
		out.printLine(answer.size());
		for (IntIntPair pair : answer) {
			out.printLine(pair.first, pair.second);
		}
	}
}
