package on2018_02.on2018_02_07_SNWS_2018_Round_5.E___Biorobotics;



import net.egork.generated.collections.list.LongArray;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.string.SuffixAutomaton;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        char x = in.readCharacter();
        String s = in.readString();
        SuffixAutomaton automaton = new SuffixAutomaton(s);
        long[] visit = new long[automaton.size];
        long[] noVisit = new long[automaton.size];
        noVisit[0] = 1;
        Graph graph = new Graph(automaton.size, automaton.edgeSize);
        for (int i = 0; i < automaton.size; i++) {
            for (int j = automaton.first[i]; j != -1; j = automaton.next[j]) {
                graph.addSimpleEdge(i, automaton.to[j]);
            }
        }
        int[] order = GraphAlgorithms.topologicalSort(graph);
        for (int i : order) {
            for (int j = automaton.first[i]; j != -1; j = automaton.next[j]) {
                if (automaton.label[j] == x) {
                    visit[automaton.to[j]] += visit[i] + noVisit[i];
                } else {
                    visit[automaton.to[j]] += visit[i];
                    noVisit[automaton.to[j]] += noVisit[i];
                }
            }
        }
        out.printLine(new LongArray(visit).sum());
    }
}
