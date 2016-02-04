package net.egork.numbers;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class BooleanMatrix {
    public final boolean[][] data;
    public final int rowCount;
    public final int columnCount;

    public BooleanMatrix(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.data = new boolean[rowCount][columnCount];
    }

    public static BooleanMatrix add(BooleanMatrix first, BooleanMatrix second) {
        BooleanMatrix result = new BooleanMatrix(first.rowCount, first.columnCount);
        for (int i = 0; i < result.rowCount; i++) {
            for (int j = 0; j < result.columnCount; j++) {
                result.data[i][j] = first.data[i][j] || second.data[i][j];
            }
        }
        return result;
    }

    public static BooleanMatrix multiply(BooleanMatrix first, BooleanMatrix second) {
        BooleanMatrix result = new BooleanMatrix(first.rowCount, second.columnCount);
        for (int i = 0; i < first.rowCount; i++) {
            for (int j = 0; j < second.rowCount; j++) {
                for (int k = 0; k < second.columnCount; k++) {
                    result.data[i][k] |= first.data[i][j] && second.data[j][k];
                }
            }
        }
        return result;
    }

    public static BooleanMatrix identityMatrix(int size) {
        BooleanMatrix result = new BooleanMatrix(size, size);
        for (int i = 0; i < size; i++) {
            result.data[i][i] = true;
        }
        return result;
    }

    public BooleanMatrix power(int exponent) {
        if (exponent == 0) {
            return identityMatrix(rowCount);
        }
        if (exponent == 1) {
            return this;
        }
        BooleanMatrix result = power(exponent >> 1);
        result = multiply(result, result);
        if ((exponent & 1) == 1) {
            result = multiply(result, this);
        }
        return result;
    }
}
