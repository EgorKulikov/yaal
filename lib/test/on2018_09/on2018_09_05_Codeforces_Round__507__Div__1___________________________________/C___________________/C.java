package on2018_09.on2018_09_05_Codeforces_Round__507__Div__1___________________________________.C___________________;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.numbers.IntegerUtils;

import static java.util.Arrays.sort;
import static net.egork.misc.ArrayUtils.*;
import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.misc.MiscUtils.decreaseByOne;
import static net.egork.numbers.IntegerUtils.power;

public class C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int k = in.readInt();
        long[] c = in.readLongArray(n);
        int[] u = new int[m];
        int[] v = new int[m];
        in.readIntArrays(u, v);
        decreaseByOne(u, v);
        long[] x = new long[m];
        for (int i = 0; i < m; i++) {
            x[i] = c[u[i]] ^ c[v[i]];
        }
        long base = power(2, n, MOD7);
        long rev2 = IntegerUtils.reverse(2, MOD7);
        long answer = (1L << k) % MOD7 * base % MOD7;
        int[] order = order(x);
        order = reversePermutation(order);
        orderBy(order, u, v);
        sort(x);
        int[] first = createArray(n, -1);
        int[] valid = new int[n];
        int[] next = new int[m * 2];
        int[] to = new int[m * 2];
        int[] visited = new int[n];
        int id = 1;
        IntList involved = new IntArrayList();
        int[] queue = new int[n];
        for (int i = 0; i <= m; i++) {
            if (i == m || i != 0 && x[i] != x[i - 1]) {
                long current = base;
                for (int j : involved) {
                    if (visited[j] != id) {
                        int size = 1;
                        queue[0] = j;
                        visited[j] = id;
                        for (int l = 0; l < size; l++) {
                            int vertex = queue[l];
                            for (int o = first[vertex]; o != -1; o = next[o]) {
                                int dest = to[o];
                                if (visited[dest] != id) {
                                    current *= rev2;
                                    current %= MOD7;
                                    answer -= current;
                                    queue[size++] = dest;
                                    visited[dest] = id;
                                }
                            }
                        }
                    }
                }
                involved = new IntArrayList();
                id++;
            }
            if (i != m) {
                if (valid[u[i]] != id) {
                    valid[u[i]] = id;
                    first[u[i]] = -1;
                    involved.add(u[i]);
                }
                next[2 * i] = first[u[i]];
                first[u[i]] = 2 * i;
                to[2 * i] = v[i];
                if (valid[v[i]] != id) {
                    valid[v[i]] = id;
                    first[v[i]] = -1;
                    involved.add(v[i]);
                }
                next[2 * i + 1] = first[v[i]];
                first[v[i]] = 2 * i + 1;
                to[2 * i + 1] = u[i];
            }
        }
        answer %= MOD7;
        if (answer < 0) {
            answer += MOD7;
        }
        out.printLine(answer);
    }
}
