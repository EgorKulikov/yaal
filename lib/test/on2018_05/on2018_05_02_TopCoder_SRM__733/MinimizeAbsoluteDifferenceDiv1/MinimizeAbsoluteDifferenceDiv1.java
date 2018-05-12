package on2018_05.on2018_05_02_TopCoder_SRM__733.MinimizeAbsoluteDifferenceDiv1;



import net.egork.numbers.Rational;

import static java.util.Arrays.copyOf;
import static net.egork.misc.ArrayUtils.createOrder;
import static net.egork.misc.ArrayUtils.nextPermutation;

public class MinimizeAbsoluteDifferenceDiv1 {
    public int[] findTuple(int[] x) {
        Rational result = Rational.MAX_VALUE;
        int[] answer = new int[5];
        int[] order = createOrder(5);
        do {
            Rational candidate = new Rational(x[order[0]], x[order[1]]).subtract(new Rational(x[order[2]],
                    x[order[3]])).abs();
            if (candidate.compareTo(result) < 0) {
                result = candidate;
                answer = order.clone();
            }
        } while (nextPermutation(order));
        return copyOf(answer, 4);
    }
}
