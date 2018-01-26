package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskM {

    private static final long MAX = 1_000_000_000_000_000_005L;
    int[] p3;
    private int n;
    private int a, b;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        a = in.readInt();
        b = in.readInt();
        int q = in.readInt();

        p3 = new int[n + 1];
        p3[0] = 1;
        for (int i = 1; i <= n; i++) {
            p3[i] = p3[i - 1] * 3;
        }

        d = new long[n * n][][];
        for (int i = 0; i < n; i++) {
            for (int col = 0; col < n; col++) {
                d[i * n + col] = new long[col + 1][(n + 1) * (n + 1) * (n + 1) * (n + 1)];
            }
        }
        fill(d, -1);

        int[][] res = new int[n][n];


//        out.printLine(calc(0, 0, build(0, 0, n, 0)));

        for (int tt = 0; tt < q; tt++) {
            long t = in.readLong();
            t--;
            if (t >= calc(0, 0, build(0, 0, n, 0))) {
                out.printLine("No such matrix.");
                out.printLine();
                continue;
            }

            int[] st = new int[n];

            int f0 = 0;
            int f1 = 0;
            int s0 = n;
            int s1 = 0;

            for (int i = 0; i < n; i++) {
                int last = 0;
                for (int col = 0; col < n; col++) {
                    int x = i * n + col;
                    {
                        // add 0
                        int nf0 = f0;
                        int nf1 = f1;
                        int ns0 = s0;
                        int ns1 = s1;

                        if (st[col] == 0) {
                            ns0--;
                            nf0++;
                        } else if (st[col] == 1) {
                            ns1--;
                        } else {
                        }
                        int nmask = build(nf0, nf1, ns0, ns1);
                        if (col == n - 1) {
                            nmask = build(0, 0, nf0, nf1);
                        }
                        if (col != n - 1 || (last >= a && last <= b)) {
                            if (calc(x + 1, col == n - 1 ? 0 : last, nmask) > t) {
                                f0 = nf0;
                                f1 = nf1;
                                s0 = ns0;
                                s1 = ns1;
                                if (st[col] == 1) st[col] = 2;
                                res[i][col] = 0;
                                continue;
                            }
                            t -= calc(x + 1, col == n - 1 ? 0 : last, nmask);
                        }
                    }
                    res[i][col] = 1;
                    if (st[col] == 0) {
                        s0--;
                        f1++;
                        st[col] = 1;
                    } else if (st[col] == 1) {
                        s1--;
                        f1++;
                    } else {
                        throw new RuntimeException();
                    }
                    last++;
                }
                s0 = f0;
                s1 = f1;
                f0 = f1 = 0;
            }
            for (int i = 0; i < n; i++) {
                for (int col = 0; col < n; col++) {
                    out.print(res[i][col]);
                }
                out.printLine();
            }
            out.printLine();
        }
    }

    long calc(int x, int last, int mask) {
        int f0 = mask / (n + 1) / (n + 1) / (n + 1);
        int f1 = mask / (n + 1) / (n + 1) % (n + 1);
        int s0 = mask / (n + 1) % (n + 1);
        int s1 = mask % (n + 1);
        int col = x % n;
        int f2 = col - f0 - f1;
        int s2 = n - col - s0 - s1;
        if (x == n * n) {
            boolean ok = s0 == 0;
            if (ok) {
                return 1;
            } else {
                return 0;
            }
        }
        if (d[x][last][mask] != -1) {
            return d[x][last][mask];
        }
        long res = 0;
        {
            // add 0
            int nf0 = f0;
            int nf1 = f1;
            int ns0 = s0;
            int ns1 = s1;
            if (ns0 != 0) {
                ns0--;
                nf0++;
            } else if (ns1 != 0) {
                ns1--;
            } else {
            }
            int nmask = build(nf0, nf1, ns0, ns1);
            if (col == n - 1) {
                nmask = build(0, 0, nf0, nf1);
            }
            if (col != n - 1 || (last >= a && last <= b)) {
                res += calc(x + 1, col == n - 1 ? 0 : last, nmask);
            }
        }
        if (s0 + s1 > 0) {
            // add 1
            int nf0 = f0;
            int nf1 = f1;
            int ns0 = s0;
            int ns1 = s1;
            if (ns0 != 0) {
                ns0--;
                nf1++;
            } else if (ns1 != 0) {
                ns1--;
                nf1++;
            } else {
                throw new RuntimeException();
            }
            int nmask = build(nf0, nf1, ns0, ns1);
            int nlast = last + 1;
            if (col == n - 1) {
                nmask = build(0, 0, nf0, nf1);
            }
            if (col != n - 1 || (nlast >= a && nlast <= b)) {
                res += calc(x + 1, col == n - 1 ? 0 : nlast, nmask);
                if (res > MAX) res = MAX;
            }
        }
        return d[x][last][mask] = res;
    }

    private int build(int nf0, int nf1, int ns0, int ns1) {
        return (((nf0 * (n + 1) + nf1) * (n + 1)) + ns0) * (n + 1) + ns1;
    }

    long[][][] d;
}
