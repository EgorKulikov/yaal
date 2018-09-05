package on2018_09.on2018_09_01_IIT_BHU.FunctionGame;



import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.generateReverse;
import static net.egork.numbers.IntegerUtils.power;
import static net.egork.numbers.MultiplicativeFunction.PHI;

public class FunctionGame {
    public int findValue(int m, int n) {
        long[] phi = PHI.calculateUpTo(m + 1);
        long[] rev = generateReverse(m + 1, MOD7);
        long answer = n;
        for (int i = 2; i <= m; i++) {
            answer += phi[i] * (power(i, n, MOD7) - 1) % MOD7 * rev[i - 1] % MOD7;
        }
        return (int) (answer % MOD7);
    }
}
