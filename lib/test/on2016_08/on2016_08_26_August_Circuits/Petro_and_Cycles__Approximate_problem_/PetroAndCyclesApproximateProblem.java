package on2016_08.on2016_08_26_August_Circuits.Petro_and_Cycles__Approximate_problem_;



import net.egork.collections.intcollection.IntHashMap;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.fill;
import static net.egork.io.IOUtils.readIntArrays;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class PetroAndCyclesApproximateProblem {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long time = System.currentTimeMillis();
        int n = in.readInt();
        int m = in.readInt();
        int[] x = new int[m];
        int[] y = new int[m];
        readIntArrays(in, x, y);
        decreaseByOne(x, y);
        Graph graph = BidirectionalGraph.createGraph(n, x, y);
        boolean[] used = new boolean[m];
        int[] deg = new int[n];
        for (int i : x) {
            deg[i]++;
        }
        for (int i : y) {
            deg[i]++;
        }
        int[] order = ArrayUtils.order(deg);
        List<int[]> answer = attempt(graph, used, order);
        Random random = new Random(239);
        while (System.currentTimeMillis() - time < 8000) {
            for (int i = 0; i < n; i++) {
                int at = random.nextInt(i + 1);
                int temp = order[i];
                order[i] = order[at];
                order[at] = temp;
            }
            List<int[]> candidate = attempt(graph, used, order);
            if (candidate.size() > answer.size()) {
                answer = candidate;
            }
        }
        out.printLine(answer.size());
        answer.forEach(out::printLine);
    }

    protected List<int[]> attempt(Graph graph, boolean[] used, int[] order) {
        fill(used, false);
        List<int[]> answer = new ArrayList<>();
        for (int i : order) {
            IntHashMap set = new IntHashMap();
            for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                if (!used[j >> 1]) {
                    set.put(graph.destination(j), j);
                }
            }
            for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                if (!used[j >> 1]) {
                    int k = graph.destination(j);
                    for (int l = graph.firstOutbound(k); l != -1; l = graph.nextOutbound(l)) {
                        if (!used[l >> 1] && set.contains(graph.destination(l))) {
                            int a = j >> 1;
                            int b = l >> 1;
                            int c = set.get(graph.destination(l)) >> 1;
                            used[a] = true;
                            used[b] = true;
                            used[c] = true;
                            answer.add(new int[]{a + 1, b + 1, c + 1});
                            set.remove(k);
                            set.remove(graph.destination(l));
                            break;
                        }
                    }
                }
            }
        }
        return answer;
    }
}
