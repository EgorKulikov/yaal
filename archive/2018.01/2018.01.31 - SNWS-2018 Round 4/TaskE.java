package net.egork;

import net.egork.collections.map.CPPMap;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        Map<String, NavigableSet<Integer>> buildings = new CPPMap<>(() -> new TreeSet<>());
        for (int i = 0; i < k; i++) {
            int city = in.readInt();
            buildings.get(in.readString()).add(city);
        }
        int m = in.readInt();
        String[] seen = in.readStringArray(m);
        int at = 0;
        int[] direct = new int[m];
        for (int i = 0; i < m; i++) {
            Integer next = buildings.get(seen[i]).ceiling(at);
            if (next == null) {
                out.printLine("impossible");
                return;
            }
            direct[i] = at = next;
        }
        at = n - 1;
        int[] reverse = new int[m];
        for (int i = m - 1; i >= 0; i--) {
            at = buildings.get(seen[i]).floor(at);
            reverse[i] = at;
        }
        if (Arrays.equals(direct, reverse)) {
            out.printLine("unique");
        } else {
            out.printLine("ambiguous");
        }
    }
}
