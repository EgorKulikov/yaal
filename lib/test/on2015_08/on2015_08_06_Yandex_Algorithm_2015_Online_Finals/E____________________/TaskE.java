package on2015_08.on2015_08_06_Yandex_Algorithm_2015_Online_Finals.E____________________;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
    private static final long MOD = (long) (1e9 + 7);

    static long[] factorial = IntegerUtils.generateFactorial(10000 + 100, MOD);
    static long[] reverse = IntegerUtils.generateReverseFactorials(10000 + 100, MOD);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        char[] s = in.readString().toCharArray();
        if (testNumber == 31) {
            System.err.println(s);
        }
        int[] present = new int[26];
        Arrays.fill(present, -1);
        for (int i = 0; i < s.length; i++) {
            if (s[i] != '?') {
                int cur = s[i] - 'a';
                if (present[cur] != -1) {
                    for (int j = present[cur] + 1; j < i; j++) {
                        if (s[j] != '?') {
                            out.printLine(0);
                            return;
                        }
                        s[j] = s[i];
                    }
                }
                present[cur] = i;
            }
        }
        int free = ArrayUtils.count(present, -1);
        long[] answer = new long[free + 1];
        answer[free] = 1;
        long[] next = new long[free + 1];
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '?' && (i == s.length - 1 || s[i + 1] != '?')) {
                Arrays.fill(next, 0);
                int length = 0;
                for (int j = i; j >= 0; j--) {
                    if (s[j] != '?') {
                        break;
                    }
                    length++;
                }
                int freeEnds = 0;
                boolean leftFree = length <= i;
                boolean rightFree = i != s.length - 1;
                if (leftFree) {
                    freeEnds++;
                }
                if (rightFree) {
                    freeEnds++;
                }
                for (int k = 0; k <= free; k++) {
                    for (int l = k; l >= 0 && l > -freeEnds; l--) {
                        next[k - l] += c(length - 1 + freeEnds, l - 1 + freeEnds) * answer[k] % MOD * c(k, l) % MOD * factorial[l] % MOD;
                    }
                }
                for (int k = 0; k <= free; k++) {
                    next[k] %= MOD;
                }
                long[] temp = answer;
                answer = next;
                next = temp;
            }
        }
        long total = 0;
        for (long l : answer) {
            total += l;
        }
        out.printLine(total % MOD);
    }

    private long c(int n, int k) {
        if (k > n) {
            return 0;
        }
        return factorial[n] * reverse[k] % MOD * reverse[n - k] % MOD;
    }
}
