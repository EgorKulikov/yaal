package on2011_11.on2011_10_19.taskd0;



import net.egork.misc.ArrayUtils;
import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[][] lengths = new int[count][count];
		ArrayUtils.fill(lengths, Integer.MAX_VALUE / 2);
		for (int i = 0; i < count; i++)
			lengths[i][i] = 0;
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int length = in.readInt();
			lengths[from][to] = lengths[to][from] = length;
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++)
					lengths[j][k] = Math.min(lengths[j][i] + lengths[i][k], lengths[j][k]);
			}
		}
		int radius = Integer.MAX_VALUE;
		int index = -1;
		for (int i = 0; i < count; i++) {
			int current = CollectionUtils.maxElement(Array.wrap(lengths[i]));
			if (current < radius) {
				radius = current;
				index = i + 1;
			}
		}
		out.printLine(index, radius);
	}
}
