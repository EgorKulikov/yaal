package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.egork.misc.ArrayUtils.*;

public class TwoFlowers {
    int[] id;
    int[] nId;
    int[] key;
    int[] size;
    int[] nSize;

    int current;
    int answer = 1;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[][] a = in.readIntTable(n, m);
        System.err.println("Read");
        id = createOrder(n * m);
        size = createArray(n * m, 1);
        int[] first = new int[2 * n * m];
        int[] second = new int[2 * n * m];
        int[] from = new int[2 * n * m];
        int[] to = new int[2 * n * m];
        int qty = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = i * m + j;
                if (i < n - 1) {
                    int y = (i + 1) * m + j;
                    if (a[i][j] == a[i + 1][j]) {
                        joinMain(x, y);
                    } else {
                        first[qty] = min(a[i][j], a[i + 1][j]);
                        second[qty] = max(a[i][j], a[i + 1][j]);
                        from[qty] = x;
                        to[qty++] = y;
                    }
                }
                if (j < m - 1) {
                    int y = i * m + j + 1;
                    if (a[i][j] == a[i][j + 1]) {
                        joinMain(x, y);
                    } else {
                        first[qty] = min(a[i][j], a[i][j + 1]);
                        second[qty] = max(a[i][j], a[i][j + 1]);
                        from[qty] = x;
                        to[qty++] = y;
                    }
                }
            }
        }
        int[] order = createOrder(qty);
        sort(order, (u, v) -> first[u] == first[v] ? second[u] - second[v] : first[u] - first[v]);
        System.err.println("Build");
        for (int i = 0; i < n * m; i++) {
            getMain(i);
        }
        System.err.println("Build");
        nId = new int[n * m];
        nSize = new int[n * m];
        key = new int[n * m];
        for (int i = 0; i < qty; i++) {
            int j = order[i];
            if (i == 0 || first[j] != first[order[i - 1]] || second[j] != second[order[i - 1]]) {
                current++;
            }
            joinSide(from[j], to[j]);
        }
        out.printLine(answer);
    }

    private void joinSide(int x, int y) {
        x = getSide(id[x]);
        y = getSide(id[y]);
        if (x != y) {
            nSize[x] += nSize[y];
            answer = Math.max(answer, nSize[x]);
            nId[y] = x;
        }
    }

    private int getSide(int x) {
        if (key[x] != current) {
            key[x] = current;
            nId[x] = id[x];
            nSize[x] = size[x];
        }
        if (x == nId[x]) {
            return x;
        }
        return nId[x] = getSide(nId[x]);
    }

    private void joinMain(int x, int y) {
        x = getMain(x);
        y = getMain(y);
        if (x != y) {
            size[x] += size[y];
            answer = Math.max(answer, size[x]);
            id[y] = x;
        }
    }

    private int getMain(int x) {
        if (x == id[x]) {
            return x;
        }
        return id[x] = getMain(id[x]);
    }
}
