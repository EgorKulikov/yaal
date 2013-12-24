package on2013_12.on2013_12_10_December_Challenge_2013.Chef_and_Codes;



import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndCodes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] frequency = IOUtils.readCharArray(in, 26);
		char[] text = in.readLine().toCharArray();
		final int[] qty = new int[26];
		for (char c : text) {
			if (Character.isLetter(c))
				qty[Character.toLowerCase(c) - 'a']++;
		}
		int[] order = ArrayUtils.createOrder(26);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				if (qty[first] != qty[second])
					return qty[first] - qty[second];
				return first - second;
			}
		});
		int[] reverse = ArrayUtils.reversePermutation(order);
		for (int i = 0; i < text.length; i++) {
			if (Character.isLetter(text[i])) {
				char result = frequency[reverse[Character.toLowerCase(text[i]) - 'a']];
				if (Character.isUpperCase(text[i]))
					result = Character.toUpperCase(result);
				text[i] = result;
			}
		}
		out.printLine(text);
    }
}
