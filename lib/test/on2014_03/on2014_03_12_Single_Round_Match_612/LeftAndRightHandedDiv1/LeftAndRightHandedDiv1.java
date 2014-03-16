package on2014_03.on2014_03_12_Single_Round_Match_612.LeftAndRightHandedDiv1;



import net.egork.misc.ArrayUtils;

public class LeftAndRightHandedDiv1 {
    public long countSwaps(String Y, int A, int B, int C, int D, int N) {
		long index = A;
		char[] sequence = new char[N];
		for (int i = 0; i < N; i++) {
			sequence[i] = Y.charAt((int) (index % Y.length()));
			index = (index * B + C) % D;
		}
		int lCount = ArrayUtils.count(sequence, 'L');
		if (lCount == 0 || lCount == N)
			return 0;
		int[] qty = new int[N + 1];
		for (int i = 0; i < N; i++)
			qty[i + 1] = qty[i] + (sequence[i] == 'R' ? 1 : 0);
		long base = 0;
		int skipped = 0;
		for (int i = 0; i < N; i++) {
			if (sequence[i] == 'L')
				skipped++;
			else
				base += Math.min(skipped, lCount - skipped);
		}
		if (lCount == 1)
			return 0;
		if (lCount % 2 == 0) {
			int middle = -1;
			skipped = 0;
			for (int i = 0; i < N; i++) {
				if (sequence[i] == 'L')
					skipped++;
				if (lCount == 2 * skipped) {
					middle = i;
					break;
				}
			}
			long answer = base;
			for (int i = 0; i < N; i++) {
				if (sequence[i] == 'R')
					continue;
				middle++;
				if (middle == N)
					middle = 0;
				while (sequence[middle] == 'R') {
					middle++;
					if (middle == N)
						middle = 0;
				}
				base -= qty(i, middle, qty);
				base += qty(middle, i, qty);
				answer = Math.min(answer, base);
			}
			return answer;
		} else {
			int middleLeft = -1;
			int middleRight = -1;
			skipped = 0;
			for (int i = 0; i < N; i++) {
				if (sequence[i] == 'L')
					skipped++;
				if (lCount == 2 * skipped + 1) {
					middleLeft = i;
				}
				if (lCount == 2 * skipped - 1) {
					middleRight = i;
					break;
				}
			}
			long answer = base;
			for (int i = 0; i < N; i++) {
				if (sequence[i] == 'R')
					continue;
				middleLeft = middleRight;
				middleRight++;
				if (middleRight == N)
					middleRight = 0;
				while (sequence[middleRight] == 'R') {
					middleRight++;
					if (middleRight == N)
						middleRight = 0;
				}
				base -= qty(i, middleLeft, qty);
				base += qty(middleRight, i, qty);
				answer = Math.min(answer, base);
			}
			return answer;
		}
    }

	private long qty(int from, int to, int[] qty) {
		if (to >= from)
			return qty[to] - qty[from];
		return qty[to] + qty[qty.length - 1] - qty[from];
	}
}
