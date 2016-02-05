package on2016_01.on2016_01_30_Facebook_Hacker_Cup_2016_Round_3.Boomerang_Umbrella;



import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BoomerangUmbrella {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            long s;
            long[] x;
            long[] k;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                s = in.readLong();
                x = new long[n];
                k = new long[n];
                IOUtils.readLongArrays(in, x, k);
            }

            long answer;

            @Override
            public void solve() {
                for (int it = 0; it < 2; it++) {
                    int[] order = ArrayUtils.createOrder(n);
                    ArrayUtils.sort(order, (a, b) -> (int)(x[a] != x[b] ? x[a] - x[b] : k[a] - k[b]));
                    ArrayUtils.orderBy(ArrayUtils.asLong(ArrayUtils.reversePermutation(order)), x, k);
                    long[] val1 = new long[n];
                    long[] val2 = new long[n];
                    for (int i = 0; i < n; i++) {
                        val1[i] = s * k[i] - x[i];
                        val2[i] = x[i] + s * k[i];
                    }
                    ArrayUtils.compress(val1, val2);
                    MaxTree tr1 = new MaxTree(2 * n);
                    MaxTree tr2 = new MaxTree(2 * n);
                    for (int i = 0; i < n; i++) {
                        int v1 = (int) val1[i];
                        int v2 = (int) val2[i];
                        tr1.update(v1, v1, tr1.query(v1, v1) + 1);
                        answer = Math.max(answer, tr1.query(v1, v1) + tr2.query(v2, 2 * n - 1));
                        tr2.update(v2, v2, tr2.query(v2, v2) + 1);
                        answer = Math.max(answer, tr2.query(v2, v2) + tr1.query(0, v1 - 1));
                        if (i == n - 1 || x[i + 1] != x[i]) {
                            long up = -1;
                            int last = 2 * n - 1;
                            for (int j = i; j >= 0 && x[j] == x[i]; j--) {
                                int cv1 = (int) val1[j];
                                int cv2 = (int) val2[j];
                                up = Math.max(up + 1, tr2.query(cv2 + 1, last));
                                answer = Math.max(answer, tr1.query(cv1, cv1) + up);
                                answer = Math.max(answer, tr1.query(0, cv1 - 1) + 1 + up);
                            }
                        }
                    }
                    for (int i = 0; i < n; i++) {
                        x[i] = (int)1e9 - x[i];
                    }
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }

    static class MaxTree extends LongIntervalTree {
   		protected MaxTree(int size) {
   			super(size);
   		}

   		@Override
   		protected long joinValue(long left, long right) {
   			return Math.max(left, right);
   		}

   		@Override
   		protected long joinDelta(long was, long delta) {
   			return Math.max(was, delta);
   		}

   		@Override
   		protected long accumulate(long value, long delta, int length) {
   			return Math.max(value, delta);
   		}

   		@Override
   		protected long neutralValue() {
   			return 0;
   		}

   		@Override
   		protected long neutralDelta() {
   			return 0;
   		}
   	}
}
