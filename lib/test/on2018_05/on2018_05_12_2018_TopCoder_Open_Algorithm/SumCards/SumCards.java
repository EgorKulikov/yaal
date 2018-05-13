package on2018_05.on2018_05_12_2018_TopCoder_Open_Algorithm.SumCards;



import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.generatePowers;

public class SumCards {
    public int findsum(String[] a, String[] b) {
        int n = a.length;
        long answer = 0;
        long[] p = generatePowers(10, 31, MOD7);
        for (int x = 0; x < 2; x++) {
            for (int i = 0; i < n; i++) {
                long current = 1;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        long by = 0;
                        int cmp = compare(a[i], a[j]);
                        if (cmp > 0 || cmp == 0 && i > j) {
                            by++;
                        } else {
                            by += p[a[j].length()];
                        }
                        cmp = compare(a[i], b[j]);
                        if (cmp > 0 || cmp == 0 && i > j) {
                            by++;
                        } else {
                            by += p[b[j].length()];
                        }
                        current *= by;
                        current %= MOD7;
                    }
                }
                long number = 0;
                for (int j = 0; j < a[i].length(); j++) {
                    number *= 10;
                    number += a[i].charAt(j) - '0';
                    number %= MOD7;
                }
                answer += current * number;
                answer %= MOD7;
            }
            String[] temp = a;
            a = b;
            b = temp;
        }
        return (int) answer;
    }

    int compare(String a, String b) {
        return (a + b).compareTo(b + a);
    }
}
