package net.egork;

public class SetAndSet {
	int[][][] arrays;

	public long countandset(int[] A) {
		int desired = -1;
		for (int i : A)
			desired &= i;
		for (int i = 0; i < A.length; i++)
			A[i] -= desired;
		arrays = new int[21][A.length + 1][];
		for (int i = 0; i <= 20; i++) {
			for (int j = 0; j <= A.length; j++)
				arrays[i][j] = new int[j];
		}
		return go(A, 1 << 19, 19);
	}

	private long go(int[] a, int mask, int step) {
		if (mask == 0)
			return (1L << a.length) - 2;
		int countZero = 0;
		for (int i : a) {
			if ((i & mask) == 0)
				countZero++;
		}
		if (countZero < 2)
			return 0;
		int index = 0;
		int arrayIndex = a.length - countZero + 1;
		int last = -1;
		for (int i : a) {
			if ((i & mask) == 0)
				last &= i;
			else
				arrays[step][arrayIndex][index++] = i - mask;
		}
		arrays[step][arrayIndex][index] = last;
		for (int i = 0; i < a.length; i++)
			arrays[step][a.length][i] = a[i] & (mask - 1);
		return go(arrays[step][a.length], mask >> 1, step - 1) - go(arrays[step][arrayIndex], mask >> 1, step - 1);
	}
}

