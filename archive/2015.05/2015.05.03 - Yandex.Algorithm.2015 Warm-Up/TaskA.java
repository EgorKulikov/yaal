package net.egork;

import net.egork.collections.heap.Heap;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntIterator;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.intcollection.IntPair;
import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int edgeCount = in.readInt();
        int threshold = in.readInt();
        int[] remaining = new int[count];
        for (int i = 0; i < count; i++) {
            remaining[i] = (2 * edgeCount) / count + ((2 * edgeCount) % count > i ? 1 : 0);
        }
        Heap heap = new Heap((a, b) -> remaining[b] - remaining[a], count);
        for (int i = 0; i < count; i++) {
            if (remaining[i] > 0) {
                heap.add(i);
            }
        }
        List<IntPair> answer = new ArrayList<>();
        while (edgeCount > 0) {
            int current = heap.poll();
            IntList reAdd = new IntArrayList();
            for (int i = 0; i < remaining[current] && edgeCount > 0; i++) {
                int other = heap.poll();
                remaining[other]--;
                reAdd.add(other);
                edgeCount--;
                answer.add(new IntPair(current + 1, other + 1));
            }
            for (int i = 0; i < reAdd.size(); i++) {
                heap.add(reAdd.get(i));
            }
        }
        for (IntPair pair : answer) {
            out.printLine(pair.first, pair.second);
        }
    }
}
