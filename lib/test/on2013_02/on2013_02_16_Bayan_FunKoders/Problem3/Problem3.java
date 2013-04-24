package on2013_02.on2013_02_16_Bayan_FunKoders.Problem3;



import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Collections;
import java.util.List;

public class Problem3 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] letters = in.readString().toCharArray();
		List<Character> array = Array.wrap(letters);
		Collections.sort(array);
		do {
			for (char c : array)
				out.print(c);
			out.printLine();
		} while (ListUtils.nextPermutation(array));
    }
}
