package on2015_08.on2015_08_22_SNSS_2015_R4.A______________;


import net.egork.collections.Pair;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int e = in.readInt();
        Graph graph = new BidirectionalGraph(n);
        for (int i = 0; i < e; i++) {
            if (!in.isExhausted()) {
                graph.addSimpleEdge(in.readInt() - 1, in.readInt() - 1);
            } else {
                out.printLine("impossible");
                return;
            }
        }
//        int[] a = new int[e];
//        int[] b = new int[e];
//        IOUtils.readIntArrays(in, a, b);
//        MiscUtils.decreaseByOne(a, b);
//        Graph graph = BidirectionalGraph.createGraph(n, a, b);
        int[] color = new int[n];
        List<Pair<IntList, IntList>> components = new ArrayList<>();
        int[] queue = new int[n];
        for (int i = 0; i < n; i++) {
            if (color[i] != 0) {
                continue;
            }
            int size = 1;
            queue[0] = i;
            color[i] = 1;
            IntList pos = new IntArrayList();
            IntList neg = new IntArrayList();
            pos.add(i);
            for (int j = 0; j < size; j++) {
                int current = queue[j];
                for (int k = graph.firstOutbound(current); k != -1; k = graph.nextOutbound(k)) {
                    int next = graph.destination(k);
                    if (color[next] == color[current]) {
                        out.printLine("impossible");
                        return;
                    }
                    if (color[next] == 0) {
                        color[next] = -color[current];
                        queue[size++] = next;
                        if (color[next] == 1) {
                            pos.add(next);
                        } else {
                            neg.add(next);
                        }
                    }
                }
            }
            components.add(Pair.makePair(pos, neg));
        }
        int[][] last = new int[n + 1][n + 1];
        int sum = 0;
        last[0][0] = 1;
        Collections.reverse(components);
        for (Pair<IntList, IntList> component : components) {
            for (int i = 0; i <= sum; i++) {
                if (last[i][sum - i] != 0) {
                    last[i + component.first.size()][sum - i + component.second.size()] = 1;
                    if (last[i + component.second.size()][sum - i + component.first.size()] == 0) {
                        last[i + component.second.size()][sum - i + component.first.size()] = -1;
                    }
                }
            }
            sum += component.first.size() + component.second.size();
        }
        Collections.reverse(components);
        for (int x = n / 2; x >= 0; x--) {
            String c1 = null;
            int i = x;
            if (last[i][n - i] != 0) {
                int j = n - i;
                char[] answer = new char[n];
                for (Pair<IntList, IntList> component : components) {
                    IntList pos = component.first;
                    IntList neg = component.second;
                    if (last[i][j] == -1) {
                        IntList temp = pos;
                        pos = neg;
                        neg = temp;
                    }
                    for (int k : pos.toArray()) {
                        answer[k] = 'A';
                    }
                    for (int k : neg.toArray()) {
                        answer[k] = 'B';
                    }
                    i -= pos.size();
                    j -= neg.size();
                }
                c1 = new String(answer);
            }
            String c2 = null;
            i = n - x;
            if (last[i][n - i] != 0) {
                int j = n - i;
                char[] answer = new char[n];
                for (Pair<IntList, IntList> component : components) {
                    IntList pos = component.first;
                    IntList neg = component.second;
                    if (last[i][j] == -1) {
                        IntList temp = pos;
                        pos = neg;
                        neg = temp;
                    }
                    for (int k : pos.toArray()) {
                        answer[k] = 'A';
                    }
                    for (int k : neg.toArray()) {
                        answer[k] = 'B';
                    }
                    i -= pos.size();
                    j -= neg.size();
                }
                c2 = new String(answer);
            }
            if (c1 != null) {
                if (c2 != null) {
                    out.printLine(MiscUtils.min(c1, c2));
                    return;
                } else {
                    out.printLine(c1);
                    return;
                }
            } else {
                if (c2 != null) {
                    out.printLine(c2);
                    return;
                }
            }
        }
        throw new RuntimeException();
    }
}
