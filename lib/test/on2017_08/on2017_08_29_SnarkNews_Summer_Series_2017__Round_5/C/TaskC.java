package on2017_08.on2017_08_29_SnarkNews_Summer_Series_2017__Round_5.C;


import net.egork.collections.FenwickTree;
import net.egork.generated.collections.function.IntFilter;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.partialSums;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskC {
    static final int BUBEN = 500;

    int cl;
    int cr;
    IntFilter left = (a -> a >= cl);
    IntFilter right = (b -> b > cr);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int q = in.readInt();
        int[] line = in.readIntArray(n);
        int[] p = in.readIntArray(n);
        decreaseByOne(line);
        IntList[] ids = new IntList[m];
        int[] shift = new int[m];
        for (int i = 0; i < m; i++) {
            ids[i] = new IntArrayList();
        }
        for (int i = 0; i < n; i++) {
            ids[line[i]].add(i);
        }
        long[][] pSums = new long[m][];
        FenwickTree main = new FenwickTree(n);
        IntList big = new IntArrayList();
        for (int i = 0; i < m; i++) {
            if (ids[i].size() >= BUBEN) {
                big.add(i);
                int[] values = new int[ids[i].size()];
                for (int j = 0; j < values.length; j++) {
                    values[j] = p[ids[i].get(j)];
                }
                pSums[i] = partialSums(values);
            } else {
                for (int j = 0; j < ids[i].size(); j++) {
                    main.add(ids[i].get(j), p[ids[i].get(j)]);
                }
            }
        }
        for (int i = 0; i < q; i++) {
            int type = in.readInt();
            if (type == 1) {
                int l = in.readInt() - 1;
                int r = in.readInt() - 1;
                long answer = main.get(l, r);
                for (int j = 0; j < big.size(); j++) {
                    int k = big.get(j);
                    cl = l;
                    cr = r;
                    int ll = ids[k].binarySearch(left);
                    int rr = ids[k].binarySearch(right) - 1;
                    if (ll > rr) {
                        continue;
                    }
                    ll -= shift[k];
                    rr -= shift[k];
                    int size = ids[k].size();
                    if (ll < 0) {
                        ll += size;
                        rr += size;
                    }
                    if (ll >= size) {
                        ll -= size;
                        rr -= size;
                    }
                    if (rr >= size) {
                        answer += pSums[k][size] - pSums[k][ll] + pSums[k][rr - size + 1];
                    } else {
                        answer += pSums[k][rr + 1] - pSums[k][ll];
                    }
                }
                out.printLine(answer);
            } else {
                int x = in.readInt() - 1;
                shift[x]++;
                int size = ids[x].size();
                if (shift[x] == size) {
                    shift[x] = 0;
                }
                if (size < BUBEN) {
                    for (int j = 0; j < size; j++) {
                        main.add(ids[x].get((j + shift[x]) % size), p[ids[x].get(j)]);
                        main.add(ids[x].get((j + shift[x] + size - 1) % size), -p[ids[x].get(j)]);
                    }
                }
            }
        }
    }
}
