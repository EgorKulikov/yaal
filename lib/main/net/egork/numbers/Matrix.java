package net.egork.numbers;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Matrix {
	public static long mod = Long.MAX_VALUE;
	public final long[][] data;
	public final int rowCount;
	public final int columnCount;

	public Matrix(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.data = new long[rowCount][columnCount];
	}

	public static Matrix add(Matrix first, Matrix second) {
		Matrix result = new Matrix(first.rowCount, first.columnCount);
		for (int i = 0; i < result.rowCount; i++) {
			for (int j = 0; j < result.columnCount; j++) {
				result.data[i][j] = first.data[i][j] + second.data[i][j];
				if (result.data[i][j] >= mod)
					result.data[i][j] -= mod;
			}
		}
		return result;
	}

	public static Matrix multiply(Matrix first, Matrix second) {
		Matrix result = new Matrix(first.rowCount, second.columnCount);
		for (int i = 0; i < first.rowCount; i++) {
			for (int j = 0; j < second.rowCount; j++) {
				for (int k = 0; k < second.columnCount; k++)
					result.data[i][k] = (result.data[i][k] + first.data[i][j] * second.data[j][k]) % mod;
			}
		}
		return result;
	}

	public static Matrix fastMultiply(Matrix first, Matrix second) {
		Matrix result = new Matrix(first.rowCount, second.columnCount);
		for (int i = 0; i < first.rowCount; i++) {
			for (int j = 0; j < second.rowCount; j++) {
				for (int k = 0; k < second.columnCount; k++)
					result.data[i][k] += first.data[i][j] * second.data[j][k];
			}
		}
		for (int i = 0; i < first.rowCount; i++) {
			for (int j = 0; j < second.columnCount; j++)
				result.data[i][j] %= mod;
		}
		return result;
	}

	public static Matrix identityMatrix(int size) {
		Matrix result = new Matrix(size, size);
		for (int i = 0; i < size; i++)
			result.data[i][i] = 1;
		return result;
	}

	public Matrix power(long exponent) {
		if (exponent == 0)
			return identityMatrix(rowCount);
		if (exponent == 1)
			return this;
		Matrix result = power(exponent >> 1);
		result = multiply(result, result);
		if ((exponent & 1) == 1)
			result = multiply(result, this);
		return result;
	}

	public Matrix fastPower(long exponent) {
		if (exponent == 0)
			return identityMatrix(rowCount);
		if (exponent == 1)
			return this;
		Matrix result = power(exponent >> 1);
		result = fastMultiply(result, result);
		if ((exponent & 1) == 1)
			result = fastMultiply(result, this);
		return result;
	}
}
