import net.egork.io.IOUtils;
import net.egork.string.StringUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int cipherLength = in.readInt();
		int[] firstPart = IOUtils.readIntArray(in, cipherLength);
		int[] secondPart = IOUtils.readIntArray(in, cipherLength);
		int key = in.readInt();
		char[] pattern = new char[4 * cipherLength + 1];
		for (int i = 0; i < cipherLength; i++)
			pattern[i] = firstPart[i] == key ? 'a' : 'b';
		pattern[cipherLength] = 'c';
		for (int i = 0; i < cipherLength; i++)
			pattern[cipherLength + 1 + i] = pattern[cipherLength * 2 + 1 + i] = pattern[cipherLength * 3 + 1 + i] = secondPart[i] == key ? 'a' : 'b';
		int[] zArray = StringUtils.zAlgorithm(new String(pattern));
		for (int i = 3 * cipherLength + 1; i > cipherLength + 1; i -= 2) {
			if (zArray[i] == cipherLength) {
				out.println((3 * cipherLength + 1 - i) / 2);
				return;
			}
		}
		out.println(-1);
	}
}

