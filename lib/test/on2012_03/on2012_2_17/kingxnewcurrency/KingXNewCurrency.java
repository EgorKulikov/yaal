package on2012_03.on2012_2_17.kingxnewcurrency;



import net.egork.collections.sequence.Array;
import net.egork.numbers.IntegerUtils;

public class KingXNewCurrency {
	public int howMany(int A, int B, int X) {
		if (A % X == 0 && B % X == 0)
			return -1;
		boolean[] can = new boolean[A * B];
		for (int i = 0; i < B; i++) {
			for (int j = 0; j < A; j++) {
				int value = i * A + j * B;
				if (value < can.length)
					can[value] = true;
			}
		}
		int maxY = X > Math.min(A, B) ? Math.min(A, B) : Math.max(A, B);
		int answer = 0;
		for (int i = 1; i <= maxY; i++) {
			if (i == X || IntegerUtils.gcd(A, B) % IntegerUtils.gcd(i, X) != 0)
				continue;
			boolean[] cannot = can.clone();
			int max = Math.min(can.length, X * i);
			for (int j = 0; j < max; j += X) {
				for (int k = 0; j + k < max; k += i)
					cannot[j + k] = false;
			}
			if (Array.wrap(cannot).subList(0, max).indexOf(true) == -1)
				answer++;
		}
		return answer;
	}


}

