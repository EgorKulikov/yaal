package on2015_06.on2015_06_27_CodeChef_Snackdown_2015___Final_Round.CardShuffling;



import net.egork.collections.intcollection.IntTreapArray;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class CardShuffling {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int size = in.readInt();
        int count = in.readInt();
        int type = in.readInt();
        IntTreapArray array = new IntTreapArray(ArrayUtils.createOrder(size));
        for (int i = 0; i < count; i++) {
            int from = in.readInt() - 1;
            int to = in.readInt() - 1;
            array.putBack(from, to);
        }
        int[] permutation = array.toArray();
        if (type == 1) {
            permutation = power(in.readLong(), new int[size], new int[size], permutation);
            for (int i = 0; i < size; i++) {
                permutation[i]++;
            }
            out.printLine(permutation);
        } else {
            int[] target = IOUtils.readIntArray(in, size);
            MiscUtils.decreaseByOne(target);
            ArrayUtils.reversePermutation(target);
            ArrayUtils.reversePermutation(permutation);
            boolean[] visited = new boolean[size];
            long answer = 0;
            long mod = 1;
            for (int i = 0; i < size && mod <= 1_000_000_0000_000L; i++) {
                if (visited[i]) {
                    continue;
                }
                int at = i;
                int current = 0;
                int toTarget = -1;
                do {
                    visited[at] = true;
                    if (target[i] == at) {
                        toTarget = current;
                    }
                    at = permutation[at];
                    current++;
                } while (at != i);
                if (toTarget == -1) {
                    throw new RuntimeException();
                }
                answer = IntegerUtils.findCommon(answer, mod, toTarget, current);
                if (answer == -1) {
                    throw new RuntimeException();
                }
                mod = IntegerUtils.lcm(mod, current);
            }
            if (mod > 1_000_000_000_000L && !Arrays.equals(target, power(answer, new int[size], new int[size], permutation))) {
                throw new RuntimeException();
            }
            out.printLine(answer);
        }
    }

    private int[] power(long exponent, int[] result, int[] temp, int[] permutation) {
        if (exponent == 0) {
            for (int i = 0; i < result.length; i++) {
                result[i] = i;
            }
            return result;
        }
        if ((exponent & 1) == 1) {
            power(exponent - 1, temp, result, permutation);
            multiplyPermutations(result, temp, permutation);
        } else {
            power(exponent >> 1, temp, result, permutation);
            multiplyPermutations(result, temp, temp);
        }
        return result;
    }

    public static void multiplyPermutations(int[] result, int[] first, int[] second) {
   		int count = first.length;
   		for (int i = 0; i < count; i++) {
   			result[i] = first[second[i]];
   		}
   	}
}
