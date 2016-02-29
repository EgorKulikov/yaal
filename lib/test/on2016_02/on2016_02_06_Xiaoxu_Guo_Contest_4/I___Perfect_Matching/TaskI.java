package on2016_02.on2016_02_06_Xiaoxu_Guo_Contest_4.I___Perfect_Matching;



import net.egork.collections.intcollection.IntHashMap;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
//import static net.egork.io.IOUtils.*;
//import static net.egork.misc.MiscUtils.*;
//import static net.egork.misc.ArrayUtils.*;
//import static java.lang.Math.*;
//import static java.util.Arrays.*;

public class TaskI {
    private static final long MOD = (long) (1e9 + 7);
    int[] graph;
    IntHashMap[] maps;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = new int[m];
        int[] b = new int[m];
        IOUtils.readIntArrays(in, a, b);
        MiscUtils.decreaseByOne(a, b);
        graph = new int[n];
        for (int i = 0; i < m; i++) {
            int x = Math.max(a[i], b[i]);
            int y = Math.min(a[i], b[i]);
            graph[x] |= 1 << y;
        }
        for (int i = 0; i < n; i++) {
            graph[i] = ~graph[i];
        }
        maps = new IntHashMap[n];
        for (int i = 0; i < n; i++) {
            maps[i] = new IntHashMap();
        }
        out.printLine(go(n - 1, 0));
    }

    private int go(int step, int mask) {
        if (step == -1) {
            return 1;
        }
        if ((mask >> step & 1) == 1) {
            return go(step - 1, mask - (1 << step));
        }
        if (maps[step].contains(mask)) {
            return maps[step].get(mask);
        }
        long result = 0;
        for (int i = 0; i < step; i++) {
            if (((graph[step] | mask) >> i & 1) == 0) {
                result += go(step - 1, mask + (1 << i));
            }
        }
        result %= MOD;
        maps[step].put(mask, (int) result);
        return (int) result;
    }
}
