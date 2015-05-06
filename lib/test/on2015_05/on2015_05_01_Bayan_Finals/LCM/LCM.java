package on2015_05.on2015_05_01_Bayan_Finals.LCM;



import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

import java.util.Set;

public class LCM {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            public static final long MOD = (long) (1e9 + 7);
            int firstSize;
            long[] first;
            int secondSize;
            long[] second;

            long answer;

            @Override
            public void read(InputReader in) {
                firstSize = in.readInt();
                secondSize = in.readInt();
                first = IOUtils.readLongArray(in, firstSize);
                second = IOUtils.readLongArray(in, secondSize);
            }

            @Override
            public void solve() {
                long firstGCD = 0;
                for (long i : first) {
                    firstGCD = IntegerUtils.gcd(firstGCD, i);
                }
                long secondGCD = 0;
                for (long i : second) {
                    secondGCD = IntegerUtils.gcd(secondGCD, i);
                }
                answer = 1;
                long copy = firstGCD;
                Set<Long> checked = new EHashSet<>();
                for (long i = 2; i * i <= copy; i++) {
                    if (copy % i == 0) {
                        check(i, firstGCD, secondGCD);
                        checked.add(i);
                        do {
                            copy /= i;
                        } while (copy % i == 0);
                    }
                }
                if (copy != 1) {
                    check(copy, firstGCD, secondGCD);
                    checked.add(copy);
                }
                copy = secondGCD;
                for (long i = 2; i * i <= copy; i++) {
                    if (copy % i == 0) {
                        if (!checked.contains(i)) {
                            check(i, firstGCD, secondGCD);
                            checked.add(i);
                        }
                        do {
                            copy /= i;
                        } while (copy % i == 0);
                    }
                }
                if (copy != 1 && !checked.contains(copy)) {
                    check(copy, firstGCD, secondGCD);
                    checked.add(copy);
                }
            }

            private void check(long p, long firstGCD, long secondGCD) {
                long norm = 1;
                int freedom = 0;
                while (firstGCD % p == 0 || secondGCD % p == 0) {
                    if (firstGCD % p == 0) {
                        firstGCD /= p;
                    }
                    if (secondGCD % p == 0) {
                        secondGCD /= p;
                    }
                    norm *= p;
                    freedom++;
                }
                long current = check(first, norm, p, freedom) + check(second, norm, p, freedom) - 1;
                answer *= current;
                answer %= MOD;
            }

            private long check(long[] array, long norm, long p, int freedom) {
                long result = 1;
                for (long i : array) {
                    if (i % norm != 0 || (i / norm) % p != 0) {
                        result *= freedom + 1;
                        result %= MOD;
                    }
                }
                return result;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":");
                out.printLine(answer);
            }
        }, 4);
    }
}
