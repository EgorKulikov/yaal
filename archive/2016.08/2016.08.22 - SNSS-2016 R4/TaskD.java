package net.egork;

import net.egork.collections.intcollection.Heap;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int l = in.readInt();
        int r = in.readInt();
        int[] ql = new int[1000];
        int[] qr = new int[1000];
        int[] sDigs = new int[1000];
        for (int i = 0; i < 1000; i++) {
            sDigs[i] = i % 10 + i / 10 % 10 + i / 100;
        }
        for (int i = 0; i < l; i++) {
            ql[in.readInt()]++;
        }
        for (int i = 0; i < r; i++) {
            qr[in.readInt()]++;
        }
        IntList answer = new IntArrayList();
        int[] temp = new int[1000];
        for (int i = 0; i <= 27; i++) {
            IntList left = new IntArrayList();
            for (int j = 0; j < 1000; j++) {
                if (sDigs[j] == i) {
                    left.add(j);
                }
            }
            left.sort((x, y) -> ql[y] - ql[x]);
            Heap heap = new Heap(1000, (x, y) -> qr[y] - qr[x], 1000);
            for (int j : left.toArray()) {
                if (qr[j] != 0) {
                    heap.add(j);
                }
            }
            for (int j : left.toArray()) {
                int size = 0;
                for (int k = 0; k < ql[j] && !heap.isEmpty(); k++) {
                    int current = heap.poll();
                    answer.add(1000 * j + current);
                    if (--qr[current] > 0) {
                        temp[size++] = current;
                    }
                }
                for (int k = 0; k < size; k++) {
                    heap.add(temp[k]);
                }
            }
        }
        out.printLine(answer.size() == 0 ? -1 : answer.size());
        for (int i : answer.toArray()) {
            out.printFormat("%06d\n", i);
        }
    }
}
