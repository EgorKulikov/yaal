package on2013_10.on2013_10_17_ACM_Central_Subregional.TaskD;



import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int placeCount = in.readInt();
		int count = in.readInt();
		int[] load = IOUtils.readIntArray(in, placeCount);
		final int[] value = new int[count];
		int[] weight = new int[count];
		IOUtils.readIntArrays(in, value, weight);
		int[] placeOrder = ArrayUtils.order(load);
		Heap heap = new Heap(new IntComparator() {
			public int compare(int first, int second) {
				return value[second] - value[first];
			}
		}, count);
		int[] order = ArrayUtils.order(weight);
		int j = 0;
		int[] answer = new int[placeCount];
		for (int i : placeOrder) {
			while (j < count && weight[order[j]] <= load[i])
				heap.add(order[j++]);
			if (!heap.isEmpty())
				answer[i] = heap.poll() + 1;
		}
		out.printLine(answer);
    }
}
