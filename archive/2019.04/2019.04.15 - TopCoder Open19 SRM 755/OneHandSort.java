package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;

import static net.egork.misc.ArrayUtils.reversePermutation;

public class OneHandSort {
    public int[] sortShelf(int[] target) {
        IntList answer = new IntArrayList();
        int[] position = reversePermutation(target);
        int n = target.length;
        boolean[] done = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (done[i]) {
                continue;
            }
            int j = i;
            do {
                done[j] = true;
                answer.add(j);
                j = position[j];
            } while (j != i);
            answer.add(n);
        }
        return answer.toArray();
    }
}
