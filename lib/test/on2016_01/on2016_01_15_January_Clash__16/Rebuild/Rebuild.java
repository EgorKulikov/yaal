package on2016_01.on2016_01_15_January_Clash__16.Rebuild;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.ListIndependentSetSystem;
import net.egork.generated.collections.comparator.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Rebuild {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[] a = new int[m];
		int[] b = new int[m];
		int[] c = new int[m];
		IOUtils.readIntArrays(in, a, b, c);
		MiscUtils.decreaseByOne(a, b);
		IndependentSetSystem setSystem = new ListIndependentSetSystem(n);
		int[] order = ArrayUtils.createOrder(m);
		ArrayUtils.sort(order, new IntComparator() {
			@Override
			public int compare(int first, int second) {
				if (c[first] != c[second]) {
					return c[first] - c[second];
				}
				if (Math.min(a[first], b[first]) != Math.min(a[second], b[second])) {
					return -Math.min(a[first], b[first]) + Math.min(a[second], b[second]);
				}
				return a[second] + b[second] - a[first] - b[first];
			}
		});
		long total = 0;
		int[] degree = new int[n];
		for (int i : order) {
			if (setSystem.join(a[i], b[i])) {
				total += c[i];
				degree[a[i]]++;
				degree[b[i]]++;
			}
		}
		out.printLine(total);
		out.printLine(degree);
	}
}
