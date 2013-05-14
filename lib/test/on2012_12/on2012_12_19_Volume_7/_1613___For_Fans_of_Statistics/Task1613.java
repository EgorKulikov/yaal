package on2012_12.on2012_12_19_Volume_7._1613___For_Fans_of_Statistics;



import net.egork.misc.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1613 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		final int[] result = IOUtils.readIntArray(in, count);
		int[] order = ArrayUtils.createOrder(count);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				if (result[first] != result[second])
					return result[first] - result[second];
				return first - second;
			}
		});
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int value = in.readInt();
			int left = 0;
			int right = count;
			while (left < right) {
				int middle = (left + right) >> 1;
				if (result[order[middle]] > value || result[order[middle]] == value && order[middle] >= from)
					right = middle;
				else
					left = middle + 1;
			}
			if (left < count && result[order[left]] == value && order[left] <= to)
				out.print(1);
			else
				out.print(0);
		}
		out.printLine();
	}
}
