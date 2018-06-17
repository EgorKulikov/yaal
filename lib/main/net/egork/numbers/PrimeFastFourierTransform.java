package net.egork.numbers;

import java.util.Arrays;

/**
 * @author egor@egork.net
 */
public class PrimeFastFourierTransform {
    private final long MOD;
    private final long root;
    private final long reverseRoot;
    private int rootPower;
    private long[] aa;
    private long[] bb;

    public PrimeFastFourierTransform(long MOD) {
        this.MOD = MOD;
        rootPower = 1;
        int pw = 0;
        while ((MOD - 1) % (2 * rootPower) == 0) {
            rootPower *= 2;
            pw++;
        }
        for (int i = 2; ; i++) {
            if (IntegerUtils.power(i, IntegerUtils.power(2, pw - 1, MOD - 1), MOD) != 1 &&
                    IntegerUtils.power(i, IntegerUtils.power(2, pw, MOD - 1), MOD) == 1) {
                root = i;
                reverseRoot = IntegerUtils.reverse(i, MOD);
                break;
            }
        }
    }

    public void multiply(long[] result, long[] first, long[] second, int length) {
        int resultSize = Integer.highestOneBit(result.length - 1) << 2;
        resultSize = Math.max(resultSize, 4);
        if (aa == null || aa.length < resultSize) {
            aa = new long[resultSize];
            bb = new long[resultSize];
        }
        Arrays.fill(aa, 0, resultSize, 0);
        Arrays.fill(bb, 0, resultSize, 0);
        for (int i = 0; i < length; i++) {
            aa[i] = first[i];
        }
        for (int i = 0; i < length; i++) {
            bb[i] = second[i];
        }
        fft(aa, false, resultSize);
        if (first == second) {
            System.arraycopy(aa, 0, bb, 0, resultSize);
        } else {
            fft(bb, false, resultSize);
        }
        for (int i = 0; i < resultSize; i++) {
            aa[i] *= bb[i];
            aa[i] %= MOD;
        }
        fft(aa, true, resultSize);
        for (int i = 0; i < result.length; i++) {
            result[i] = aa[i] % MOD;
        }

    }

    private void fft(long[] array, boolean invert, int size) {
        for (int i = 1, j = 0; i < size; ++i) {
            int bit = size >> 1;
            for (; j >= bit; bit >>= 1) {
                j -= bit;
            }
            j += bit;
            if (i < j) {
                long temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        for (int len = 2; len <= size; len <<= 1) {
            long wlen = invert ? reverseRoot : root;
            for (int i = len; i < rootPower; i <<= 1) {
                wlen = wlen * wlen % MOD;
            }
            int half = len >> 1;
            for (int i = 0; i < size; i += len) {
                long w = 1;
                for (int j = 0; j < half; ++j) {
                    long u = array[i + j], v = array[i + j + half] * w % MOD;
                    array[i + j] = u + v < MOD ? u + v : u + v - MOD;
                    array[i + j + half] = u - v >= 0 ? u - v : u - v + MOD;
                    w = w * wlen % MOD;
                }
            }
        }
        if (invert) {
            long reverseSize = IntegerUtils.reverse(size, MOD);
            for (int i = 0; i < size; ++i) {
                array[i] = array[i] * reverseSize % MOD;
            }
        }
    }
}