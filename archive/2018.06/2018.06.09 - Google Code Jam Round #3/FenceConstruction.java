package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.collections.map.Indexer;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import static java.lang.Math.atan2;

public class FenceConstruction {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];
        in.readIntArrays(a, b, c, d);
        int[] from = new int[n];
        int[] to = new int[n];
        Indexer<IntIntPair> points = new Indexer<>();
        Map<IntIntPair, Integer> id = new HashMap<>();
        for (int i = 0; i < n; i++) {
            from[i] = points.get(new IntIntPair(a[i], b[i]));
            to[i] = points.get(new IntIntPair(c[i], d[i]));
            id.put(new IntIntPair(from[i], to[i]), i);
            id.put(new IntIntPair(to[i], from[i]), i + n);
        }
        Graph graph = new BidirectionalGraph(points.size());
        for (int i = 0; i < n; i++) {
            graph.addSimpleEdge(from[i], to[i]);
        }
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(2 * n);
        for (int i = 0; i < points.size(); i++) {
            NavigableMap<Double, Integer> order = new TreeMap<>();
            for (int j = 0; j < n; j++) {
                if (from[j] == i) {
                    order.put(atan2(d[j] - b[j], c[j] - a[j]), to[j]);
                } else if (to[j] == i) {
                    order.put(atan2(b[j] - d[j], a[j] - c[j]), from[j]);
                }
            }
            int last = -1;
            for (int j : order.values()) {
                if (last != -1) {
                    setSystem.join(id.get(new IntIntPair(last, i)), id.get(new IntIntPair(i, j)));
                }
                last = j;
            }
            setSystem.join(id.get(new IntIntPair(last, i)), id.get(new IntIntPair(i, order.firstEntry().getValue())));
        }
        IntList answer = new IntArrayList();
        boolean[] done = new boolean[n];
        for (int i = k - 1; i >= -1; i--) {
            while (true) {
                boolean updated = false;
                for (int j = k; j < n; j++) {
                    if (!done[j] && (setSystem.get(j) == setSystem.get(k - 1) || setSystem.get(j + n) == setSystem.get
                            (k - 1))) {
                        setSystem.join(j, j + n);
                        done[j] = true;
                        answer.add(j + 1);
                        updated = true;
                    }
                }
                if (!updated) {
                    break;
                }
            }
            if (i != -1) {
                setSystem.join(i, i + n);
                answer.add(i + 1);
                done[i] = true;
            }
        }
        answer.inPlaceReverse();
        out.print("Case #" + testNumber + ": ");
        out.printLine(answer);
    }
}
