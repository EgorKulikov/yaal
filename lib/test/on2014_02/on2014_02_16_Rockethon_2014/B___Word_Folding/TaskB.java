package on2014_02.on2014_02_16_Rockethon_2014.B___Word_Folding;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int[] answer = new int[s.length];
		for (int i = s.length - 1; i >= 0; i--) {
			answer[i] = 1;
			for (int j = i + 1; j < s.length; j += 2) {
				if (s[j] == s[i])
					answer[i] = Math.max(answer[i], answer[j] + 1);
			}
		}
		out.printLine(ArrayUtils.maxElement(answer));
    }
}
