import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Comparator;

public class Binary implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		final int size = in.readInt();
		int[] array = new int[size];
		for (int i = 0; i < size; i++)
			array[i] = in.readInt() - 1;
		final int[] result = new int[size];
		int divider = size - 1;
		for (int i = 0; i < size; i++) {
			if (divider == array[i])
				divider--;
			else
				break;
		}
		for (int i = 0; i < size; i++) {
			if (array[i] == divider) {
				for (int j = i; j < size; j++)
					result[array[j]] = 1;
			}
		}
		final long[] hash = new long[size + 1];
		final long multiplier = 43;
		long reverse = BigInteger.valueOf(43).modInverse(BigInteger.valueOf(2).pow(64)).longValue();
		final long[] reverseArray = new long[size + 1];
		final long[] power = new long[size + 1];
		power[0] = 1;
		for (int i = 0; i < size; i++)
			power[i + 1] = power[i] * multiplier;
		reverseArray[0] = 1;
		for (int i = 0; i < size; i++)
			reverseArray[i + 1] = reverse * reverseArray[i];
		for (int i = 0; i < size; i++)
			hash[i + 1] = hash[i] * multiplier + result[i];
		Integer[] order = new Integer[size];
		for (int i = 0; i < size; i++)
			order[i] = i;
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (o1.equals(o2))
					return 0;
				int left = 0;
				int right = Math.min(size - o1, size - o2);
				while (right > left) {
					int middle = (left + right + 1) / 2;
					if (getHash(o1, o1 + middle, hash, reverseArray, power) == getHash(o2, o2 + middle, hash, reverseArray, power))
						left = middle;
					else
						right = middle - 1;
				}
				if (o1 + left == size)
					return -1;
				if (o2 + left == size)
					return 1;
				return result[o1 + left] - result[o2 + left];
			}
		};
		for (int i = 1; i < size; i++) {
			if (comparator.compare(array[i - 1], array[i]) > 0) {
				out.println("Error");
				return;
			}
		}
		for (int i : result)
			out.print(i);
		out.println();
	}

	private long getHash(int from, int to, long[] hash, long[] reverse, long[] power) {
		return (hash[to] - hash[from] * power[to - from]);// * reverse[from];
	}
}

