package on2016_08.on2016_08_26_SNSS_2016_R5.B___Butterflies;



import net.egork.collections.intcollection.IntHashMap;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArrays;
import static net.egork.misc.ArrayUtils.orderBy;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] x = new int[n];
        int[] y = new int[n];
        readIntArrays(in, x, y);
        IntHashMap qx = new IntHashMap();
        IntHashMap qy = new IntHashMap();
        for (int i : y) {
            qy.put(i, 0);
        }
        int[] toLeft = new int[n];
        orderBy(x, y);
        for (int i = 0; i < n; i++) {
            toLeft[i] = qy.get(y[i]);
            qy.put(y[i], toLeft[i] + 1);
        }
        int[] toRight = new int[n];
        for (int i = 0; i < n; i++) {
            toRight[i] = qy.get(y[i]) - 1 - toLeft[i];
        }
        orderBy(y, x, toLeft, toRight);
        for (int i : x) {
            qx.put(i, 0);
        }
        int[] toUp = new int[n];
        for (int i = 0; i < n; i++) {
            toUp[i] = qx.get(x[i]);
            qx.put(x[i], toUp[i] + 1);
        }
        int[] toDown = new int[n];
        for (int i = 0; i < n; i++) {
            toDown[i] = qx.get(x[i]) - 1 - toUp[i];
        }
        long answer = 0;
        for (int i = 0; i < n; i++) {
            answer += (long)toLeft[i] * toRight[i] * toUp[i] * toDown[i];
        }
        out.printLine(2 * answer);
    }
}
