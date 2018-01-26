package on2017_05.on2017_05_14_RCC_2017_Elimination_round.A__________________;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.numbers.IntegerUtils.gcd;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b = in.readInt();
        int g = gcd(a, b);
        a /= g;
        b /= g;
        IntList primes = new IntArrayList();
        for (int i = 2; i * i <= a; i++) {
            if (a % i == 0) {
                while (a % (i * i) == 0) {
                    a /= i * i;
                }
                if (a % i == 0) {
                    primes.add(i);
                    a /= i;
                }
            }
        }
        if (a != 1) {
            primes.add(a);
        }
        for (int i = 2; i * i <= b; i++) {
            if (b % i == 0) {
                while (b % (i * i) == 0) {
                    b /= i * i;
                }
                if (b % i == 0) {
                    primes.add(i);
                    b /= i;
                }
            }
        }
        if (b != 1) {
            primes.add(b);
        }
        long result = Long.MAX_VALUE;
        long left = -1;
        long right = -1;
        for (int i = 0; i < (1 << primes.size()); i++) {
            long cLeft = 1;
            long cRight = 1;
            for (int j = 0; j < primes.size(); j++) {
                if ((i >> j & 1) == 1) {
                    cLeft *= primes.get(j);
                } else {
                    cRight *= primes.get(j);
                }
            }
            if (cLeft + cRight < result) {
                result = cLeft + cRight;
                left = cLeft;
                right = cRight;
            }
        }
        out.printLine(left, right);
    }
}
