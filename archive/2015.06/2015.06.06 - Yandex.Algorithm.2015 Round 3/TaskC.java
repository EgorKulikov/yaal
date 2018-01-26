package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int rowCount = in.readInt();
        int columnCount = in.readInt();
        boolean[][] field = new boolean[rowCount][columnCount];
        int shipCount = in.readInt();
        int answer = 0;
        for (int i = 0; i < shipCount; i++) {
            int fromRow = in.readInt();
            int fromColumn = in.readInt();
            int toRow = in.readInt();
            int toColumn = in.readInt();
            if (fromRow > toRow) {
                int temp = fromRow;
                fromRow = toRow;
                toRow = temp;
            }
            if (fromColumn > toColumn) {
                int temp = fromColumn;
                fromColumn = toColumn;
                toColumn = temp;
            }
            answer = Math.max(answer, (toRow - fromRow) * (toColumn - fromColumn));
            fromRow = Math.max(fromRow - 1, 0);
            fromColumn = Math.max(fromColumn - 1, 0);
            toRow = Math.min(toRow + 1, rowCount);
            toColumn = Math.min(toColumn + 1, columnCount);
            for (int j = fromRow; j < toRow; j++) {
                for (int k = fromColumn; k < toColumn; k++) {
                    field[j][k] = true;
                }
            }
        }
        int[] left = new int[columnCount];
        int[] height = new int[columnCount];
        int[] stack = new int[columnCount + 1];
        for (int i = 0; i < rowCount; i++) {
            stack[0] = -1;
            int size = 1;
            for (int j = 0; j < columnCount; j++) {
                height[j] = field[i][j] ? 0 : height[j] + 1;
                while (size > 1 && height[stack[size - 1]] >= height[j]) {
                    size--;
                }
                left[j] = stack[size - 1];
                stack[size++] = j;
            }
            stack[0] = columnCount;
            size = 1;
            for (int j = columnCount - 1; j >= 0; j--) {
                while (size > 1 && height[stack[size - 1]] >= height[j]) {
                    size--;
                }
                answer = Math.max(answer, height[j] * (stack[size - 1] - left[j] - 1));
                stack[size++] = j;
            }
        }
        out.printLine(answer);
    }
}
