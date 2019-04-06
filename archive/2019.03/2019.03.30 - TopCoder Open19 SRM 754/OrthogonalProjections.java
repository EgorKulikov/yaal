package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;

import static java.lang.Math.min;

public class OrthogonalProjections {
    static final int BUBEN = 250;

    public int[] generate(int n) {
        if (n == 1) {
            return new int[]{0, 0};
        }
        if (n % 2 == 1) {
            return new int[0];
        }
        n /= 2;
        if (n == 1) {
            return new int[]{0, 0, 0, 1};
        }
        if (n == 2) {
            return new int[0];
        }
        if (n < BUBEN + 10) {
            int[] answer = new int[2 * n];
            answer[1] = 1;
            for (int i = 0; i < n - 1; i++) {
                answer[2 * i + 2] = i;
            }
            return answer;
        }
        n--;
        IntList answer = new IntArrayList();
        for (int i = 0; i < BUBEN; i++) {
            answer.add(i);
            answer.add(0);
        }
        int current = 0;
        while (n > 0) {
            int add = min(n, BUBEN);
            current += add;
            answer.add(current);
            answer.add(1);
            n -= add;
        }
        return answer.toArray();
    }
}
