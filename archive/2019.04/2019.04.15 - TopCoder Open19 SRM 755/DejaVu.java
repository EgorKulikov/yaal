package net.egork;

import net.egork.collections.map.CPPMap;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;

import java.util.Map;

public class DejaVu {
    long[] delta;
    long[] max;

    public int mostDejaVus(int n, int seed, int R) {
        long[] a = new long[n];
        a[0] = seed;
        for (int i = 1; i < n; i++) {
            a[i] = (a[i - 1] * 1664525 + 1013904223) % 4294967296L;
        }
        for (int i = 0; i < n; i++) {
            a[i] %= R;
        }
        delta = new long[4 * n];
        max = new long[4 * n];
        Map<Long, IntList> positions = new CPPMap<>(IntArrayList::new);
        long answer = 0;
        for (int i = 0; i < n; i++) {
            IntList current = positions.get(a[i]);
            current.add(i);
            if (current.size() == 1) {
                continue;
            }
            update(0, 0, n - 1, current.size() == 2 ? 0 : current.get(current.size() - 3) + 1,
                    current.get(current.size() - 2), 1);
            if (current.size() == 2) {
                answer = Math.max(answer, max[0]);
                continue;
            }
            update(0, 0, n - 1, current.size() == 3 ? 0 : current.get(current.size() - 4) + 1,
                    current.get(current.size() - 3), -1);
            answer = Math.max(answer, max[0]);
        }
        return (int)answer;
    }

    private void update(int root, int from, int to, int left, int right, int val) {
        if (from > right || to < left) {
            return;
        }
        if (from >= left && to <= right) {
            delta[root] += val;
            max[root] += val;
            return;
        }
        int middle = (from + to) >> 1;
        update(2 * root + 1, from, middle, left, right, val);
        update(2 * root + 2, middle + 1, to, left, right, val);
        max[root] = Math.max(max[2 * root + 1], max[2 * root + 2]) + delta[root];
    }
}
