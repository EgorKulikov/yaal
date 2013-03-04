import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class BinPal implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int index = in.readInt();
		int current = 2;
		int length = 1;
		while (index > current) {
			index -= current;
			current *= 2;
			length += 2;
		}
		if (index > current / 2) {
			index -= current / 2;
			length++;
		}
		index--;
		String result = Integer.toString(index, 2);
		while (result.length() < (length - 1) / 2)
			result = "0" + result;
		result = result.substring(result.length() - (length - 1) / 2);
		result = "1" + result;
		for (int i = result.length(); i < length; i++)
			result += result.charAt(length - i - 1);
		out.println(Long.parseLong(result, 2));
	}
}

