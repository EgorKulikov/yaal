package on2016_08.on2016_08_25_August_Circuits.Ash_and_shopping;




import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.copyOfRange;
import static net.egork.misc.ArrayUtils.concatenate;

public class AshAndShopping {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        int k = in.readInt();
        long[] mult = new long[k];
        mult[0]++;
        mult[1 % k]++;
        long[] ans = power(n, mult);
        out.printLine(ans[0]);
    }

    private long[] power(long n, long[] mult) {
        if (n == 0) {
            long[] res = new long[mult.length];
            res[0] = 1;
            return res;
        }
        if ((n & 1) == 0) {
            long[] res = power(n >> 1, mult);
            return multiplyExt(res, res);
        } else {
            return multiplyExt(power(n - 1, mult), mult);
        }
    }

    private long[] multiplyExt(long[] a, long[] b) {
        int length = a.length;
        b = b.clone();
        long st = b[0];
        for (int i = 1; i < length; i++) {
            b[i - 1] = b[i];
        }
        b[length - 1] = st;
        a = concatenate(a, a);
        b = copyOf(b, 2 * length);
        long[] result = copyOfRange(multiply(a, b), length - 1, 2 * length - 1);
        for (int i = 0; i < result.length; i++) {
            result[i] %= 100000;
        }
        return result;
    }

    FFT fft1 = new FFT(998244353);
    FFT fft2 = new FFT(1107296257);

    private long[] multiply(long[] a, long[] b) {
//                return FastFourierTransform.multiply(a, b);
        long[] res1 = new long[a.length];
        fft1.multiply(res1, a, b, a.length);
        long[] res2 = new long[a.length];
        fft2.multiply(res2, a, b, a.length);
        long[] result = new long[a.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = IntegerUtils.findCommon(res1[i], fft1.MOD, res2[i], fft2.MOD);
        }
        return result;
    }

    static class FFT {
        long MOD;
        long root;
        long reverseRoot;
        int rootPower = 1 << 23;

        public FFT(long MOD) {
            this.MOD = MOD;
            for (int i = 2; ; i++) {
                if (IntegerUtils.power(i, IntegerUtils.power(2, 22, MOD - 1), MOD) != 1 &&
                        IntegerUtils.power(i, IntegerUtils.power(2, 23, MOD - 1), MOD) == 1) {
                    root = i;
                    reverseRoot = IntegerUtils.reverse(i, MOD);
                    break;
                }
            }
        }

        long[] aa;
        long[] bb;

        public void multiply(long[] result, long[] first, long[] second, int length) {
            int resultSize = Integer.highestOneBit(result.length - 1) << 1;
            resultSize = Math.max(resultSize, 4);
            if (aa == null) {
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
            if (first != second) {
                fft(bb, false, resultSize);
            } else {
                System.arraycopy(aa, 0, bb, 0, resultSize);
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

}
