package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.reversePermutation;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class DASequenceOfPermutations {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] p = in.readIntArray(n);
        int[] q = in.readIntArray(n);
        decreaseByOne(p, q);
        if (k >= 4) {
            int[] qRev = reversePermutation(q);
            int[] pRev = reversePermutation(p);
            int[] qRevP = multiplyPermutations(qRev, p);
            int[] pref = multiplyPermutations(qRevP, multiplyPermutations(q, pRev));
            int[] prefPower = new int[n];
            int[] temp = new int[n];
            int pow = (k - 4) / 6;
            power(pref, pow, prefPower, temp);
            int[] post = reversePermutation(prefPower);
            int[] nP = multiplyPermutations(prefPower, multiplyPermutations(multiplyPermutations(qRev,
                    multiplyPermutations(pRev, q)), post));
            int[] nQ = multiplyPermutations(prefPower, multiplyPermutations(multiplyPermutations(qRevP,
                                multiplyPermutations(qRev, multiplyPermutations(pRev, q))), post));
            p = nP;
            q = nQ;
            k -= 6 * pow + 3;
        }
        for (int i = 0; i < k - 1; i++) {
            int[] next = multiplyPermutations(reversePermutation(p), q);
            p = q;
            q = next;
        }
        for (int i = 0; i < n; i++) {
            p[i]++;
        }
        out.printLine(p);
    }

    private void power(int[] p, int n, int[] res, int[] temp) {
        if (n == 0) {
            for (int i = 0; i < res.length; i++) {
                res[i] = i;
            }
            return;
        }
        if ((n & 1) == 1) {
            power(p, n - 1, temp, res);
            inlineMultiplyPermutations(res, temp, p);
        } else {
            power(p, n >> 1, temp, res);
            inlineMultiplyPermutations(res, temp, temp);
        }
    }

    public static void inlineMultiplyPermutations(int[] result, int[] first, int[] second) {
        int count = first.length;
        for (int i = 0; i < count; i++) {
            result[i] = second[first[i]];
        }
    }

    public static int[] multiplyPermutations(int[] first, int[] second) {
        int count = first.length;
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = second[first[i]];
        }
        return result;
    }


}
