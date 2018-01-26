package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class SudokuSwap {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[][] sudoku = IOUtils.readIntTable(in, 9, 9);
        out.printLine("Case #" + testNumber + ":");
        if (valid(sudoku)) {
            out.printLine("Serendipity");
        } else {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    for (int k = i; k < 9; k++) {
                        for (int l = (i == k) ? j + 1 : 0; l < 9; l++) {
                            int temp = sudoku[i][j];
                            sudoku[i][j] = sudoku[k][l];
                            sudoku[k][l] = temp;
                            if (valid(sudoku)) {
                                out.printLine("(" + (i + 1) + "," + (j + 1) + ")", "<->", "(" + (k + 1) + "," + (l + 1) + ")");
                            }
                            temp = sudoku[i][j];
                            sudoku[i][j] = sudoku[k][l];
                            sudoku[k][l] = temp;
                        }
                    }
                }
            }
        }
    }

    private boolean valid(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            int mask = 0;
            for (int j = 0; j < 9; j++) {
                mask |= 1 << sudoku[i][j];
            }
            if (mask != 1022) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            int mask = 0;
            for (int j = 0; j < 9; j++) {
                mask |= 1 << sudoku[j][i];
            }
            if (mask != 1022) {
                return false;
            }
        }
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                int mask = 0;
                for (int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        mask |= 1 << sudoku[k][l];
                    }
                }
                if (mask != 1022) {
                    return false;
                }
            }
        }
        return true;
    }
}
