package on2015_06.on2015_06_20_EpicCode_CodeSprint.Line_Segments;



import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class LineSegments {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] start = new int[count];
        int[] end = new int[count];
        in.readIntArrays(start, end);
        int[] order = ArrayUtils.createOrder(count);
        ArrayUtils.sort(order, (f, s) -> (start[f] != start[s] ? start[f] - start[s] : end[s] - end[f]));
        ArrayUtils.orderBy(ArrayUtils.reversePermutation(order), end);
        int[] result = new int[count];
        int size = 0;
        for (int i : end) {
            int at = Arrays.binarySearch(result, 0, size, i);
            if (at < 0) {
                at = -at - 1;
                result[at] = i;
                if (at == size) {
                    size++;
                }
            }
        }
        out.printLine(size);
    }
}
