package net.egork;

import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.collections.set.EHashSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class Seating {
    private NavigableSet<Integer>[] low;
    private NavigableMap<Integer, Integer> high;
    private LongIntervalTree tree;
    private NavigableSet<Integer> all;
    private int[] length;
    private int threshold;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int size = in.readInt();
        int count = in.readInt();
        threshold = 60;
        low = new NavigableSet[threshold];
        high = new TreeMap<Integer, Integer>();
        for (int i = 0; i < threshold; i++)
            low[i] = new TreeSet<Integer>();
        if (size > threshold)
            high.put(0, size);
        for (int i = 0; i < threshold && i < size; i++)
            low[i].add(0);
        tree = new LongIntervalTree(size) {
            @Override
            protected long joinValue(long left, long right) {
                if (left == -2)
                    return right;
                return left;
            }

            @Override
            protected long joinDelta(long was, long delta) {
                if (delta == -2)
                    return was;
                return delta;
            }

            @Override
            protected long accumulate(long value, long delta, int length) {
                if (delta == -2)
                    return value;
                return delta;
            }

            @Override
            protected long neutralValue() {
                return -2;
            }

            @Override
            protected long neutralDelta() {
                return -2;
            }
        };
        all = new TreeSet<Integer>();
        int answer = 0;
        length = new int[size];
        length[0] = size;
        for (int i = 0; i < count; i++) {
            char type = in.readCharacter();
            if (type == 'A') {
                int qty = in.readInt();
                int position;
                if (qty <= threshold) {
                    if (low[qty - 1].isEmpty())
                        position = -1;
                    else
                        position = low[qty - 1].first();
                } else {
                    position = -1;
                    for (Map.Entry<Integer, Integer> entry : high.entrySet()) {
                        if (entry.getValue() >= qty) {
                            position = entry.getKey();
                            break;
                        }
                    }
                }
                if (position == -1) {
                    answer++;
                    continue;
                }
                int remaining = length[position] - qty;
                remove(position);
                add(position + qty, remaining);
            } else {
                int from = in.readInt() - 1;
                int to = in.readInt() - 1;
                Set<Integer> set = new EHashSet<Integer>(all.subSet(from, true, to + 1, true));
                int max = to;
                for (int j : set) {
                    max = Math.max(max, j + length[j] - 1);
                    remove(j);
                }
                to = max;
                if (from > 0) {
                    int min = (int) tree.query(from - 1, from - 1);
                    if (min >= 0) {
                        from = min;
                        to = Math.max(to, from + length[from] - 1);
                        remove(from);
                    }
                }
                add(from, to - from + 1);
            }
        }
        out.printLine(answer);
    }

    private void add(int position, int value) {
        if (value == 0)
            return;
        for (int j = 0; j < threshold && j < value; j++)
            low[j].add(position);
        if (value > threshold)
            high.put(position, value);
        all.add(position);
        tree.update(position, position + value - 1, position);
        length[position] = value;
    }

    private void remove(int position) {
        for (int j = 0; j < threshold && j < length[position]; j++)
            low[j].remove(position);
        if (length[position] > threshold)
            high.remove(position);
        all.remove(position);
        tree.update(position, position + length[position] - 1, -1);
        length[position] = 0;
    }
}
