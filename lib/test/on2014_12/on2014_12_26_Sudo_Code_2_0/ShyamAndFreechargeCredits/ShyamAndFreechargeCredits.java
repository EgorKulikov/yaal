package on2014_12.on2014_12_26_Sudo_Code_2_0.ShyamAndFreechargeCredits;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class ShyamAndFreechargeCredits {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		NavigableSet<Integer> set = new TreeSet<>();
		for (int i = 0; i < count; i++) {
			set.add(i);
		}
		int[] order = ArrayUtils.order(numbers);
		long answer = 0;
		int delta = 0;
		for (int i : order) {
			answer += (long)(numbers[i] - delta) * Math.max(set.size() - 2, 0);
			delta = numbers[i];
			Integer left = set.lower(i);
			Integer right = set.higher(i);
			if (left != null && right != null) {
				answer += Math.min(numbers[left], numbers[right]) - delta;
			}
			set.remove(i);
		}
		out.printLine(answer);
	}
}
