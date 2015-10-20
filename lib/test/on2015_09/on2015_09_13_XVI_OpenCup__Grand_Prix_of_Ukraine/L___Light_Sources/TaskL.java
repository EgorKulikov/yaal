package on2015_09.on2015_09_13_XVI_OpenCup__Grand_Prix_of_Ukraine.L___Light_Sources;


import net.egork.generated.collections.comparator.IntComparator;
import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskL {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int k = in.readInt();
        Point[] p = new Point[n];
        for (int i = 0; i < n; i++) {
            p[i] = Point.readPoint(in);
        }
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            color[i] = in.readInt() - 1;
        }
        final Point[] q = new Point[m];
        for (int i = 0; i < m; i++) {
            q[i] = Point.readPoint(in);
        }

        int[][][] w = new int[m][m][m];

        double[][] dst = new double[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                dst[i][j] = q[i].distance(q[j]);
            }
        }
//        System.err.println("Blya");

        boolean[][][] side = new boolean[m][m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (i == j) {
                    continue;
                }
                Line line = q[i].line(q[j]);
                for (int l = 0; l < n; l++) {
                    side[i][j][l] = Math.signum(line.value(p[l])) > 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                for (int t = j + 1; t < m; t++) {
//                    Polygon polygon = new Polygon(q[i], q[j], q[t]);
                    for (int x = 0; x < n; x++) {
                        if (side[i][j][x] == side[j][t][x] && side[i][j][x] == side[t][i][x]) {
                            w[i][j][t] |= (1 << color[x]);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                for (int l = 0; l < m; l++) {
                    w[i][j][l] = w[i][j][l] | w[i][l][j] | w[j][i][l] | w[j][l][i] | w[l][i][j] | w[l][j][i];
                }
            }
        }

//        System.err.println("Blya");

        double res = Double.POSITIVE_INFINITY;
        for (int i = 0; i < m; i++) {
            int[] id = new int[m];
            int nn = 0;
            for (int j = 0; j < m; j++) {
                if (q[j].y > q[i].y || (q[j].y == q[i].y && q[j].x > q[i].x)) {
                    id[nn++] = j;
                }
            }
            final int finalI = i;
            ArrayUtils.sort(id, 0, nn, new IntComparator() {
                @Override
                public int compare(int first, int second) {
                    double x1 = q[first].x - q[finalI].x;
                    double x2 = q[second].x - q[finalI].x;
                    double y1 = q[first].y - q[finalI].y;
                    double y2 = q[second].y - q[finalI].y;
                    return Double.compare(x1 * y2, x2 * y1);
                }
            });
            double[][] d = new double[nn][1 << k];
            for (int j = 0; j < nn; j++) {
                d[j][0] = dst[i][id[j]];
                for (int mm = 1; mm < (1 << k); mm++) {
                    d[j][mm] = Double.POSITIVE_INFINITY;
                }
            }
            for (int j = 0; j < nn; j++) {
                for (int mm = 0; mm < (1 << k); mm++) {
                    for (int t = j + 1; t < nn; t++) {
                        int e = w[i][id[j]][id[t]];
                        d[t][mm | e] = Math.min(d[t][mm | e], d[j][mm] + dst[id[j]][id[t]]);
                    }
                }
                res = Math.min(res, d[j][(1 << k) - 1] + dst[i][id[j]]);
            }
        }
        if (res == Double.POSITIVE_INFINITY) {
            out.printLine(-1);
        } else {
            out.printLine(res);
        }

    }
}
