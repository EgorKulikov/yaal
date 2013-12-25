package on2012_12.on2012_12_06_Codeforces_Round__153.B___Playing_with_Permutations;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int moveCount = in.readInt();
		int[] move = IOUtils.readIntArray(in, size);
		int[] target = IOUtils.readIntArray(in, size);
		MiscUtils.decreaseByOne(move, target);
		int[] reverse = ArrayUtils.reversePermutation(move);
		int[] start = ArrayUtils.createOrder(size);
		if (Arrays.equals(start, target)) {
			out.printLine("NO");
			return;
		}
		if (Arrays.equals(move, reverse)) {
			if (moveCount == 1 && Arrays.equals(move, target)) {
				out.printLine("YES");
			} else {
				out.printLine("NO");
			}
			return;
		}
		int up = moveCount + 1;
		int down = moveCount + 1;
		for (int i = 0; i < moveCount; i++) {
			start = ArrayUtils.multiplyPermutations(start, reverse);
			if (Arrays.equals(start, target)) {
				down = i + 1;
				break;
			}
		}
		start = ArrayUtils.createOrder(size);
		for (int i = 0; i < moveCount; i++) {
			start = ArrayUtils.multiplyPermutations(start, move);
			if (Arrays.equals(start, target)) {
				up = i + 1;
				break;
			}
		}
		if (up % 2 == moveCount % 2 || down % 2 == moveCount % 2)
			out.printLine("YES");
		else
			out.printLine("NO");
	}
}
