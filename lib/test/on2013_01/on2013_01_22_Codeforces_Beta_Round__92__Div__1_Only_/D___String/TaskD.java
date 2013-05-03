package on2013_01.on2013_01_22_Codeforces_Beta_Round__92__Div__1_Only_.D___String;



import net.egork.misc.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.string.SuffixAutomaton;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        final SuffixAutomaton automaton = new SuffixAutomaton(s);
        int size = automaton.size;
        int[] qty = new int[size];
        int current = 0;
        for (int i = 0; i < s.length(); i++) {
            current = automaton.to[automaton.findEdge(current, s.charAt(i))];
            qty[current]++;
        }
        int[] order = ArrayUtils.createOrder(size);
        ArrayUtils.sort(order, new IntComparator() {
            public int compare(int first, int second) {
                return automaton.length[second] - automaton.length[first];
            }
        });
        for (int i : order) {
            if (automaton.link[i] != -1)
                qty[automaton.link[i]] += qty[i];
        }
        int[] paths = new int[size];
        Arrays.fill(paths, -1);
        paths[0] = 1;
        int[] reverseFirst = new int[size];
        int[] reverseNext = new int[4 * s.length()];
        int[] reverseTo = new int[4 * s.length()];
        Arrays.fill(reverseFirst, -1);
        int edgeCount = 0;
        for (int i = 0; i < size; i++) {
            int edge = automaton.first[i];
            while (edge != -1) {
                int to = automaton.to[edge];
                reverseNext[edgeCount] = reverseFirst[to];
                reverseFirst[to] = edgeCount;
                reverseTo[edgeCount++] = i;
                edge = automaton.next[edge];
            }
        }
        for (int i = 1; i < size; i++)
            countPaths(i, paths, reverseFirst, reverseNext, reverseTo);
        long answer = 0;
        for (int i = 1; i < size; i++)
            answer += (long) qty[i] * (qty[i] + 1) / 2 * paths[i];
        out.printLine(answer);
    }

    private int countPaths(int vertex, int[] paths, int[] reverseFirst, int[] reverseNext, int[] reverseTo) {
        if (paths[vertex] != -1)
            return paths[vertex];
        paths[vertex] = 0;
        int edge = reverseFirst[vertex];
        while (edge != -1) {
            paths[vertex] += countPaths(reverseTo[edge], paths, reverseFirst, reverseNext, reverseTo);
            edge = reverseNext[edge];
        }
        return paths[vertex];
    }
}
