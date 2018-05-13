package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;

public class DiversePairsDiv1 {
    public int[] findMaxDiversePairs(int a, int b) {
        int n = b - a;
        int x = n / 5;
        int k = n / 5 * 2;
        if (n % 5 >= 2) {
            k++;
        }
        int i = 0;
        int j = 2 * k - x;
        int ii = x;
        int jj = k;
        if ((i + j) % 2 == (ii + jj) % 2) {
            j++;
        }
        IntList answer = new IntArrayList();
        for (; i < x; i++, j++) {
            answer.add(i + a);
            answer.add(j + a);
        }
        for (; ii < k; ii++, jj++) {
            answer.add(ii + a);
            answer.add(jj + a);
        }
        return answer.toArray();
    }
}
