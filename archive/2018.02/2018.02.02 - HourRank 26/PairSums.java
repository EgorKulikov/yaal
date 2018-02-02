package net.egork;

import net.egork.collections.set.TreapSet;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Long.compare;

public class PairSums {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
/*        long aa = 0;
        int f = 0;
        int t = 0;
        for (int i = 0; i < n; i++) {
            long sum = 0;
            long add = 0;
            for (int j = i; j < n; j++) {
                add += sum * a[j];
                sum += a[j];
                if (add > aa) {
                    f = i;
                    t = j;
                    aa = add;
                }
            }
        }
        System.err.println(f + " " + t + " " + aa);*/
        long[] k = new long[n + 1];
        long[] b = new long[n + 1];
        TreapSet<Integer> set = new TreapSet<>((x, y) -> k[x] == k[y] ? x - y : compare(k[y], k[x]));
        set.add(n);
        long answer = 0;
        long sum = 0;
        long add = 0;
        long sumSq = 0;
        for (int i = 0; i < n; i++) {
            add += sum * a[i];
            sum += a[i];
            sumSq += a[i] * a[i];
            int left = 0;
            int right = set.size() - 1;
            while (right - left >= 3) {
                int lm = (2 * left + right) / 3;
                int rm = (2 * right + left) / 3;
                int lmi = set.get(lm);
                int rmi = set.get(rm);
                long vl = k[lmi] * sum + b[lmi];
                long vr = k[rmi] * sum + b[rmi];
                if (vl < vr) {
                    right = rm;
                } else {
                    left = lm;
                }
            }
            for (int j = left; j <= right; j++) {
                int id = set.get(j);
                answer = Math.max(answer, add - sum * k[id] - b[id]);
            }
            k[i] = sum;
            b[i] = -add - sumSq;
            Integer l = set.floor(i);
            Integer r = set.ceiling(i);
            if (l != null && k[l] == k[i]) {
                if (b[l] <= b[i]) {
                    continue;
                } else {
                    set.remove(l);
                    set.add(i);
                    l = set.floor(i);
                }
            } else if (l == null || r == null) {
                set.add(i);
            } else {
                boolean need = isNeed(k, b, i, l, r);
                if (need) {
                    set.add(i);
                } else {
                    continue;
                }
            }
            while (l != null) {
                Integer ll = set.lower(l);
                if (ll == null) {
                    break;
                }
                if (isNeed(k, b, l, ll, i)) {
                    break;
                } else {
                    set.remove(l);
                    l = ll;
                }
            }
            while (r != null) {
                Integer rr = set.higher(r);
                if (rr == null) {
                    break;
                }
                if (isNeed(k, b, r, i, rr)) {
                    break;
                } else {
                    set.remove(r);
                    r = rr;
                }
            }
        }
        out.printLine(answer);
    }

    protected boolean isNeed(long[] k, long[] b, int i, int l, int r) {
        long db = b[r] - b[l];
        long dk = k[l] - k[r];
        if (dk < 0) {
            db = -db;
            dk = -dk;
        }
        long our = k[i] * db + b[i] * dk;
        long was = k[l] * db + b[l] * dk;
        return our < was;
    }
}
