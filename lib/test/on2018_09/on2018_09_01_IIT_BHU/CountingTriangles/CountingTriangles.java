package on2018_09.on2018_09_01_IIT_BHU.CountingTriangles;



import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.power;
import static net.egork.numbers.IntegerUtils.reverse;

public class CountingTriangles {
    public int findCount(long N) {
        long n2 = power(2, N - 1, MOD7);
        long n = n2 * 2 % MOD7;
        long p1 = n * (n + 1) % MOD7 * (n + 2) % MOD7;
        long p2 = n2 * (n2 + 1) % MOD7 * (n + 1) % MOD7;
        long p3 = n * (n2 + 1) % MOD7 * (n2 - 1) % MOD7;
        return (int) ((p1 + p2 + p3) * reverse(6, MOD7) % MOD7);
    }
}
