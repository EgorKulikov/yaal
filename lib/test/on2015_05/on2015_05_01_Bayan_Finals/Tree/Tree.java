package on2015_05.on2015_05_01_Bayan_Finals.Tree;



import net.egork.collections.intervaltree.LCA;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class Tree {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            public static final long MOD = (long) (1e9 + 7);

            int count;
            int times;
            Graph tree;

            long length;
            long ways;

            @Override
            public void read(InputReader in) {
                count = in.readInt();
                times = in.readInt();
                int[] from = new int[count - 1];
                int[] to = new int[count - 1];
                IOUtils.readIntArrays(in, from, to);
                MiscUtils.decreaseByOne(from, to);
                tree = BidirectionalGraph.createGraph(count, from, to);
            }

            @Override
            public void solve() {
                LCA lca = new LCA(tree);
                int diameter = 0;
                for (int i = 0; i < count; i++) {
                    for (int j = 0; j < i; j++) {
                        diameter = Math.max(diameter, lca.getLevel(i) + lca.getLevel(j) - 2 * lca.getLevel(lca.getLCA(i, j)));
                    }
                }
                length = diameter * IntegerUtils.power(3, times);
                long[][] ways = new long[count][count];
                for (int i = 0; i < count; i++) {
                    for (int j = 0; j < i; j++) {
                        ways[j][i] = ways[i][j] = lca.getLevel(i) + lca.getLevel(j) - 2 * lca.getLevel(lca.getLCA(i, j)) == diameter ? 1 : 0;
                    }
                }
                int[] qty = new int[count + 1];
                for (int i = 0; i < count; i++) {
                    int current = 0;
                    for (long j : ways[i]) {
                        current += j;
                    }
                    qty[current]++;
                }
                int[] id = new int[count + 1];
                int at = 0;
                for (int i = 1; i <= count; i++) {
                    if (qty[i] != 0) {
                        id[i] = at++;
                    }
                }
                ways = new long[at][at];
                int total = 0;
                for (int i = 1; i <= count; i++) {
                    total += qty[i];
                }
                for (int i = 1; i <= count; i++) {
                    if (qty[i] == 0) {
                        continue;
                    }
                    for (int j = 1; j <= count; j++) {
                        if (qty[j] != 0 && i != j) {
                            ways[id[i]][id[j]] = qty[j];
                        }
                    }
                    ways[id[i]][id[i]] = qty[i] - (total - i);
                }
                long[] matrix = Matrix.convert(ways);
                matrix = Matrix.power(matrix, (1 << times) * 2 - 1, MOD, at);
                this.ways = 0;
                for (int i = 1; i <= count; i++) {
                    if (qty[i] == 0) {
                        continue;
                    }
                    for (int j = at * id[i]; j < at * (id[i] + 1); j++) {
                        this.ways += qty[i] * matrix[j];
                    }
                }
                this.ways %= MOD;
                this.ways *= (MOD + 1) / 2;
                this.ways %= MOD;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":");
                out.printLine(length, ways);
            }
        }, 4);
    }
}
