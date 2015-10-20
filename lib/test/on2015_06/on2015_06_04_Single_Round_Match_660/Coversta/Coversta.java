package on2015_06.on2015_06_04_Single_Round_Match_660.Coversta;


import net.egork.generated.collections.pair.IntIntPair;
import net.egork.misc.MiscUtils;

import java.util.Arrays;

public class Coversta {
    public int place(String[] a, int[] x, int[] y) {
        int rowCount = a.length;
        int columnCount = a[0].length();
        int[][] table = new int[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                table[i][j] = a[i].charAt(j) - '0';
            }
        }
        int[][] base = new int[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                for (int k = 0; k < x.length; k++) {
                    int xx = x[k] + i;
                    int yy = y[k] + j;
                    if (MiscUtils.isValidCell(xx, yy, rowCount, columnCount)) {
                        base[i][j] += table[xx][yy];
                    }
                }
            }
        }
        IntIntPair[] order = new IntIntPair[rowCount * columnCount];
        for (int i = 0; i < order.length; i++) {
            order[i] = new IntIntPair(i / columnCount, i % columnCount);
        }
        Arrays.sort(order, (f, s) -> base[s.first][s.second] - base[f.first][f.second]);
        int answer = 0;
        for (IntIntPair first : order) {
            for (IntIntPair second : order) {
                int current = base[first.first][first.second] + base[second.first][second.second];
                boolean intersect = false;
                for (int i = 0; i < x.length; i++) {
                    int xx = first.first + x[i];
                    int yy = first.second + y[i];
                    if (!MiscUtils.isValidCell(xx, yy, rowCount, columnCount)) {
                        continue;
                    }
                    for (int j = 0; j < x.length; j++) {
                        if (xx == second.first + x[j] && yy == second.second + y[j]) {
                            intersect = true;
                            current -= table[(xx)][(yy)];
                        }
                    }
                }
                answer = Math.max(answer, current);
                if (!intersect) {
                    break;
                }
            }
        }
        return answer;
    }
}
