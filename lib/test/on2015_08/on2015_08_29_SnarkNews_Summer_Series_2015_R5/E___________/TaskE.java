package on2015_08.on2015_08_29_SnarkNews_Summer_Series_2015_R5.E___________;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = IOUtils.readIntArray(in, n);
		int[] last = ArrayUtils.createArray(n, -1);
		int answer = 0;
		for (int i = 0; i < n; i++) {
			answer++;
			for (int j = i - 1; j >= 0; j--) {
				if (same(a[i], a[j])) {
					last[i] = j;
					answer--;
					break;
				}
			}
		}
//		int answer = 0;
//		for (int i = 0; i < n; i++) {
//			for (int j = i; j < n; j++) {
//				if (last[j] >= i) {
//					break;
//				}
//				answer = Math.max(answer, j - i + 1);
//			}
//		}
		out.printLine(answer);
	}

	private boolean same(int a, int b) {
//		if (a < 0 || b < 0) {
//			return a == b;
//		}
		if (a < 0) {
			if (b < 0) {
				a = -a;
				b = -b;
			} else {
				return false;
			}
		} else if (b < 0) {
			return false;
		}
		int ten = 1;
		while (ten * 10 <= a) {
			ten *= 10;
		}
		if (b < ten || b >= ten * 10) {
			return false;
		}
		int current = a;
		do {
			if (current == b) {
				return true;
			}
			current = (current % ten) * 10 + current / ten;
		} while (current != a);
		return false;
	}
}
