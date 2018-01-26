package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.generated.collections.comparator.IntComparator;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;

import static java.lang.Math.abs;

public class ExtremeSpanningTrees {
    public long minTime(int[] a, int[] b, int[] w, int[] m1, int[] m2) {
        int n = m1.length + 1;
        int m = a.length;
        boolean[][] lessThan = new boolean[m][m];
        for (int i = 0; i < n - 1; i++) {
            IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n);
            for (int j = 0; j < n - 1; j++) {
                if (i != j) {
                    setSystem.join(a[m1[i]], b[m1[i]]);
                }
            }
            for (int j = 0; j < m; j++) {
                if (setSystem.get(a[j]) != setSystem.get(b[j])) {
                    lessThan[m1[i]][j] = true;
                }
            }
        }
        for (int i = 0; i < n - 1; i++) {
            IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n);
            for (int j = 0; j < n - 1; j++) {
                if (i != j) {
                    setSystem.join(a[m2[i]], b[m2[i]]);
                }
            }
            for (int j = 0; j < m; j++) {
                if (setSystem.get(a[j]) != setSystem.get(b[j])) {
                    lessThan[j][m2[i]] = true;
                }
            }
        }
        long answer = 0;
        boolean[] processed = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (processed[i]) {
                continue;
            }
            IntList current = new IntArrayList();
            boolean hasDifferent = false;
            for (int j = i + 1; j < n; j++) {
                if (lessThan[i][j] && lessThan[j][i]) {
                    current.add(j);
                } else if (lessThan[i][j] ^ lessThan[j][i]) {
                    hasDifferent = true;
                }
            }
            if (!hasDifferent) {
                current.add(i);
                current.sort(new IntComparator() {
                    @Override
                    public int compare(int first, int second) {
                        return w[first] - w[second];
                    }
                });
                int to = w[current.get(current.size() / 2)];
                for (int j : current) {
                    processed[j] = true;
                    answer += abs(to - w[j]);
                }
            }
        }
        IntList up = new IntArrayList();
        IntList down = new IntArrayList();
        for (int i = 0; i < n - 1; i++) {
            if (!processed[m1[i]]) {
                up.add(m1[i]);
            }
            if (!processed[m2[i]]) {
                down.add(m2[i]);
            }
        }
        IntList[][] vertices = new IntList[up.size()][down.size()];
        for (int i = 0; i < up.size(); i++) {
            for (int j = 0; j < down.size(); j++) {
                vertices[i][j] = new IntArrayList();
            }

        }
        return 0L;
    }
}
