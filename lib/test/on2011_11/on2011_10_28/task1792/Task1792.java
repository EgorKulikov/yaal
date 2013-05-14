package on2011_11.on2011_10_28.task1792;



import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1792 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] code = IOUtils.readIntArray(in, 7);
		if (!correct(code)) {
			for (int i = 0; i < 7; i++) {
				code[i] = 1 - code[i];
				if (correct(code))
					break;
				code[i] = 1 - code[i];
			}
		}
		out.printLine(Array.wrap(code).toArray());
	}

	private boolean correct(int[] code) {
		int sum = code[0] + code[1] + code[2] + code[3];
		for (int i = 0; i < 3; i++) {
			if ((sum - code[i]) % 2 != code[i + 4])
				return false;
		}
		return true;
	}
}
