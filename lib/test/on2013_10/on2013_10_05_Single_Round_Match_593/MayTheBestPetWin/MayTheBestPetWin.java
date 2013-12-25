package on2013_10.on2013_10_05_Single_Round_Match_593.MayTheBestPetWin;



import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class MayTheBestPetWin {
    public int calc(int[] A, int[] B) {
		int count = A.length;
		int sumB = (int) ArrayUtils.sumArray(B);
		int[] answer = new int[sumB + 1];
		int[] next = new int[sumB + 1];
		Arrays.fill(answer, Integer.MAX_VALUE);
		answer[0] = 0;
		for (int i = 0; i < count; i++) {
			Arrays.fill(next, Integer.MAX_VALUE);
			for (int j = 0; j <= sumB; j++) {
				if (answer[j] == Integer.MAX_VALUE)
					continue;
				next[j + B[i]] = Math.min(next[j + B[i]], answer[j] - A[i]);
				int first = j - A[i];
				int second = answer[j] + B[i];
				next[Math.max(first, second)] = Math.min(next[Math.max(first, second)], Math.min(first, second));
			}
			int[] temp = answer;
			answer = next;
			next = temp;
		}
		for (int i = 0; i <= sumB; i++) {
			if (answer[i] != Integer.MAX_VALUE)
				return i;
		}
		throw new RuntimeException();
    }
}
