package on2016_01.on2016_01_21_Educational_Codeforces_Round_6.F___Xors_on_Segments;



import net.egork.collections.intcollection.Range;
import net.egork.generated.collections.function.IntTask;
import net.egork.generated.collections.list.IntArray;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Random;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[] a = readIntArray(in, n);
//		int[] a = new int[n];
//		Random ra = new Random(239);
//		for (int i = 0; i < n; i++) {
//			a[i] = ra.nextInt(1000000) + 1;
//		}
		int[] l = new int[m];
		int[] r = new int[m];
		readIntArrays(in, l, r);
		decreaseByOne(l, r);
		Graph graph = Graph.createGraph(Math.max(n, m), l, Range.range(m).toArray());
		int[] result = new int[n];
		int[] answer = new int[m];
		for (int i = n - 1; i >= 0; i--) {
			result[i] = a[i];
			for (int j = i + 1; j < n; j++) {
				result[j] = Math.max(value(a[i], a[j]), Math.max(result[j - 1], result[j]));
			}
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				int current = graph.destination(j);
				answer[current] = result[r[current]];
			}
		}
		new IntArray(answer).forEach((IntTask) out::printLine);
	}

	private int value(int a, int b) {
		return value(Math.max(a, b)) ^ value(Math.min(a, b) - 1);
	}

	private int value(int x) {
		return x * (1 - (x & 1)) + ((x >> 1 & 1) ^ (x & 1));
	}
}
