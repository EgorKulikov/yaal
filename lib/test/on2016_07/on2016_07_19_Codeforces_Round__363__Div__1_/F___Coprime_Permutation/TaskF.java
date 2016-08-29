package on2016_07.on2016_07_19_Codeforces_Round__363__Div__1_.F___Coprime_Permutation;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.generateDivisorTable;
import static net.egork.numbers.IntegerUtils.generateFactorial;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] p = readIntArray(in, n);
        int[] divisor = generateDivisorTable(n + 1);
        int[] simple = new int[n + 1];
        int[] tps = new int[n + 1];
        int[] pr = new int[n + 1];
        simple[1] = 1;
        pr[1] = 1;
        int[] qty = new int[n + 1];
        qty[1]++;
        for (int i = 2; i <= n; i++) {
            int prev = i / divisor[i];
            if (prev % divisor[i] == 0) {
                simple[i] = simple[prev];
            } else {
                simple[i] = simple[prev] * divisor[i];
            }
            qty[simple[i]]++;
        }
        int[] type = new int[n + 1];
        type[1] = 1;
        int[] rem = new int[n + 1];
        rem[1]++;
        for (int i = 2; i <= n; i++) {
            if (divisor[i] == i) {
                type[i] = n / i;
                rem[type[i]]++;
            }
        }
        tps[1] = 1;
        for (int i = 2; i <= n; i++) {
            if ((long)i * i <= n || divisor[i] != i) {
                continue;
            }
            for (int j = i; j <= n; j += i) {
                simple[j] /= i;
                tps[j] = type[i];
                pr[j] = i;
            }
        }
        int[] direct = new int[n + 1];
        int[] reverse = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (p[i] == 0) {
                continue;
            }
            if (simple[i + 1] != simple[p[i]] || tps[i + 1] != tps[p[i]]) {
                out.printLine(0);
                return;
            }
            if (pr[i + 1] == 0) {
                qty[simple[i + 1]]--;
            } else {
                qty[simple[i + 1] * pr[i + 1]]--;
            }
            if (pr[i + 1] != 0) {
                if (direct[pr[i + 1]] == 0) {
                    direct[pr[i + 1]] = pr[p[i]];
                    rem[tps[i + 1]]--;
                } else if (direct[pr[i + 1]] != pr[p[i]]) {
                    out.printLine(0);
                    return;
                }
                if (reverse[pr[p[i]]] == 0) {
                    reverse[pr[p[i]]] = pr[i + 1];
                } else if (reverse[pr[p[i]]] != pr[i + 1]) {
                    out.printLine(0);
                    return;
                }
            }
        }
        long answer = 1;
        long[] fact = generateFactorial(n + 1, MOD7);
        for (int i : qty) {
            answer *= fact[i];
            answer %= MOD7;
        }
        for (int i : rem) {
            answer *= fact[i];
            answer %= MOD7;
        }
        out.printLine(answer);
    }
}
