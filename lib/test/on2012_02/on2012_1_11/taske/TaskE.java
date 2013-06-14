package on2012_02.on2012_1_11.taske;



import net.egork.io.IOUtils;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		int count = in.readInt();
		String[] good = IOUtils.readStringArray(in, count);
		String sReverse = StringUtils.reverse(s);
		int answer = 0;
		for (String t : good) {
			if (t.length() == 1)
				continue;
			String direct = t + "$" + s;
			String reverse = StringUtils.reverse(t) + "$" + sReverse;
			int[] zDirect = StringUtils.zAlgorithm(direct);
			int[] zReverse = StringUtils.zAlgorithm(reverse);
			boolean found = false;
			int maxPrefix = 0;
			int last = s.length() - t.length();
			for (int i = 0; i <= last; i++) {
				maxPrefix = Math.max(maxPrefix, zDirect[i + t.length() + 1]);
				if (maxPrefix + zReverse[s.length() - i + 1] >= t.length())
					found = true;
			}
			if (found)
				answer++;
		}
		out.printLine(answer);
	}
}
