package on2012_11.on2012_11_04_OpenCup_Eastern_Europe_GP.TaskH;



import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] u = new int[n];
		int[] z = new int[n];
		int[] r = new int[n];
		int[][] g = new int[n][];
		int[] d = new int[n];
		for (int i = 0; i < n; i++) {
			u[i] = in.readInt();
			z[i] = in.readInt();
			r[i] = in.readInt();
			g[i] = new int[r[i]];
			for (int j = 0; j < r[i]; j++) {
				g[i][j] = in.readInt() - 1;
				d[g[i][j]]++;
			}
		}
		int[][] b = new int[n][];
		for (int i = 0; i < n; i++) {
			b[i] = new int[d[i]];
		}
		int[] s = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j : g[i]) {
				b[j][s[j]++] = i;
			}
		}

		final int[] dd = z.clone();

		Heap heap = new Heap(new IntComparator() {
			public int compare(int first, int second) {
				return dd[first] - dd[second];
			}
		}, n);

		for (int i = 0; i < n; i++) {
			heap.add(i);
		}

		int[] cc = new int[n];
		System.arraycopy(u, 0, cc, 0, n);
		boolean[] zz = new boolean[n];
		Arrays.fill(s, 0);

		for (int i = 0; i < n; i++) {
			int x = heap.poll();
//			System.out.println(x + " " + dd[x]);
			zz[x] = true;
			for (int j : b[x]) if (!zz[j]) {
				cc[j] += dd[x];
				s[j]++;
				if (s[j] == r[j]) {
					dd[j] = Math.min(dd[j], cc[j]);
					heap.shiftUp(heap.getIndex(j));
				}
			}
		}
//		System.out.println(Arrays.toString(dd));
//		System.out.println(Arrays.toString(cc));
//		System.out.println(Arrays.toString(zz));

		out.printLine(dd[0]);

	}
}
