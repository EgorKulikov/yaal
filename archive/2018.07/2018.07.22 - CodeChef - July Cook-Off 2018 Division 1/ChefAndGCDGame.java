package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.count;
import static net.egork.numbers.IntegerUtils.generatePrimes;

public class ChefAndGCDGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        int[] p = generatePrimes(1000);
        int[] qty = new int[n];
        int answer = 1;
        for (int i : p) {
            for (int j = 0; j < n; j++) {
                qty[j] = 0;
                while (a[j] % i == 0) {
                    a[j] /= i;
                    qty[j]++;
                }
            }
            for (int j = 1; ; j++) {
                int balance = 0;
                for (int k = 0; k < n; k++) {
                    if (qty[k] >= j) {
                        balance += (qty[k] - j) >> 1;
                    } else {
                        balance += qty[k] - j;
                    }
                }
                if (balance >= 0) {
                    answer *= i;
                } else {
                    break;
                }
            }
        }
        if (count(a, a[0]) == n) {
            answer *= a[0];
        }
        out.printLine(answer);
    }
}
