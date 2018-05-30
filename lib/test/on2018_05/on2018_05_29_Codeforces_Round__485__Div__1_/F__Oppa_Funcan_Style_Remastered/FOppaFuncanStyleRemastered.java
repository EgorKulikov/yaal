package on2018_05.on2018_05_29_Codeforces_Round__485__Div__1_.F__Oppa_Funcan_Style_Remastered;



import net.egork.generated.collections.list.LongArrayList;
import net.egork.generated.collections.list.LongList;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.math.BigInteger;
import java.util.Arrays;

import static java.math.BigInteger.valueOf;
import static net.egork.numbers.IntegerUtils.generatePrimes;

public class FOppaFuncanStyleRemastered {
    int[] primes = generatePrimes(40_000_000);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.readInt();
        long[] n = new long[t];
        long[] k = new long[t];
        in.readLongArrays(n, k);
        int[] answer = new int[t];
        for (int i = 0; i < t; i++) {
            if (answer[i] != 0) {
                continue;
            }
            LongList divisors = new LongArrayList();
            long number = k[i];
            if (isPrime(number)) {
                divisors.add(number);
            } else {
                for (long j : primes) {
                    if (number % j == 0) {
                        divisors.add(j);
                        do {
                            number /= j;
                        } while (number % j == 0);
                        if (isPrime(number)) {
                            divisors.add(number);
                            break;
                        }
                    }
                }
            }
            if (divisors.size() <= 1) {
                for (int j = i; j < t; j++) {
                    if (k[j] == k[i]) {
                        if (k[i] != 1 && n[j] % divisors.get(0) == 0) {
                            answer[j] = 1;
                        } else {
                            answer[j] = -1;
                        }
                    }
                }
                continue;
            }
            if (divisors.size() == 2) {
                long a = divisors.get(0);
                long b = divisors.get(1);
                BigInteger bigB = valueOf(b);
                BigInteger revA = valueOf(a).modInverse(bigB);
                for (int j = i; j < t; j++) {
                    if (k[j] == k[i]) {
                        long x = valueOf(n[j]).multiply(revA).mod(bigB).longValue();
                        if (n[j] / a >= x) {
                            answer[j] = 1;
                        } else {
                            answer[j] = -1;
                        }
                    }
                }
                continue;
            }
            long[] min = new long[(int) divisors.get(0)];
            Arrays.fill(min, Long.MAX_VALUE / 2);
            min[0] = 0;
            IntHashSet was = new IntHashSet();
            for (long d : divisors) {
                if (d % min.length == 0) {
                    continue;
                }
                int shift = (int) (d % min.length);
                if (was.contains(shift)) {
                    continue;
                }
                was.add(shift);
                int current = 0;
                for (int j = 0; j < min.length; j++) {
                    int next = current + shift;
                    if (next >= min.length) {
                        next -= min.length;
                    }
                    min[next] = Math.min(min[next], min[current] + d);
                    current = next;
                }
                for (int j = 0; j < min.length; j++) {
                    int next = current + shift;
                    if (next >= min.length) {
                        next -= min.length;
                    }
                    if (min[next] <= min[current] + d) {
                        break;
                    }
                    min[next] = min[current] + d;
                    current = next;
                }
            }
            for (int j = i; j < t; j++) {
                if (k[j] == k[i]) {
                    if (min[(int) (n[j] % min.length)] <= n[j]) {
                        answer[j] = 1;
                    } else {
                        answer[j] = -1;
                    }
                }
            }
        }
        for (int i = 0; i < t; i++) {
            out.printLine(answer[i] == 1 ? "YES" : "NO");
        }
    }

    boolean isPrime(long n) {
        return valueOf(n).isProbablePrime(30);
    }
}
