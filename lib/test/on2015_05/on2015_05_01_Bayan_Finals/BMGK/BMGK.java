package on2015_05.on2015_05_01_Bayan_Finals.BMGK;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class BMGK {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            public static final long MOD = (long) (1e9 + 7);

            int count;
            int[] p;
            int[] alpha;

            long answer;

            @Override
            public void read(InputReader in) {
                count = in.readInt();
                p = new int[count];
                alpha = new int[count];
                IOUtils.readIntArrays(in, p, alpha);
            }

            @Override
            public void solve() {
                answer = 1;
                for (int i : alpha) {
                    answer *= i + 1;
                    answer %= MOD;
                }
                long sigma = 1;
                for (int i = count - 1; i >= 0; i--) {
                    long residue = 0;
                    int current = p[i];
                    while (current != 1) {
                        residue += current;
                        boolean found = false;
                        for (int j = 2; j * j <= current; j++) {
                            if (current % j == 0) {
                                current -= current / j;
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            current--;
                        }
                    }
                    residue %= MOD;
                    long qty = 1;
                    for (int j = 0; j < i; j++) {
                        qty *= alpha[j] + 1;
                        qty %= MOD;
                    }
                    long reverse = IntegerUtils.reverse(p[i] - 1, MOD);
                    long exponent = 1;
                    for (int j = 0; j < alpha[i]; j++) {
                        answer += (alpha[i] - j) * qty % MOD * sigma % MOD * exponent % MOD * residue % MOD;
                        exponent *= p[i];
                        exponent %= MOD;
                    }
                    exponent *= p[i];
                    exponent %= MOD;
                    sigma *= reverse;
                    sigma %= MOD;
                    sigma *= exponent - 1;
                    sigma %= MOD;
                }
                answer %= MOD;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":");
                out.printLine(answer);
            }
        }, 4);
    }
}
