package on2015_12.on2015_12_25_Educational_Codeforces_Round_4.E___Square_Root_of_Permutation;



import net.egork.generated.collections.function.IntToIntFunction;
import net.egork.generated.collections.list.IntArray;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] p = IOUtils.readIntArray(in, n);
		MiscUtils.decreaseByOne(p);
		int[] cycle = ArrayUtils.createArray(n + 1, -1);
		int[] q = new int[n];
		boolean[] processed = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (processed[i]) {
				continue;
			}
			int length = 0;
			int current = i;
			do {
				length++;
				processed[current] = true;
				current = p[current];
			} while (!processed[current]);
			if (length % 2 == 0) {
				if (cycle[length] != -1) {
					int a = i;
					int b = cycle[length];
					for (int j = 0; j < length; j++) {
						q[a] = b;
						a = p[a];
						q[b] = a;
						b = p[b];
					}
					cycle[length] = -1;
				} else {
					cycle[length] = i;
				}
			} else {
				int a = i;
				int b = i;
				for (int j = 0; j < (length + 1) / 2; j++) {
					b = p[b];
				}
				for (int j = 0; j < length; j++) {
					q[a] = b;
					a = p[a];
					b = p[b];
				}
			}
		}
		if (ArrayUtils.count(cycle, -1) != n + 1) {
			out.printLine(-1);
			return;
		}
		out.printLine(new IntArray(q).map((IntToIntFunction)  x -> x + 1).compute());
	}
}
