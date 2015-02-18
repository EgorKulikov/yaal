package on2014_12.on2014_12_26_Sudo_Code_2_0.ProblemsWithArray;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ProblemsWithArray {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		IntervalTree tree = new SumIntervalTree(count);
		for (int i = 0; i < count; i++) {
			tree.update(i, i, array[i]);
		}
		for (int i = 0; i < queryCount; i++) {
			char type = in.readCharacter();
			if (type == 'S') {
				int from = in.readInt();
				int to = in.readInt();
				out.printLine(tree.query(from, to));
			} else {
				int at = in.readInt();
				int value = in.readInt();
				if (type == 'V') {
					value = -value;
				}
				tree.update(at, at, value);
			}
		}
    }
}
