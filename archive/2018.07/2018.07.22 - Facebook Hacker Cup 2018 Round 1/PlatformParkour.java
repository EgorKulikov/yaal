package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class PlatformParkour {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            int m;
            int h1;
            int h2;
            int w;
            int x;
            int y;
            int z;
            int[] a;
            int[] b;
            int[] u;
            int[] d;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                m = in.readInt();
                h1 = in.readInt();
                h2 = in.readInt();
                w = in.readInt();
                x = in.readInt();
                y = in.readInt();
                z = in.readInt();
                a = new int[m];
                b = new int[m];
                u = new int[m];
                d = new int[m];
                in.readIntArrays(a, b, u, d);
            }

            int answer;

            @Override
            public void solve() {
                decreaseByOne(a, b);
                int[] h = new int[n];
                h[0] = h1;
                h[1] = h2;
                for (int i = 2; i < n; i++) {
                    h[i] = (int) (((long)w * h[i - 2] + (long)x * h[i - 1] + y) % z);
                }
                for (int i = 0; i < n; i++) {
                    h[i] *= 2;
                }
                int[] up = createArray(n, 2000000);
                int[] down = createArray(n, 2000000);
                for (int i = 0; i < m; i++) {
                    if (a[i] > b[i]) {
                        int temp = a[i];
                        a[i] = b[i];
                        b[i] = temp;
                        temp = u[i];
                        u[i] = d[i];
                        d[i] = temp;
                    }
                    for (int j = a[i]; j < b[i]; j++) {
                        up[j] = Math.min(up[j], u[i] * 2);
                        down[j] = Math.min(down[j], d[i] * 2);
                    }
                }
                int left = 0;
                int right = 2000000;
                while (left < right) {
                    int middle = (left + right) >> 1;
                    int from = 0;
                    int to = 2000000;
                    boolean good = true;
                    for (int i = 0; i < n; i++) {
                        from = Math.max(from, h[i] - middle);
                        to = Math.min(to, h[i] + middle);
                        if (from > to) {
                            good = false;
                            break;
                        }
                        from -= down[i];
                        to += up[i];
                    }
                    if (good) {
                        right = middle;
                    } else {
                        left = middle + 1;
                    }
                }
                answer = left;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer / 2d);
            }
        }, 4);
    }
}
