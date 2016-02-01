package on2015_12.on2015_12_14_USACO_2015_December_Contest__Gold.Problem_1__High_Card_Low_Card__Gold_;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cardgame {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] cards = IOUtils.readIntArray(in, n);
		MiscUtils.decreaseByOne(cards);
		int[] big = Arrays.copyOf(cards, n / 2);
		int[] small = Arrays.copyOfRange(cards, n / 2, n);
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < 2 * n; i++) {
			set.add(i);
		}
		for (int i : cards) {
			set.remove(i);
		}
		List<Integer> bessie = new ArrayList<>(set);
		Collections.sort(bessie);
		Arrays.sort(small);
		int at = 0;
		int answer = 0;
		for (int i : small) {
			if (bessie.get(at) < i) {
				answer++;
				at++;
			}
		}
		at = n - 1;
		Arrays.sort(big);
		for (int i = 0, j = big.length - 1; i < j; i++, j--) {
			int temp = big[i];
			big[i] = big[j];
			big[j] = temp;
		}
		for (int i : big) {
			if (bessie.get(at) > i) {
				answer++;
				at--;
			}
		}
		out.printLine(answer);
	}
}
