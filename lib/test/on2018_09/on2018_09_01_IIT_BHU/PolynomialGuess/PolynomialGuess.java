package on2018_09.on2018_09_01_IIT_BHU.PolynomialGuess;



import net.egork.numbers.Interpolation;

import static net.egork.misc.ArrayUtils.asLong;
import static net.egork.misc.MiscUtils.MOD7;

public class PolynomialGuess {
    public int findValue(int[] p) {
        for (int i = 0; i < p.length; i++) {
            if (p[i] < 0) {
                p[i] += MOD7;
            }
        }
        Interpolation interpolation = new Interpolation(asLong(p), MOD7);
        int result = (int) interpolation.calculate(6);
        if (result > MOD7 / 2) {
            result -= MOD7;
        }
        return result;
    }
}
