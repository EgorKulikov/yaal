package on2016_08.on2016_08_11_SNSS_2016_R1.B______________;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.graph.Graph;
import net.egork.string.SuffixAutomaton;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.graph.GraphAlgorithms.topologicalSort;
import static net.egork.io.IOUtils.readStringArray;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        String[] a = readStringArray(in, n);
        String[] b = readStringArray(in, k);
//        SubstringAutomaton automaton = new SubstringAutomaton(b);
//        for (String s : a) {
//            int at = 0;
//            long answer = 0;
//            for (int i = 0; i < s.length(); i++) {
//                at = automaton.edges[at][s.charAt(i) - 'a'];
//                answer += automaton.ends[at];
//            }
//            out.printLine(answer);
//        }
        IntList all = new IntArrayList();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < a[i].length(); j++) {
                all.add(a[i].charAt(j));
            }
            all.add(-i);
        }
        SuffixAutomaton automaton = new SuffixAutomaton(all);
        long[] qty = new long[automaton.size];
        for (String s : b) {
            int at = 0;
            for (int i = 0; i < s.length(); i++) {
                int edge = automaton.findEdge(at, s.charAt(i));
                if (edge == -1) {
                    at = -1;
                    break;
                }
                at = automaton.to[edge];
            }
            if (at != -1) {
                qty[at]++;
            }
        }
        Graph graph = new Graph(automaton.size, automaton.edgeSize);
        for (int i = 0; i < automaton.size; i++) {
            for (int j = automaton.first[i]; j != -1; j = automaton.next[j]) {
                graph.addSimpleEdge(i, automaton.to[j]);
            }
        }
        int[] order = topologicalSort(graph);
        long[] answer = new long[n];
        for (int i : order) {
            for (int j = automaton.first[i]; j != -1; j = automaton.next[j]) {
                if (automaton.label[j] <= 0) {
                    answer[-automaton.label[j]] += qty[i];
                } else {
                    qty[automaton.to[j]] += qty[i];
                }
            }
        }
        for (long i : answer) {
            out.printLine(i);
        }
    }
}
