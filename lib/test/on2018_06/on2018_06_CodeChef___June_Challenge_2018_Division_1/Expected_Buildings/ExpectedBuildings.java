package on2018_06.on2018_06_CodeChef___June_Challenge_2018_Division_1.Expected_Buildings;



import net.egork.collections.Pair;
import net.egork.collections.map.CPPTreeMap;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Matrix;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import static java.lang.Math.min;
import static java.lang.System.arraycopy;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class ExpectedBuildings {
    long[] base;
    int[] a;
    long[] sums;
//    long totalSum;
    int h;
    long[] temp;
    long[] temp2;
    long[] temp3;
    long[] temp4;

    NavigableMap<Integer, Pair<long[], long[]>> map = new TreeMap<>();
    NavigableMap<Integer, Integer> counter = new CPPTreeMap<>(() -> 0);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Matrix.mod = 163577857;
        int n = in.readInt();
        h = in.readInt();
        int x = in.readInt();
        int k = in.readInt();
        int[] p = in.readIntArray(n);
        decreaseByOne(p);
        a = in.readIntArray(k);
        sums = ArrayUtils.partialSums(a);
        int[] c = in.readIntArray(k);
        long[][] m = new long[k][k];
        for (int i = 0; i < k; i++) {
            m[0][i] = c[i];
        }
        for (int i = 1; i < k; i++) {
            m[i][i - 1] = 1;
        }
        base = Matrix.convert(m);
        temp = new long[k * k];
        temp2 = new long[k * k];
        temp3 = new long[k * k];
        temp4 = new long[k * k];
//        totalSum = getSum(h - 1);
        long answer = 0;
        for (int i : p) {
            /*answer += */getSum(i - x + 1, i);
        }
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == 0) {
                continue;
            }
            answer += getSum(entry.getKey()) * entry.getValue();
        }
        answer %= Matrix.mod;
        answer += Matrix.mod;
        answer %= Matrix.mod;
        answer *= IntegerUtils.reverse(getSum(h - 1), Matrix.mod);
        answer %= Matrix.mod;
        out.printLine(answer);
    }

    void getSum(int from, int to) {
        long result;
        if (from < 0) {
            counter.put(to, counter.get(to) + 1);
            counter.put(h - 1, counter.get(h - 1) + 1);
            counter.put(from + h - 1, counter.get(from + h - 1) - 1);
//            result = getSum(to) + totalSum - getSum(from + h - 1);
        } else {
            counter.put(to, counter.get(to) + 1);
            counter.put(from - 1, counter.get(from - 1) - 1);
//            result = getSum(to) - getSum(from - 1);
        }
//        result %= Matrix.mod;
//        if (result < 0) {
//            result += Matrix.mod;
//        }
//        return result;
    }

    private long getSum(int to) {
        long result = sums[min(to + 1, a.length)];
        if (to < a.length) {
            return result % Matrix.mod;
        }
        calculate(temp, temp2, temp3, temp4, to - a.length + 1);
        for (int i = 0; i < a.length; i++) {
            result += temp[i] * a[a.length - 1 - i] % Matrix.mod;
        }
        return result % Matrix.mod;
    }

    private void calculate(long[] result, long[] power, long[] temp, long[] temp2, int exponent) {
        Map.Entry<Integer, Pair<long[], long[]>> entry = map.floorEntry(exponent);
        if (entry != null && entry.getKey() * 2 > exponent) {
            if (entry.getKey() == exponent) {
                arraycopy(entry.getValue().first, 0, result, 0, result.length);
                arraycopy(entry.getValue().second, 0, power, 0, result.length);
                return;
            }
            calculate(temp, temp2, result, power, exponent - entry.getKey());
            Matrix.multiply(result, temp, entry.getValue().second, Matrix.mod, a.length);
            Matrix.add(result, entry.getValue().first, Matrix.mod);
            Matrix.multiply(power, temp2, entry.getValue().second, Matrix.mod, a.length);
            saveResult(result, power, exponent);
            return;
        }
        if (exponent == 1) {
            arraycopy(base, 0, result, 0, result.length);
            arraycopy(base, 0, power, 0, result.length);
            return;
        }
        if ((exponent & 1) == 0) {
            calculate(temp, temp2, result, power, exponent >> 1);
            Matrix.multiply(result, temp, temp2, Matrix.mod, a.length);
            Matrix.add(result, temp, Matrix.mod);
            Matrix.multiply(power, temp2, temp2, Matrix.mod, a.length);
        } else {
            calculate(result, temp2, temp, power, exponent - 1);
            Matrix.multiply(power, temp2, base, Matrix.mod, a.length);
            Matrix.add(result, power, Matrix.mod);
        }
        saveResult(result, power, exponent);
    }

    private void saveResult(long[] result, long[] power, int exponent) {
        map.put(exponent, Pair.makePair(result.clone(), power.clone()));
    }
}
