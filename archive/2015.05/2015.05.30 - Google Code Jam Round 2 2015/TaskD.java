package net.egork;

import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class TaskD {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int rowCount;
            int columnCount;

            int[][] table;
            long answer;

            @Override
            public void read(InputReader in) {
                rowCount = in.readInt();
                columnCount = in.readInt();
            }

            @Override
            public void solve() {
//                table = new int[rowCount][columnCount];
//                answer = go(0, 0);
                long[][] oneTwo = new long[13][rowCount + 1];
                long[][] three = new long[13][rowCount + 1];
                oneTwo[1][0] = 1;
                three[1][0] = 1;
                for (int i = 1; i <= rowCount; i++) {
                    for (int j = 1; j <= 12; j++) {
                        if (j == 5 || j >= 7 && j <= 11) {
                            continue;
                        }
                        if (i >= 2) {
                            three[j][i] += oneTwo[j][i - 2];
                        }
                        oneTwo[j][i] += three[j][i - 1];
                        if (i >= 2) {
                            if (columnCount % 3 == 0) {
                                oneTwo[IntegerUtils.lcm(3, j)][i] += three[j][i - 2] * IntegerUtils.gcd(j, 3);
                            }
                            if (columnCount % 6 == 0) {
                                oneTwo[IntegerUtils.lcm(6, j)][i] += three[j][i - 2] * IntegerUtils.gcd(j, 6);
                            }
                        }
                        if (i >= 3) {
                            if (columnCount % 4 == 0) {
                                oneTwo[IntegerUtils.lcm(4, j)][i] += three[j][i - 3] * IntegerUtils.gcd(j, 4);
                            }
                        }
                    }
                    for (int j = 1; j <= 12; j++) {
                        three[j][i] %= MOD;
                        oneTwo[j][i] %= MOD;
                    }
                }
                answer = 0;
                for (int j = 1; j <= 12; j++) {
                    answer += three[j][rowCount];
                    answer += oneTwo[j][rowCount];
                }
                answer %= MOD;
            }

            private long go(int row, int column) {
                if (column == table[0].length) {
                    return go(row + 1, 0);
                }
                if (row == table.length) {
                    for (int i = 0; i < rowCount; i++) {
                        for (int j = 0; j < columnCount; j++) {
                            if (table[i][j] == 0 || !checkSingle(i, j)) {
                                throw new RuntimeException();
                            }
                            System.err.print(table[i][j]);
                        }
                        System.err.println("");
                    }
                    System.err.println("");
                    return 1;
                }
                long total = 0;
                for (int i = 1; i <= 3; i++) {
                    table[row][column] = i;
                    if (check(row, column)) {
                        total += go(row, column + 1);
                    }
                    table[row][column] = 0;
                }
                return total;
            }

            private boolean check(int row, int column) {
                if (!checkSingle(row, column)) {
                    return false;
                }
                for (int i = 0; i < 4; i++) {
                    int nRow = row + MiscUtils.DX4[i];
                    int nColumn = column + MiscUtils.DY4[i];
                    if (nRow < 0 || nRow >= rowCount) {
                        continue;
                    }
                    if (nColumn < 0) {
                        nColumn = columnCount - 1;
                    } else if (nColumn == columnCount) {
                        nColumn = 0;
                    }
                    if (!checkSingle(nRow, nColumn)) {
                        return false;
                    }
                }
                return true;
            }

            private boolean checkSingle(int row, int column) {
                if (table[row][column] == 0) {
                    return true;
                }
                int exact = 0;
                int any = 0;
                for (int i = 0; i < 4; i++) {
                    int nRow = row + MiscUtils.DX4[i];
                    int nColumn = column + MiscUtils.DY4[i];
                    if (nRow < 0 || nRow >= rowCount) {
                        continue;
                    }
                    if (nColumn < 0) {
                        nColumn = columnCount - 1;
                    } else if (nColumn == columnCount) {
                        nColumn = 0;
                    }
                    if (table[nRow][nColumn] == 0) {
                        any++;
                    } else if (table[nRow][nColumn] == table[row][column]) {
                        exact++;
                    }
                }
                return exact <= table[row][column] && exact + any >= table[row][column];
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 1);
    }
}
