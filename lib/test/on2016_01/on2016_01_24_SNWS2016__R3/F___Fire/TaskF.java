package on2016_01.on2016_01_24_SNWS2016__R3.F___Fire;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int p = in.readInt();
		int c = in.readInt();
		int[] x = new int[p];
		int[] y = new int[p];
		IOUtils.readIntArrays(in, x, y);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n * m);
		for (int i = 0; i < c; i++) {
			int xx = in.readInt();
			int yy = in.readInt();
			char type = in.readCharacter();
			if (type == 'X') {
				setSystem.join(xx * m + yy, xx * m + yy + m);
			} else {
				setSystem.join(xx * m + yy, xx * m + yy + 1);
			}
		}
		IntSet set = new IntHashSet();
		for (int i = 0; i < p; i++) {
			set.add(setSystem.get(x[i] * m + y[i]));
		}
		out.printLine(setSystem.getSetCount() - set.size());
	}
}
