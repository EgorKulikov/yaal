package on2016_01.on2016_01_31_Facebook_Hacker_Cup_2016_Round_3.Transportation;



import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;
import net.egork.concurrency.TaskFactory;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Transportation {
    public static final long MOD = (long) (1e9 + 7);
    static final long[] fact = IntegerUtils.generateFactorial(500001, MOD);
    static final long[] rev = IntegerUtils.generateReverseFactorials(fact.length, MOD);

    static long c(int n, int m) {
        if (m < 0 || m > n) {
            return 0;
        }
        return fact[n] * rev[m] % MOD * rev[n - m] % MOD;
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, new TaskFactory() {
            @Override
            public Task newTask() {
                return new Task() {


                    int n;
                    int k;
                    int l;
                    char[] stop;

                    @Override
                    public void read(InputReader in) {
                        n = in.readInt();
                        k = in.readInt();
                        l = in.readInt();
                        stop = IOUtils.readCharArray(in, l);
                    }

                    long answer;

                    @Override
                    public void solve() {
                        if (k == n - 1) {
                            answer = l * IntegerUtils.reverse(4, MOD) % MOD;
                            return;
                        }
                        k = n - k;
                        long[] pos = new long[n];
                        int at = 0;
                        for (int i = 0; i < l; i++) {
                            if (stop[i] == '1') {
                                pos[at++] = i;
                            }
                        }
                        long[] rev = pos.clone();
                        ArrayUtils.reverse(rev);
                        long[] res = multiply(pos, rev);
                        long[] negSums = new long[n];
                        for (int i = 1; i < n; i++) {
                            negSums[i] = -res[n - i - 1] - res[2 * n - i - 1];
                            negSums[i] %= MOD;
                            negSums[i] *= 2;
                            negSums[i] %= MOD;
                            if (negSums[i] < 0) {
                                negSums[i] += MOD;
                            }
                        }
                        long s1 = 0;
                        long s2 = 0;
                        long s3 = 0;
                        long sumSq = res[n - 1] * 2 % MOD;
                        for (int i = 0; i <= n - k; i++) {
                            s1 += 2 * l * pos[i] % MOD;
                            s1 %= MOD;
                            s2 += 2 * l * pos[n - i - 1] % MOD;
                            s2 %= MOD;
                            s3 += (long)l * l % MOD;
                            s3 %= MOD;
                            long current = (sumSq + negSums[i + 1] + s1 - s2 + s3) * c(n - 2 - i, k - 2) % MOD;
                            answer += current % MOD;
                        }
                        answer %= MOD;
                        if (answer < 0) {
                            answer += MOD;
                        }
                        answer *= IntegerUtils.reverse(4 * l, MOD);
                        answer %= MOD;
                        answer *= IntegerUtils.reverse(c(n, k), MOD) % MOD;
                        answer %= MOD;
                    }

                    private long[] multiply(long[] a, long[] b) {
//                return FastFourierTransform.multiply(a, b);
                        FFT fft1 = new FFT(998244353);
                        long[] res1 = new long[a.length * 2 - 1];
                        fft1.multiply(res1, a, b, a.length);
                        FFT fft2 = new FFT(1107296257);
                        long[] res2 = new long[a.length * 2 - 1];
                        fft2.multiply(res2, a, b, a.length);
                        long[] result = new long[a.length * 2 - 1];
                        for (int i = 0; i < result.length; i++) {
                            result[i] = IntegerUtils.findCommon(res1[i], fft1.MOD, res2[i], fft2.MOD);
                        }
                        return result;
                    }


                    @Override
                    public void write(OutputWriter out, int testNumber) {
                        out.printLine("Case #" + testNumber + ":", answer);
                    }
                };
            }
        }, 4);
    }

    static class FFT {
        long MOD;
        long root;
        long reverseRoot;
        int rootPower = 1 << 23;

        public FFT(long MOD) {
            this.MOD = MOD;
            for (int i = 2; ; i++) {
                if (IntegerUtils.power(i, IntegerUtils.power(2, 22, MOD - 1), MOD) != 1 && IntegerUtils.power(i, IntegerUtils.power(2, 23, MOD - 1), MOD) == 1) {
                    root = i;
                    reverseRoot = IntegerUtils.reverse(i, MOD);
                    break;
                }
            }
        }

        long[] aa;
       	long[] bb;

       	public void multiply(long[] result, long[] first, long[] second, int length) {
            int resultSize = Integer.highestOneBit(result.length - 1) << 2;
          		resultSize = Math.max(resultSize, 4);
       		if (aa == null) {
       			aa = new long[resultSize];
       			bb = new long[resultSize];
       		}
       		Arrays.fill(aa, 0, resultSize, 0);
       		Arrays.fill(bb, 0, resultSize, 0);
       		for (int i = 0; i < length; i++)
       			aa[i] = first[i];
       		for (int i = 0; i < length; i++)
       			bb[i] = second[i];
       		fft(aa, false, resultSize);
       		if (first == second) {
       			System.arraycopy(aa, 0, bb, 0, resultSize);
       		} else
       			fft(bb, false, resultSize);
       		for (int i = 0; i < resultSize; i++) {
       			aa[i] *= bb[i];
       			aa[i] %= MOD;
       		}
       		fft(aa, true, resultSize);
       		for (int i = 0; i < result.length; i++)
       			result[i] = aa[i] % MOD;

       	}

       	private void fft(long[] array, boolean invert, int size) {
       		for (int i = 1, j = 0; i < size; ++i) {
       			int bit = size >> 1;
       			for (; j >= bit; bit >>= 1)
       				j -= bit;
       			j += bit;
       			if (i < j) {
       				long temp = array[i];
       				array[i] = array[j];
       				array[j] = temp;
       			}
       		}

       		for (int len = 2; len <= size; len <<= 1) {
       			long wlen = invert ? reverseRoot : root;
       			for (int i = len; i < rootPower; i <<= 1)
       				wlen =	wlen * wlen % MOD;
       			int half = len >> 1;
       			for (int i = 0; i < size; i += len) {
       				long w = 1;
       				for (int j = 0; j < half; ++j) {
       					long u = array[i + j], v = array[i + j + half] * w % MOD;
       					array[i + j] = u + v < MOD ? u + v : u + v - MOD;
       					array[i + j + half] = u - v >= 0 ? u - v : u - v + MOD;
       					w =	w * wlen % MOD;
       				}
       			}
       		}
       		if (invert) {
       			long reverseSize = IntegerUtils.reverse(size, MOD);
       			for (int i = 0; i < size; ++i)
       				array[i] = array[i] * reverseSize % MOD;
       		}
       	}
    }
}
