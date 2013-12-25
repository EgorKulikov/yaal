package on2013_12.on2013_12_10_December_Challenge_2013.Funny_Marbles;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class FunnyMarbles {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		final int[] initial = IOUtils.readIntArray(in, count);
		IntervalTree tree = new SumIntervalTree(count) {
			@Override
			protected long initValue(int index) {
				return initial[index];
			}
		};
		for (int i = 0; i < queryCount; i++) {
			char type = in.readCharacter();
			if (type == 'S') {
				int from = in.readInt();
				int to = in.readInt();
				out.printLine(tree.query(from, to));
			} else {
				int index = in.readInt();
				int value = in.readInt();
				if (type == 'T')
					value = -value;
				tree.update(index, index, value);
			}
		}
    }
}
