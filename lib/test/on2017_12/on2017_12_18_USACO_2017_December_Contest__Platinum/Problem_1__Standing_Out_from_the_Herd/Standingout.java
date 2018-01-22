package on2017_12.on2017_12_18_USACO_2017_December_Contest__Platinum.Problem_1__Standing_Out_from_the_Herd;


import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.string.SuffixAutomaton;

import static net.egork.misc.ArrayUtils.reverse;

public class Standingout {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String[] names = in.readStringArray(n);
        IntList all = new IntArrayList();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < names[i].length(); j++) {
                all.add(-names[i].charAt(j));
            }
            all.add(i);
        }
        SuffixAutomaton automaton = new SuffixAutomaton(all, true);
        Graph graph = new Graph(automaton.size);
        for (int i = 0; i < automaton.size; i++) {
            for (int j = automaton.first[i]; j != -1; j = automaton.next[j]) {
                graph.addSimpleEdge(i, automaton.to[j]);
            }
        }
        int[] order = GraphAlgorithms.topologicalSort(graph);
        int[] unique = new int[automaton.size];
        reverse(order);
        for (int i : order) {
            unique[i] = -1;
            for (int j = automaton.first[i]; j != -1; j = automaton.next[j]) {
                if (automaton.label[j] >= 0) {
                    if (unique[i] == -1 || unique[i] == automaton.label[j]) {
                        unique[i] = automaton.label[j];
                    } else {
                        unique[i] = -2;
                    }
                } else if (unique[automaton.to[j]] != -1) {
                    if (unique[i] == -1 || unique[i] == unique[automaton.to[j]]) {
                        unique[i] = unique[automaton.to[j]];
                    } else {
                        unique[i] = -2;
                    }
                }
            }
        }
        reverse(order);
        int[] qty = new int[order.length];
        qty[0] = 1;
        for (int i : order) {
            for (int j = automaton.first[i]; j != -1; j = automaton.next[j]) {
                if (automaton.label[j] < 0) {
                    qty[automaton.to[j]] += qty[i];
                }
            }
        }
        long[] answer = new long[n];
        for (int i = 1; i < unique.length; i++) {
            if (unique[i] >= 0) {
                answer[unique[i]] += qty[i];
            }
        }
        for (long i : answer) {
            out.printLine(i);
        }
    }
}
