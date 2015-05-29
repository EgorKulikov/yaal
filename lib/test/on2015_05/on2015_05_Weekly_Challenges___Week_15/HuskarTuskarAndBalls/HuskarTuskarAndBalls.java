package on2015_05.on2015_05_Weekly_Challenges___Week_15.HuskarTuskarAndBalls;



import net.egork.collections.intervaltree.LCA;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class HuskarTuskarAndBalls {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int queryCount = in.readInt();
        int[] parent = IOUtils.readIntArray(in, count - 1);
        MiscUtils.decreaseByOne(parent);
        Graph graph = Graph.createTree(parent);
        LCA lca = new LCA(graph);
        for (int i = 0; i < queryCount; i++) {
            int first = in.readInt() - 1;
            int second = in.readInt() - 1;
            int cLCA = lca.getLCA(first, second);
            if (cLCA == second) {
                out.printLine(lca.getLevel(first) - lca.getLevel(cLCA), lca.getLevel(second) + 1);
            } else {
                out.printLine(lca.getLevel(first) + 1, lca.getLevel(second) - lca.getLevel(cLCA));
            }
        }
    }
}
