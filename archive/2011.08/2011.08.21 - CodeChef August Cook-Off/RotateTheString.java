import net.egork.string.StringUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class RotateTheString implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		String s = in.readString();
		int mouseMove = in.readInt();
		int poMove = in.readInt();
		int cycle = s.length();
		int[] z = StringUtils.zAlgorithm(s);
		for (int i = 1; i < s.length(); i++) {
			if (s.length() % i == 0 && z[i] == s.length() - i) {
				cycle = i;
				break;
			}
		}
		int current = 0;
		int result = 0;
		mouseMove %= cycle;
		poMove %= cycle;
		while (true) {
			result++;
			current += mouseMove;
			if (current >= cycle)
				current -= cycle;
			if (current == 0) {
				out.println(result);
				return;
			}
			result++;
			current += poMove;
			if (current >= cycle)
				current -= cycle;
			if (current == 0) {
				out.println(result);
				return;
			}
		}
	}

	private boolean isCycle(String s, int shift) {
		for (int i = shift; i < s.length(); i++) {
			if (s.charAt(i) != s.charAt(i - shift))
				return false;
		}
		return true;
	}
}

