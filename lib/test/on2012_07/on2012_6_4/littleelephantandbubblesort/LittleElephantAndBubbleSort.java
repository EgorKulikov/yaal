package on2012_07.on2012_6_4.littleelephantandbubblesort;



import net.egork.misc.ArrayUtils;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class LittleElephantAndBubbleSort {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int delta = in.readInt();
        final int[] array = IOUtils.readIntArray(in, count);
        int[] probability = IOUtils.readIntArray(in, count);
        int[] values = new int[2 * count];
        for (int i = 0; i < count; i++) {
            values[2 * i] = array[i];
            values[2 * i + 1] = array[i] + delta;
        }
        Arrays.sort(values);
        values = ArrayUtils.unique(values);
        SumIntervalTree tree = new SumIntervalTree(values.length);
        long answer = 0;
        for (int i = 0; i < count; i++) {
            int deltaPosition = Arrays.binarySearch(values, array[i] + delta);
            answer += tree.query(deltaPosition + 1, values.length - 1) * probability[i];
            int position = Arrays.binarySearch(values, array[i]);
            answer += tree.query(position + 1, values.length - 1) * (100 - probability[i]);
            tree.update(deltaPosition, deltaPosition, probability[i]);
            tree.update(position, position, 100 - probability[i]);
        }
        out.printFormat("%.4f\n", (double)answer / 10000);
	}
}
