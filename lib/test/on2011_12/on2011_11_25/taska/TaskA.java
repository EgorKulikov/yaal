package on2011_12.on2011_11_25.taska;



import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int suffix = in.readInt();
		int answer = 15;
		for (int i = 0; i < count; i++) {
			String[] currentSuffix = new String[4];
			for (int j = 0; j < 4; j++) {
				String word = in.readString();
				int remaining = suffix;
				for (int k = word.length() - 1; k >= 0; k--) {
					if (MiscUtils.isStrictVowel(word.charAt(k)))
						remaining--;
					if (remaining == 0) {
						currentSuffix[j] = word.substring(k);
						break;
					}
				}
				if (currentSuffix[j] == null) {
					out.printLine("NO");
					return;
				}
			}
			if (currentSuffix[0].equals(currentSuffix[1]) && currentSuffix[0].equals(currentSuffix[2]) && currentSuffix[0].equals(currentSuffix[3]))
				continue;
			if (currentSuffix[0].equals(currentSuffix[1]) && currentSuffix[2].equals(currentSuffix[3])) {
				answer &= 3;
				continue;
			}
			if (currentSuffix[0].equals(currentSuffix[2]) && currentSuffix[1].equals(currentSuffix[3])) {
				answer &= 5;
				continue;
			}
			if (currentSuffix[0].equals(currentSuffix[3]) && currentSuffix[1].equals(currentSuffix[2])) {
				answer &= 9;
				continue;
			}
			out.printLine("NO");
			return;
		}
		if (Integer.bitCount(answer) % 2 != 0 || answer == 0) {
			out.printLine("NO");
			return;
		}
		for (int i = 0; i < 4; i++) {
			if ((answer >> i & 1) == 1)
				out.print('a');
			else
				out.print('b');
		}
		out.printLine();
	}
}
