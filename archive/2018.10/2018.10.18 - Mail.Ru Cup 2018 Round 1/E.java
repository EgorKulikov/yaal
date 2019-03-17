package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

import static net.egork.string.StringUtils.reverse;

public class E {
    static class Step {
        int a;
        int b;
        int c;
        int d;

        public Step(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        String[][] start = in.readStringTable(n, m);
        String[][] end = in.readStringTable(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                start[i][j] = reverse(start[i][j]);
                end[i][j] = reverse(end[i][j]);
            }
        }
        int row = -1;
        int col = -1;
        for (int i = 0; i < n && row == -1; i++) {
            for (int j = 0; j < m && row == -1; j++) {
                int st = 0;
                int en = 0;
                for (int k = 0; k < n; k++) {
                    if (i != k) {
                        st += start[k][j].length();
                        en += end[k][j].length();
                    }
                }
                for (int k = 0; k < m; k++) {
                    if (j != k) {
                        st += start[i][k].length();
                        en += end[i][k].length();
                    }
                }
                if (st <= en) {
                    row = i;
                    col = j;
                }
            }
        }
        int[] zero = new int[n];
        int[] one = new int[m];
        List<Step> answer = new ArrayList<>();
        int altRow = row == 0 ? 1 : 0;
        int altCol = col == 0 ? 1 : 0;
        for (int i = 0; i < start[row][col].length(); i++) {
            if (start[row][col].charAt(i) == '0') {
                zero[altRow]++;
                answer.add(new Step(row, col, altRow, col));
            } else {
                one[altCol]++;
                answer.add(new Step(row, col, row, altCol));
            }
        }
        for (int i = 0; i < n; i++) {
            if (i == row) {
                continue;
            }
            for (int j = 0; j < start[i][col].length(); j++) {
                if (start[i][col].charAt(j) == '0') {
                    answer.add(new Step(i, col, row, col));
                    answer.add(new Step(row, col, altRow, col));
                    zero[altRow]++;
                } else {
                    answer.add(new Step(i, col, row, col));
                    answer.add(new Step(row, col, row, altCol));
                    one[altCol]++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (i == col) {
                continue;
            }
            for (int j = 0; j < start[row][i].length(); j++) {
                if (start[row][i].charAt(j) == '0') {
                    answer.add(new Step(row, i, row, col));
                    answer.add(new Step(row, col, altRow, col));
                    zero[altRow]++;
                } else {
                    answer.add(new Step(row, i, row, col));
                    answer.add(new Step(row, col, row, altCol));
                    one[altCol]++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (i == row) {
                continue;
            }
            for (int j = 0; j < m; j++) {
                if (j == col) {
                    continue;
                }
                for (int k = 0; k < start[i][j].length(); k++) {
                    if (start[i][j].charAt(k) == '0') {
                        answer.add(new Step(i, j, i, col));
                        zero[i]++;
                    } else {
                        answer.add(new Step(i, j, row, j));
                        one[j]++;
                    }
                }
            }
        }
        int nZero = 0;
        int nOne = 0;
        for (int i = 0; i < n; i++) {
            if (i == row) {
                continue;
            }
            for (int j = 0; j < m; j++) {
                if (j == col) {
                    continue;
                }
                for (int k = 0; k < end[i][j].length(); k++) {
                    if (end[i][j].charAt(k) == '0') {
                        while (zero[nZero] == 0) {
                            nZero++;
                        }
                        answer.add(new Step(nZero, col, row, col));
                        zero[nZero]--;
                    } else {
                        while (one[nOne] == 0) {
                            nOne++;
                        }
                        answer.add(new Step(row, nOne, row, col));
                        one[nOne]--;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (i == row) {
                continue;
            }
            for (int k = 0; k < end[i][col].length(); k++) {
                if (end[i][col].charAt(k) == '0') {
                    while (zero[nZero] == 0) {
                        nZero++;
                    }
                    answer.add(new Step(nZero, col, row, col));
                    zero[nZero]--;
                } else {
                    while (one[nOne] == 0) {
                        nOne++;
                    }
                    answer.add(new Step(row, nOne, row, col));
                    one[nOne]--;
                }
            }
        }
        for (int j = 0; j < m; j++) {
            if (j == col) {
                continue;
            }
            for (int k = 0; k < end[row][j].length(); k++) {
                if (end[row][j].charAt(k) == '0') {
                    while (zero[nZero] == 0) {
                        nZero++;
                    }
                    answer.add(new Step(nZero, col, row, col));
                    zero[nZero]--;
                } else {
                    while (one[nOne] == 0) {
                        nOne++;
                    }
                    answer.add(new Step(row, nOne, row, col));
                    one[nOne]--;
                }
            }
        }
        for (int k = 0; k < end[row][col].length(); k++) {
            if (end[row][col].charAt(k) == '0') {
                while (zero[nZero] == 0) {
                    nZero++;
                }
                answer.add(new Step(nZero, col, row, col));
                zero[nZero]--;
            } else {
                while (one[nOne] == 0) {
                    nOne++;
                }
                answer.add(new Step(row, nOne, row, col));
                one[nOne]--;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i == row) {
                continue;
            }
            for (int j = 0; j < m; j++) {
                if (j == col) {
                    continue;
                }
                for (int k = 0; k < end[i][j].length(); k++) {
                    answer.add(new Step(row, col, row, j));
                    answer.add(new Step(row, j, i, j));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (i == row) {
                continue;
            }
            for (int k = 0; k < end[i][col].length(); k++) {
                answer.add(new Step(row, col, i, col));
            }
        }
        for (int j = 0; j < m; j++) {
            if (j == col) {
                continue;
            }
            for (int k = 0; k < end[row][j].length(); k++) {
                answer.add(new Step(row, col, row, j));
            }
        }
        out.printLine(answer.size());
        for (Step step : answer) {
            out.printLine(step.a + 1, step.b + 1, step.c + 1, step.d + 1);
        }
    }
}
