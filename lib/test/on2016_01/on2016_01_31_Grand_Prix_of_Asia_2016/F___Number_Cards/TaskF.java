package on2016_01.on2016_01_31_Grand_Prix_of_Asia_2016.F___Number_Cards;



import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskF {
	static final int BUBEN = 35000;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = new int[n];
		int[] c = new int[n];
		readIntArrays(in, a, c);
		MiscUtils.decreaseByOne(a);
		for (int i = 1; i < n; i++) {
			if (c[i] != c[i - 1]) {
				for (int j = 0; j < i; j++) {
					if (c[i] == c[j]) {
						out.printLine(0);
						return;
					}
				}
			}
		}
		if (c[0] == c[n - 1]) {
			out.printLine(-1);
			return;
		}
		int answer = 0;
		for (int i = 1; i <= BUBEN; i++) {
			boolean good = true;
			for (int j = 1; j < n; j++) {
				if ((a[j] / i == a[j - 1] / i) ^ (c[j] == c[j - 1])) {
					good = false;
					break;
				}
			}
			if (good) {
				answer++;
			}
		}
		List<IntIntPair>[] pairs = new List[n - 1];
		for (int i = 0; i < n - 1; i++) {
			int last = BUBEN;
			pairs[i] = new ArrayList<>();
			int x = a[i];
			int y = a[i + 1];
			for (int t = BUBEN; t > 0; t--) {
				int start;
				int finish;
				if (c[i] == c[i + 1]) {
					start = y / (t + 1) + 1;
					finish = x / t;
				} else {
					start = x / (t + 1) + 1;
					finish = min(y / (t + 1), x / t);
				}
				start = Math.max(start, BUBEN + 1);
				if (start <= finish) {
					if (start <= last) {
						throw new RuntimeException();
					}
					pairs[i].add(new IntIntPair(start, finish));
					last = finish;
				}
			}
			int start;
			int finish;
			if (c[i] == c[i + 1]) {
				start = y + 1;
				finish = 1000000000;
			} else {
				start = x + 1;
				finish = y;
			}
			start = Math.max(start, BUBEN + 1);
			if (start <= finish) {
				pairs[i].add(new IntIntPair(start, finish));
			}
		}
		IntList all = new IntArrayList();
		for (int i = 0; i < n - 1; i++) {
			for (IntIntPair pair : pairs[i]) {
				all.add(pair.first);
				all.add(pair.second + 1);
			}
		}
		int[] aa = all.toArray();
		int[] bb = compress(aa);
		SumIntervalTree tree = new SumIntervalTree(bb.length);
		for (int i = 0; i < n - 1; i++) {
			for (IntIntPair pair : pairs[i]) {
				tree.update(binarySearch(bb, pair.first), binarySearch(bb, pair.second + 1) - 1, 1);
			}
		}
		for (int i = 0; i < bb.length - 1; i++) {
			long q = tree.query(i, i);
			if (q >= n) {
				throw new RuntimeException();
			}
			if (q == n - 1) {
				answer += bb[i + 1] - bb[i];
			}
		}
		out.printLine(answer);
	}
}
