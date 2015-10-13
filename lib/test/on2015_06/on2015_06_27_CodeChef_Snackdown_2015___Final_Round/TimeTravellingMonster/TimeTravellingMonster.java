package on2015_06.on2015_06_27_CodeChef_Snackdown_2015___Final_Round.TimeTravellingMonster;



import net.egork.collections.intervaltree.LCA;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TimeTravellingMonster {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] from = new int[count - 1];
        int[] to = new int[count - 1];
        IOUtils.readIntArrays(in, from, to);
        int eaten = in.readInt();
        int[] toEat = IOUtils.readIntArray(in, eaten);
        MiscUtils.decreaseByOne(from, to, toEat);
        Arrays.sort(toEat);
        Graph graph = BidirectionalGraph.createGraph(count, from, to);
        LCA lca = new LCA(graph);
        int[] order = ArrayUtils.createOrder(count);
        ArrayUtils.sort(order, (f, s) -> lca.getLevel(s) - lca.getLevel(f));
        int[] qty = new int[count];
        int[] last = new int[count];
        for (int i : order) {
            qty[i] = 1;
            last[i] = lca.getLevel(i);
            for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                int next = graph.destination(j);
                qty[i] += qty[next];
                last[i] = Math.max(last[i], last[next]);
            }
        }
        int[] answer = new int[count];
        Arrays.fill(answer, Integer.MAX_VALUE / 2);
        for (int i : order) {
            if (Arrays.binarySearch(toEat, lca.getLevel(i)) < 0) {
                continue;
            }
            int next = Arrays.binarySearch(toEat, last[i] + 1);
            if (next < 0) {
                next = -next - 1;
            }
            answer[lca.getLevel(i)] = Math.min(answer[lca.getLevel(i)], qty[i] + (next == toEat.length ? 0 : answer[toEat[next]]));
        }
        out.printLine(answer[toEat[0]]);
    }
}
