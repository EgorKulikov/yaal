package net.egork;

import net.egork.chelper.tester.Verdict;
import net.egork.chelper.tester.State;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.io.InputStream;
import java.io.OutputStream;

import static net.egork.misc.MiscUtils.isValidCell;

public class EInteractor {
    public Verdict interact(InputStream input, InputStream solutionOutput, OutputStream solutionInput, State<Boolean> state) {
        InputReader in = new InputReader(input);
        InputReader solOut = new InputReader(solutionOutput);
        OutputWriter solInp = new OutputWriter(solutionInput);
        int n = in.readInt();
        char[][] map = in.readTable(n, n);
        solInp.printLine(n);
        solInp.flush();
        int requests = 0;
        int[][] can = new int[n][n];
        while (true) {
            char type = solOut.readCharacter();
            if (type == '!') {
                String answer = solOut.readString();
                int row = 0;
                int col = 0;
                for (char c : answer.toCharArray()) {
                    if (c == 'R') {
                        col++;
                    } else {
                        row++;
                    }
                    if (!isValidCell(row, col, n, n) || map[row][col] == '#') {
                        return new Verdict(Verdict.VerdictType.WA, "Bad path");
                    }
                }
                if (row != n - 1 || col != n - 1) {
                    return new Verdict(Verdict.VerdictType.WA, "Path not to end");
                }
                return Verdict.OK;
            }
            if (requests == 4 * n) {
                return new Verdict(Verdict.VerdictType.WA, "Too many requests");
            }
            requests++;
            int r1 = solOut.readInt() - 1;
            int c1 = solOut.readInt() - 1;
            int r2 = solOut.readInt() - 1;
            int c2 = solOut.readInt() - 1;
            if (r1 > r2 || c1 > c2 || !isValidCell(r1, c1, n, n) || !isValidCell(r2, c2, n, n) || r2 - r1 + c2 - c1 < n - 1) {
                return new Verdict(Verdict.VerdictType.PE, "");
            }
            if (map[r1][c1] == '#') {
                solInp.printLine("NO");
            } else {
                can[r1][c1] = requests;
                for (int i = r1; i <= r2; i++) {
                    for (int j = c1; j <= c2; j++) {
                        if (map[i][j] == '#') {
                            continue;
                        }
                        if (i > 0 && can[i - 1][j] == requests) {
                            can[i][j] = requests;
                        }
                        if (j > 0 && can[i][j - 1] == requests) {
                            can[i][j] = requests;
                        }
                    }
                }
                solInp.printLine(can[r2][c2] == requests ? "YES" : "NO");
            }
            solInp.flush();
        }
    }
}
