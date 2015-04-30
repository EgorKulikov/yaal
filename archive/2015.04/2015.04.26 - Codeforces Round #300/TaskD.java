package net.egork;

import net.egork.collections.intcollection.IntPair;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int size = in.readInt();
        char[][] board = IOUtils.readTable(in, size, size);
        List<IntPair> pieces = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 'o') {
                    pieces.add(new IntPair(i, j));
                }
            }
        }
        List<IntPair> moves = new ArrayList<>();
        for (int i = -size + 1; i < size; i++) {
            for (int j = -size + 1; j < size; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                boolean valid = true;
                for (IntPair piece : pieces) {
                    int row = i + piece.first;
                    int column = j + piece.second;
                    if (MiscUtils.isValidCell(row, column, size, size) && board[row][column] == '.') {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    moves.add(new IntPair(i, j));
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 'x') {
                    boolean found = false;
                    for (IntPair move : moves) {
                        int row = i - move.first;
                        int column = j - move.second;
                        if (MiscUtils.isValidCell(row, column, size, size) && board[row][column] == 'o') {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        out.printLine("NO");
                        return;
                    }
                }
            }
        }
        char[][] answer = new char[2 * size - 1][2 * size - 1];
        ArrayUtils.fill(answer, '.');
        answer[size - 1][size - 1] = 'o';
        for (IntPair move : moves) {
            answer[size + move.first - 1][size + move.second - 1] = 'x';
        }
        out.printLine("YES");
        for (char[] row : answer) {
            out.printLine(row);
        }
    }
}
