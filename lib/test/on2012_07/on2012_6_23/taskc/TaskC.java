package on2012_07.on2012_6_23.taskc;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		long[][] answer = new long[2][2];
		long[][] next = new long[2][2];
		answer[0][0] = answer[1][1] = 1;
		for (int i = 1; i <= s.length; i++) {
			int currentLetter = s[i - 1] - 'A';
			int nextLetter = s[i % s.length] - 'A';
			ArrayUtils.fill(next, 0);
			if (currentLetter == 0) {
				if (nextLetter == 0) {
					for (int j = 0; j < 2; j++) {
						next[j][0] = answer[j][1] + answer[j][0];
						next[j][1] = 0;
					}
				} else if (nextLetter == 1) {
					for (int j = 0; j < 2; j++) {
						next[j][0] = answer[j][1] + answer[j][0];
						next[j][1] = answer[j][1];
					}
				}
			} else {
				if (nextLetter == 0) {
					for (int j = 0; j < 2; j++) {
						next[j][0] = answer[j][1];
						next[j][1] = answer[j][0];
					}
				} else if (nextLetter == 1) {
					for (int j = 0; j < 2; j++) {
						next[j][0] = answer[j][1];
						next[j][1] = answer[j][1];
					}
				}
			}
			long[][] temp = next;
			next = answer;
			answer = temp;
		}
		long result = answer[0][0] + answer[1][1];
		out.printLine(result);
	}
}
