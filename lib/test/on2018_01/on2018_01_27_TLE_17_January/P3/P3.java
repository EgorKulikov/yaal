package on2018_01.on2018_01_27_TLE_17_January.P3;



import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.fill;
import static net.egork.numbers.IntegerUtils.gcd;

public class P3 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        List<IntIntPair>[] divisors = new List[n];
        for (int i = 0; i < n; i++) {
            divisors[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                divisors[i * j % n].add(new IntIntPair(i, j));
            }
        }
        boolean[] isUnit = new boolean[n];
        for (int i = 0; i < n; i++) {
            isUnit[i] = gcd(i, n) == 1;
        }
        boolean[] isIrreducible = new boolean[n];
        for (int i = 1; i < n; i++) {
            if (!isUnit[i]) {
                isIrreducible[i] = true;
                for (IntIntPair pair : divisors[i]) {
                    if (!isUnit[pair.first] && !isUnit[pair.second]) {
                        isIrreducible[i] = false;
                        break;
                    }
                }
            }
        }
        boolean[] isPrime = new boolean[n];
        boolean[] hasP = new boolean[n];
        boolean[] processed = new boolean[n];
        for (int i = 1; i < n; i++) {
            if (!isUnit[i]) {
                for (int j = 0; j < n; j++) {
                    hasP[j] = false;
                    for (IntIntPair pair : divisors[j]) {
                        if (pair.first == i || pair.second == i) {
                            hasP[j] = true;
                            break;
                        }
                    }
                }
                isPrime[i] = true;
                fill(processed, false);
                for (int j = 0; j < n && isPrime[i]; j++) {
                    int current = i * j % n;
                    if (processed[current]) {
                        continue;
                    }
                    processed[current] = true;
                    for (IntIntPair pair : divisors[current]) {
                        if (!hasP[pair.first] && !hasP[pair.second]) {
                            isPrime[i] = false;
                            break;
                        }
                    }
                }
            }
        }
        out.printLine("Units:");
        for (int i = 0; i < n; i++) {
            if (isUnit[i]) {
                out.printLine(i);
            }
        }
        out.printLine("Irreducibles:");
        for (int i = 0; i < n; i++) {
            if (isIrreducible[i]) {
                out.printLine(i);
            }
        }
        out.printLine("Primes:");
        for (int i = 0; i < n; i++) {
            if (isPrime[i]) {
                out.printLine(i);
            }
        }
    }
}
