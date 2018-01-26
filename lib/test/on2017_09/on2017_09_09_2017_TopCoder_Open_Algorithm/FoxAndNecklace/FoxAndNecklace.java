package on2017_09.on2017_09_09_2017_TopCoder_Open_Algorithm.FoxAndNecklace;



import static java.util.Arrays.binarySearch;
import static java.util.Arrays.copyOf;
import static net.egork.generated.collections.comparator.IntComparator.REVERSE;
import static net.egork.misc.ArrayUtils.partialSums;
import static net.egork.misc.ArrayUtils.sort;

public class FoxAndNecklace {
    int[] val;
    long[] sums;

    public String possible(int[] value, int k, int minimalSum, long d) {
        d *= 2;
        for (int i = 2; i < k; i++) {
            d = (d + i - 1) / i;
        }
        sort(value, REVERSE);
        val = value;
        sums = partialSums(val);
        if (k > 8) {
            return go(0, k, minimalSum, d) <= 0 ? "Possible" : "Impossible";
        }
        int half = (k - 1) / 2;
        int other = k - half - 1;
        int[][][] sums = new int[value.length - other][half + 1][];
        sums[0][0] = new int[1];
        sums[0][0][0] = 1;
        for (int i = 1; i <= half; i++) {
            sums[0][i] = new int[0];
        }
        for (int i = 1; i < value.length - other; i++) {
            sums[i][0] = new int[1];
            sums[i][0][0] = 1;
            for (int j = 1; j <= half; j++) {
                sums[i][j] = copyOf(sums[i - 1][j], sums[i - 1][j].length + sums[i - 1][j - 1].length);
                for (int l = 0; l < sums[i - 1][j - 1].length; l++) {
                    sums[i][j][l + sums[i - 1][j].length] = sums[i - 1][j - 1][l] + 2 * value[i - 1];
                }
                sort(sums[i][j]);
                sums[i - 1][j - 1] = null;
            }
        }
        int[][] current = new int[other + 1][];
        int[][] next = new int[other + 1][];
        current[0] = new int[1];
        for (int i = 1; i <= other; i++) {
            current[i] = new int[0];
        }
        for (int i = value.length - 1; i >= half; i--) {
            if (i < value.length - other) {
                for (int j : current[other]) {
                    int required = minimalSum - j - value[i];
                    int index = binarySearch(sums[i][half], 2 * required);
                    d -= sums[i][half].length + index + 1;
                    if (d <= 0) {
                        return "Possible";
                    }
                }
            }
            next[0] = new int[1];
            for (int j = 1; j <= other; j++) {
                next[j] = copyOf(current[j], current[j].length + current[j - 1].length);
                for (int l = 0; l < current[j - 1].length; l++) {
                    next[j][l + current[j].length] = current[j - 1][l] + value[i];
                }
            }
            int[][] temp = current;
            current = next;
            next = temp;
        }
        return "Impossible";
    }

    private long go(int pos, int k, int minimalSum, long d) {
        if (k == 0) {
            return d - 1;
        }
        if (pos + k > val.length || sums[pos + k] - sums[pos] < minimalSum) {
            return d;
        }
        d = go(pos + 1, k - 1, minimalSum - val[pos], d);
        if (d <= 0) {
            return d;
        }
        return go(pos + 1, k, minimalSum, d);
    }
}
