package net.egork;

import net.egork.numbers.IntegerUtils;

import java.util.Arrays;

public class LCMSet {
    public String equal(int[] A, int[] B) {
		if (check(A, B)) return "Not equal";
		if (check(B, A)) return "Not equal";
		return "Equal";
    }

	private boolean check(int[] A, int[] B) {
		for (int i = 0; i < A.length; i++) {
			int lcm = 1;
			for (int j = 0; j < B.length; j++) {
				if (A[i] % B[j] == 0) {
					lcm = (int) IntegerUtils.lcm(lcm, B[j]);
				}
			}
			if (lcm != A[i])
				return true;
		}
		return false;
	}
}
