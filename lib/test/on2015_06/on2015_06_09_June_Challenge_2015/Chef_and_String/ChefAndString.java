package on2015_06.on2015_06_09_June_Challenge_2015.Chef_and_String;



import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.numbers.IntegerUtils;
import net.egork.string.SubstringAutomaton;
import net.egork.string.SuffixAutomaton;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ChefAndString {
    private static final long MOD = (long) (1e9 + 7);
    int[] ways;
    boolean[] isTerminal;
    SuffixAutomaton automaton;
    long[][] c = IntegerUtils.generateBinomialCoefficients(5001, MOD);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int length = in.readInt();
        int count = in.readInt();
        String s = in.readString();
        automaton = new SuffixAutomaton(s);
        ways = new int[automaton.size];
        Arrays.fill(ways, -1);
        isTerminal = new boolean[automaton.size];
        for (int i = automaton.last; i != -1; i = automaton.link[i]) {
            isTerminal[i] = true;
        }
        int[] qty = new int[length + 1];
        Graph graph = new Graph(automaton.size, automaton.edgeSize);
        for (int i = 0; i < automaton.size; i++) {
            for (int j = automaton.first[i]; j != -1; j = automaton.next[j]) {
                graph.addSimpleEdge(i, automaton.to[j]);
            }
        }
        int[] order = GraphAlgorithms.topologicalSort(graph);
        for (int j = automaton.size - 1; j > 0; j--) {
            int i = order[j];
            qty[get(i)] += automaton.length[i] - automaton.length[automaton.link[i]];
        }
        long[] answer = new long[length + 1];
        for (int i = 1; i <= length; i++) {
            for (int j = i; j <= length; j++) {
                answer[i] += c[j][i] * qty[j];
            }
            answer[i] %= MOD;
        }
        for (int i = 0; i < count; i++) {
            int query = in.readInt();
            if (query > length) {
                out.printLine(0);
            } else {
                out.printLine(answer[query]);
            }
        }
    }

    private int get(int at) {
        if (ways[at] != -1) {
            return ways[at];
        }
        ways[at] = isTerminal[at] ? 1 : 0;
        for (int i = automaton.first[at]; i != -1; i = automaton.next[i]) {
            ways[at] += get(automaton.to[i]);
        }
        return ways[at];
    }
}
