package on2016_02.on2016_02_04_AIM_Tech_Round__Div__1_.A___Graph_and_String;



import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] u = new int[m];
        int[] v = new int[m];
        readIntArrays(in, u, v);
        if (m == n * (n - 1) / 2) {
            out.printLine("Yes");
            for (int i = 0; i < n; i++) {
                out.print('a');
            }
            out.printLine();
            return;
        }
        boolean[][] edge = new boolean[n][n];
        decreaseByOne(u, v);
        for (int i = 0; i < m; i++) {
            edge[u[i]][v[i]] = edge[v[i]][u[i]] = true;
        }
        for (int i = 0; i < n; i++) {
            edge[i][i] = true;
        }
        int one = -1;
        int two = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (!edge[i][j]) {
                    one = i;
                    two = j;
                }
            }
        }
        IntSet first = new IntHashSet();
        IntSet second = new IntHashSet();
        for (int i = 0; i < n; i++) {
            if (!edge[i][one]) {
                first.add(i);
            } else if (!edge[i][two]) {
                second.add(i);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                boolean noEdge = first.contains(i) && second.contains(j) || first.contains(j) && second.contains(i);
                if (noEdge == edge[i][j]) {
                    out.printLine("No");
                    return;
                }
            }
        }
        out.printLine("Yes");
        for (int i = 0; i < n; i++) {
            if (first.contains(i)) {
                out.print('a');
            } else if (second.contains(i)) {
                out.print('c');
            } else {
                out.print('b');
            }
        }
        out.printLine();
    }
}
