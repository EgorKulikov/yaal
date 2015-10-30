package on2015_04.on2015_04_28_Single_Round_Match_657.RookGraph;


import net.egork.generated.collections.pair.IntIntPair;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;

import java.util.ArrayList;
import java.util.List;

public class RookGraph {
    List<IntIntPair> components = new ArrayList<>();
    long[] factorial;
    long[] reverse;
    private static final int MOD = (int) (1e9 + 7);
    long[][][] result;
    int N;

    public int countPlacements(int N, String[] graph) {
        this.N = N;
        components = new ArrayList<>();
        factorial = IntegerUtils.generateFactorial(N + 1, MOD);
        reverse = IntegerUtils.generateReverseFactorials(N + 1, MOD);
        int count = graph.length;
        boolean[] processed = new boolean[count];
        int[] queue = new int[count];
        int[] row = new int[count];
        int[] column = new int[count];
        boolean[][] occupied = new boolean[count][count];
        for (int i = 0; i < count; i++) {
            if (processed[i]) {
                continue;
            }
            ArrayUtils.fill(occupied, false);
            occupied[0][0] = true;
            queue[0] = i;
            int size = 1;
            processed[i] = true;
            int nRow = 1;
            int nColumn = 1;
            for (int j = 0; j < size; j++) {
                int current = queue[j];
                for (int k = 0; k < count; k++) {
                    if (graph[current].charAt(k) == '1' && !processed[k]) {
                        int cRow = row[current];
                        int cColumn = nColumn;
                        boolean valid = true;
                        for (int l = 0; l < size; l++) {
                            int at = queue[l];
                            if (graph[at].charAt(k) == '1' && row[at] != cRow) {
                                cColumn = column[at];
                            }
                        }
                        for (int l = 0; l < size; l++) {
                            int at = queue[l];
                            if ((row[at] == cRow || column[at] == cColumn) != (graph[at].charAt(k) == '1')) {
                                valid = false;
                                break;
                            }
                        }
                        if (valid && !occupied[cRow][cColumn]) {
                            processed[k] = true;
                            queue[size++] = k;
                            row[k] = cRow;
                            column[k] = cColumn;
                            occupied[cRow][cColumn] = true;
                            nColumn = Math.max(nColumn, cColumn + 1);
                            continue;
                        }
                        cRow = nRow;
                        cColumn = column[current];
                        valid = true;
                        for (int l = 0; l < size; l++) {
                            int at = queue[l];
                            if (graph[at].charAt(k) == '1' && column[at] != cColumn) {
                                cRow = row[at];
                            }
                        }
                        for (int l = 0; l < size; l++) {
                            int at = queue[l];
                            if ((row[at] == cRow || column[at] == cColumn) != (graph[at].charAt(k) == '1')) {
                                valid = false;
                                break;
                            }
                        }
                        if (!valid || occupied[cRow][cColumn]) {
                            return 0;
                        }
                        occupied[cRow][cColumn] = true;
                        processed[k] = true;
                        queue[size++] = k;
                        row[k] = cRow;
                        column[k] = cColumn;
                        nRow = Math.max(nRow, cRow + 1);
                    }
                }
            }
            components.add(new IntIntPair(nRow, nColumn));
        }
        result = new long[components.size() + 1][N + 1][N + 1];
        ArrayUtils.fill(result, -1);
		return (int)calculate(0, N, N);
    }

    private long calculate(int at, int rows, int columns) {
        if (result[at][rows][columns] != -1) {
            return result[at][rows][columns];
        }
        if (at == components.size()) {
            return result[at][rows][columns] = a(N, rows) * a(N, columns) % MOD;
        }
        result[at][rows][columns] = 0;
        int cRows = components.get(at).first;
        int cColumns = components.get(at).second;
        if (rows >= cRows && columns >= cColumns) {
            result[at][rows][columns] += calculate(at + 1, rows - cRows, columns - cColumns);
        }
        if (rows >= cColumns && columns >= cRows && cRows + cColumns > 2) {
            result[at][rows][columns] += calculate(at + 1, rows - cColumns, columns - cRows);
        }
        return result[at][rows][columns] %= MOD;
    }

    private long a(int n, int m) {
        return factorial[n] * reverse[m] % MOD;
    }
}
