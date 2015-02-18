package on2014_12.on2014_12_11_Single_Round_Match_641.TrianglesContainOrigin;



import net.egork.collections.comparators.IntComparator;
import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class TrianglesContainOrigin {
    public long count(int[] x, int[] y) {
        int size = x.length;
        int[] order = ArrayUtils.createOrder(size);
        ArrayUtils.sort(order, new IntComparator() {
            @Override
            public int compare(int first, int second) {
                int value = sign(y[first], x[first]) - sign(y[second], x[second]);
                if (value != 0) {
                    return -value;
                }
                return x[first] * y[second] - x[second] * y[first];
            }
        });
        order = Arrays.copyOf(order, 2 * size);
        System.arraycopy(order, 0, order, size, size);
        int at = 0;
        long answer = (long)size * (size - 1) * (size - 2) / 6;
        for (int i = 0; i < size; i++) {
            if (at < i) {
                at = i;
            }
            while (at - i < size - 1) {
                int next = at + 1;
                int x0 = x[order[i]];
                int y0 = y[order[i]];
                int x1 = x[order[next]];
                int y1 = y[order[next]];
                int a = y1 - y0;
                int b = x0 - x1;
                int c = x0 * a + y0 * b;
                if (c > 0) {
                    break;
                }
                at = next;
            }
            int total = at - i;
            answer -= total * (total - 1) / 2;
        }
		return answer;
    }

    private int sign(int y, int x) {
        if (y != 0) {
            return Integer.signum(y);
        }
        return Integer.signum(x);
    }
}
