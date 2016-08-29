package on2016_06.on2016_06_25_World_CodeSprint__4.Robanukkah_Tree;



import net.egork.graph.Graph;
import net.egork.numbers.Combinations;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.ArrayUtils.fill;
import static net.egork.misc.MiscUtils.MOD9;
import static net.egork.numbers.IntegerUtils.generateBinomialCoefficients;
import static net.egork.numbers.IntegerUtils.power;
import static net.egork.numbers.IntegerUtils.reverse;

public class RobanukkahTree {
    long[][] ways = new long[21][21];

    {
        long[][] c = generateBinomialCoefficients(21, MOD9);
        for (int i = 1; i <= 4; i++) {
            ways[4][i] = (power(i, 4, MOD9) + 11 * power(i, 2, MOD9)) % MOD9 * reverse(12, MOD9) % MOD9;
            for (int j = 1; j < i; j++) {
                ways[4][i] -= ways[4][j] * c[i][j] * ((j - i) % 2 == 0 ? 1 : 1);
                ways[4][i] %= MOD9;
            }
            if (ways[4][i] < 0) {
                ways[4][i] += MOD9;
            }
        }
        for (int i = 1; i <= 6; i++) {
            ways[6][i] = (power(i, 6, MOD9) + 3 * power(i, 4, MOD9) + 12 * power(i, 3, MOD9) + 8 * power(i, 2, MOD9)) %
                    MOD9 * reverse(24, MOD9) % MOD9;
            for (int j = 1; j < i; j++) {
                ways[6][i] -= ways[6][j] * c[i][j] * ((i - j) % 2 == 0 ? 1 : 1);
                ways[6][i] %= MOD9;
            }
            if (ways[6][i] < 0) {
                ways[6][i] += MOD9;
            }
        }
        for (int i = 1; i <= 8; i++) {
            ways[8][i] = (power(i, 8, MOD9) + 17 * power(i, 4, MOD9) + 6 * power(i, 2, MOD9)) %
                    MOD9 * reverse(24, MOD9) % MOD9;
            for (int j = 1; j < i; j++) {
                ways[8][i] -= ways[8][j] * c[i][j] * ((j - i) % 2 == 0 ? 1 : 1);
                ways[8][i] %= MOD9;
            }
            if (ways[8][i] < 0) {
                ways[8][i] += MOD9;
            }
        }
        for (int i = 1; i <= 12; i++) {
            ways[12][i] = (power(i, 12, MOD9) + 15 * power(i, 6, MOD9) + 44 * power(i, 4, MOD9)) %
                    MOD9 * reverse(60, MOD9) % MOD9;
            for (int j = 1; j < i; j++) {
                ways[12][i] -= ways[12][j] * c[i][j] * ((j - i) % 2 == 0 ? 1 : 1);
                ways[12][i] %= MOD9;
            }
            if (ways[12][i] < 0) {
                ways[12][i] += MOD9;
            }
        }
        for (int i = 1; i <= 20; i++) {
            ways[20][i] = (power(i, 20, MOD9) + 15 * power(i, 10, MOD9) + 20 * power(i, 8, MOD9) + 24 * power(i, 4,
                    MOD9)) % MOD9 * reverse(60, MOD9) % MOD9;
            for (int j = 1; j < i; j++) {
                ways[20][i] -= ways[20][j] * c[i][j] * ((j - i) % 2 == 0 ? 1 : 1);
                ways[20][i] %= MOD9;
            }
            if (ways[20][i] < 0) {
                ways[20][i] += MOD9;
            }
        }
    }

    int n;
    int k;
    int[] t;
    Graph graph;
    Combinations c;
    long[][][] answer;
    long[][][] stLocal;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        k = in.readInt();
        int f = in.readInt();
        t = readIntArray(in, f);
        graph = new Graph(n);
        for (int i = 1; i < n; i++) {
            graph.addSimpleEdge(in.readInt() - 1, i);
        }
        c = new Combinations(k + 1, MOD9);
        answer = new long[n][f][];
        stLocal = new long[n][f][21];
        fill(stLocal, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < f; j++) {
                answer[i][j] = createArray(t[j] + 1, -1L);
            }
        }
        long result = 0;
        for (int i = 0; i < f; i++) {
            for (int j = 1; j <= t[i] && j <= k; j++) {
                result += solve(0, i, j) * c.c(k, j) % MOD9 * ways[t[i]][j];
                result %= MOD9;
            }
        }
        out.printLine(result);
    }

    private long solve(int vertex, int type, int colors) {
        if (answer[vertex][type][colors] != -1) {
            return answer[vertex][type][colors];
        }
        answer[vertex][type][colors] = 1;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            long current = 0;
            for (int j = 0; j < t.length; j++) {
                if (j == type) {
                    continue;
                }
                long local = getLocal(colors, next, j);
                current += local;
                current %= MOD9;
            }
            answer[vertex][type][colors] *= current;
            answer[vertex][type][colors] %= MOD9;
        }
        return answer[vertex][type][colors];
    }

    private long getLocal(int colors, int next, int j) {
        if (stLocal[next][j][colors] != -1) {
            return stLocal[next][j][colors];
        }
        long local = 0;
        for (int l = 1; l + colors <= k && l <= t[j]; l++) {
            local += solve(next, j, l) * c.c(k - colors, l) % MOD9 * ways[t[j]][l];
            local %= MOD9;
        }
        return stLocal[next][j][colors] = local;
    }
}
