package on2016_02.on2016_02_06_IndiaHacks__Algorithms.D___Table_Splitting_2;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class DTableSplitting2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        char[][] map = in.readTable(n, m);
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem((n + 1) * (m + 1));
        for (int i = 0; i <= n; i++) {
            setSystem.join(0, (m + 1) * i);
            setSystem.join(0, (m + 1) * i + m);
        }
        for (int i = 0; i <= m; i++) {
            setSystem.join(0, i);
            setSystem.join(0, n * (m + 1) + i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i < n - 1 && map[i][j] != map[i + 1][j]) {
                    setSystem.join((i + 1) * (m + 1) + j, (i + 1) * (m + 1) + j + 1);
                }
                if (j < m - 1 && map[i][j] != map[i][j + 1]) {
                    setSystem.join(i * (m + 1) + j + 1, (i + 1) * (m + 1) + j + 1);
                }
            }
        }
        for (int i = 0; i < (n + 1) * (m + 1); i++) {
            if (setSystem.get(i) != setSystem.get(0) && setSystem.get(i) != i) {
                out.printLine("NO");
                return;
            }
        }
        out.printLine("YES");
    }
}
