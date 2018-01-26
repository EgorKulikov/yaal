package on2017_08.on2017_08_14_SnarkNews_Summer_Series_2017__Round_1.A______________________;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int w = in.readInt();
        int[] a = new int[w];
        char[] op = new char[w];
        int[] b = new int[w];
        for (int i = 0; i < w; i++) {
            a[i] = in.readInt();
            op[i] = in.readCharacter();
            b[i] = in.readInt();
        }
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n);
        for (int i = 0; i < w; i++) {
            if (op[i] == '=') {
                setSystem.join(a[i], b[i]);
            }
        }
        Graph graph = new Graph(n);
        for (int i = 0; i < w; i++) {
            if (op[i] == '>') {
                int from = setSystem.get(a[i]);
                int to = setSystem.get(b[i]);
                if (from == to) {
                    out.printLine("Impossible");
                    return;
                }
                graph.addSimpleEdge(from, to);
            }
        }
        if (GraphAlgorithms.topologicalSort(graph) != null) {
            out.printLine("Possible");
        } else {
            out.printLine("Impossible");
        }
    }
}
