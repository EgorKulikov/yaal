package on2016_01.on2016_01_21_Educational_Codeforces_Round_6.C___Pearls_in_a_Row;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = IOUtils.readIntArray(in, n);
		int answer = 0;
		IntHashSet was = new IntHashSet();
		IntList ends = new IntArrayList();
		for (int j = 0; j < a.length; j++) {
			int i = a[j];
			if (was.contains(i)) {
				answer++;
				was = new IntHashSet();
				ends.add(j);
			} else {
				was.add(i);
			}
		}
		out.printLine(answer == 0 ? -1 : answer);
		if (answer != 0) {
			out.print("1 ");
			ends.popLast();
			for (int i : ends) {
				out.printLine(i + 1);
				out.print((i + 2) + " ");
			}
			out.printLine(n);
		}
	}
}
