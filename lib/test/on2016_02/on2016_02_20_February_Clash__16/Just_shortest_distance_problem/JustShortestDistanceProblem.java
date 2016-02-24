package on2016_02.on2016_02_20_February_Clash__16.Just_shortest_distance_problem;



import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class JustShortestDistanceProblem {
    public static final int INFINITY = Integer.MAX_VALUE / 2;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] distances = createArray(n, INFINITY);
        distances[0] = 0;
        Graph graph = new Graph(n);
        boolean[][] was = new boolean[n][n];
        int[] queue = new int[n];
        for (int i = 0; i < m; i++) {
            int type = in.readInt();
            if (type == 1) {
                int x = in.readInt() - 1;
                out.printLine(distances[x] == INFINITY ? -1 : distances[x]);
            } else {
                int x = in.readInt() - 1;
                int y = in.readInt() - 1;
                if (was[x][y]) {
                    continue;
                }
                graph.addSimpleEdge(x, y);
                was[x][y] = true;
                if (distances[y] > distances[x] + 1) {
                    distances[y] = distances[x] + 1;
                    queue[0] = y;
                    int size = 1;
                    for (int j = 0; j < size; j++) {
                        int current = queue[j];
                        for (int k = graph.firstOutbound(current); k != -1; k = graph.nextOutbound(k)) {
                            int next = graph.destination(k);
                            if (distances[next] > distances[current] + 1) {
                                distances[next] = distances[current] + 1;
                                queue[size++] = next;
                            }
                        }
                    }
                }
            }
        }
    }
}
