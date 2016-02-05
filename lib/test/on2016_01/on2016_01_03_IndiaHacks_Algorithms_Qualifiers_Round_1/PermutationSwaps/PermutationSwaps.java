package on2016_01.on2016_01_03_IndiaHacks_Algorithms_Qualifiers_Round_1.PermutationSwaps;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PermutationSwaps {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[] p = IOUtils.readIntArray(in, n);
		int[] q = IOUtils.readIntArray(in, n);
		int[] a = new int[m];
		int[] b = new int[m];
		IOUtils.readIntArrays(in, a, b);
		MiscUtils.decreaseByOne(p, q, a, b);
		int[] t = new int[n];
		int[] r = new int[n];
		for (int i = 0; i < n; i++) {
			r[q[i]] = i;
		}
		for (int i = 0; i < n; i++) {
			t[i] = r[p[i]];
		}
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n);
		for (int i = 0; i < m; i++) {
			setSystem.join(a[i], b[i]);
		}
		for (int i = 0; i < n; i++) {
			if (setSystem.get(i) != setSystem.get(t[i])) {
				out.printLine("NO");
				return;
			}
		}
		out.printLine("YES");
	}
}
