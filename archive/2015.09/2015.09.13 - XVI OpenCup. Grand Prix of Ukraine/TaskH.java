package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TaskH {

    class Partition {
        int[] p;

        public Partition(int[] p) {
            this.p = p;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Partition partition = (Partition) o;
            return Arrays.equals(p, partition.p);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(p);
        }
    }

    Set<Partition> pset;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        double[] prob = new double[n];
        for (int i = 0; i < n; i++) {
            prob[i] = in.readDouble();
        }
        int[][] s = new int[n][];
        for (int i = 0; i < n; i++) {
            s[i] = new int[in.readInt()];
            for (int j = 0; j < s[i].length; j++) {
                s[i][j] = in.readInt() - 1;
            }
        }

        pset = new HashSet<>();
        genAll(new Partition(new int[]{n}));

        List<Partition> plist = new ArrayList<>();
        plist.addAll(pset);

        Collections.sort(plist, new Comparator<Partition>() {
            @Override
            public int compare(Partition o1, Partition o2) {
                return Integer.compare(o1.p.length, o2.p.length);
            }
        });

        Map<Partition, Integer> id = new HashMap<>();
        for (int i = 0; i < plist.size(); i++) {
            id.put(plist.get(i), i);
        }

        int m = plist.size();
        double[][] trans = new double[m][m];

        for (int i = 1; i < plist.size(); i++) {
            Partition p = plist.get(i);

            for (int x = 0; x < p.p.length; x++) {
                for (int y = x + 1; y < p.p.length; y++) {
                    double pr = p.p[x] * p.p[y] / (n * (n - 1) / 2d);
                    Partition pp = new Partition(new int[p.p.length - 1]);
                    for (int k = 0; k < pp.p.length; k++) {
                        if (k < x) pp.p[k] = p.p[k];
                        else if (k == x) pp.p[k] = p.p[x] + p.p[y];
                        else if (k < y) pp.p[k] = p.p[k];
                        else pp.p[k] = p.p[k + 1];
                    }
                    Arrays.sort(pp.p);
                    int j = id.get(pp);
                    trans[i][j] += pr;
                }
            }
        }

        double[][] e = new double[m][n];

        for (int i = 1; i < m; i++) {
            Partition p = plist.get(i);
//            double pr = 0;
//            for (int x = 0; x < p.p.length; x++) {
//                for (int y = x + 1; y < p.p.length; y++) {
//                    pr += p.p[x] * p.p[y];
//                }
//            }
//            pr /= n * (n - 1) / 2;

            double[][] a = new double[n][n + 1];


            for (int j = 0; j < n; j++) {
                a[j][n] = 1;
                a[j][j] = 1;
                for (int jj : s[j]) {

                    double ppp = 1d / s[j].length;
                    for (int ii = 0; ii < i; ii++) if (trans[i][ii] > 0) {
                        a[j][n] +=  prob[j] / s[j].length * trans[i][ii] * e[ii][jj];
                        ppp -= prob[j] / s[j].length * trans[i][ii];
                    }
                    a[j][jj] -= ppp;
                }
            }
            for (int ii = 0; ii < n; ii++) {
                int kk = ii;
                for (int jj = ii + 1; jj < n; jj++) {
                    if (Math.abs(a[jj][ii]) > Math.abs(a[kk][ii])) {
                        kk = jj;
                    }
                }
                double[] t = a[ii];
                a[ii] = a[kk];
                a[kk] = t;
                for (int jj = n; jj >= ii; jj--) {
                    a[ii][jj] /= a[ii][ii];
                }
                for (int jj = 0; jj < n; jj++) {
                    if (ii == jj) continue;
                    for (int ll = n; ll >= ii; ll--) {
                        a[jj][ll] -= a[jj][ii] * a[ii][ll];
                    }
                }
            }
            for (int ii = 0; ii < n; ii++) {
                e[i][ii] = a[ii][n];
            }
        }
//        double res = Double.POSITIVE_INFINITY;
//        for (int i = 0; i < n; i++) {
            //res += e[m - 1][i];
//            res = Math.min(e[m - 1][i], res);
//        }
//        res /= n;
        out.printLine(e[m - 1][0]);
    }

    private void genAll(Partition partition) {
        if (pset.contains(partition)) return;
        pset.add(partition);
        for (int i = 0; i < partition.p.length; i++) {
            for (int j = 1; j < partition.p[i]; j++) {
                Partition newPartition = new Partition(new int[partition.p.length + 1]);
                System.arraycopy(partition.p, 0, newPartition.p, 0, partition.p.length);
                newPartition.p[i] = j;
                newPartition.p[newPartition.p.length - 1] = partition.p[i] - j;
                Arrays.sort(newPartition.p);
                genAll(newPartition);
            }
        }
    }
}
