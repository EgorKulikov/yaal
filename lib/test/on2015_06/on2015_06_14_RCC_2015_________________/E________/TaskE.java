package on2015_06.on2015_06_14_RCC_2015_________________.E________;


import net.egork.collections.intcollection.Heap;
import net.egork.collections.map.Counter;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class TaskE {
    public static final int BUBEN = 1001;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int queryCount = in.readInt();
        in.readInt();
        int[] trees = IOUtils.readIntArray(in, count);
        Counter<Integer> chunks = new Counter<>();
        for (int i = 1; i < count; i++) {
            chunks.add(trees[i] - trees[i - 1]);
        }
        int[] size = new int[chunks.size()];
        int[] qty = new int[size.length];
        int at = 0;
        for (Map.Entry<Integer, Long> entry : chunks.entrySet()) {
            size[at] = entry.getKey();
            qty[at++] = (int)(long)entry.getValue();
        }
        int[] need = new int[BUBEN + 1];
        for (int i = 1; i <= BUBEN; i++) {
            for (int j = 0; j < size.length; j++) {
                need[i] += (size[j] - 1) / i * qty[j];
            }
        }
        int current = 0;
        int[] answer = new int[need[BUBEN]];
        int[] curSize = size.clone();
        int[] already = new int[size.length];
        Heap heap = new Heap((f, s) -> curSize[s] - curSize[f], size.length);
        for (int i = 0; i < size.length; i++) {
            heap.add(i);
        }
        while (current < answer.length) {
            at = heap.poll();
            for (int i = current; i < current + qty[at] && i < answer.length; i++) {
                answer[i] = curSize[at];
            }
            current += qty[at];
            already[at]++;
            curSize[at] = (size[at] + already[at]) / (already[at] + 1);
            heap.add(at);
        }
        for (int i = 0; i < queryCount; i++) {
            int query = in.readInt();
            if (query < need[BUBEN]) {
                out.printLine(answer[query]);
            } else {
                int left = 1;
                int right = BUBEN;
                while (left < right) {
                    int middle = (left + right) >> 1;
                    if (need[middle] <= query) {
                        right = middle;
                    } else {
                        left = middle + 1;
                    }
                }
                out.printLine(left);
            }
        }
    }
}
