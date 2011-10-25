import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		Map<String, Integer> types = new HashMap<String, Integer>();
		types.put("void", 0);
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			String operator = in.readString();
			if ("typedef".equals(operator)) {
				Integer normalized = nomalize(in.readString(), types);
				types.put(in.readString(), normalized);
			} else {
				Integer normalized = nomalize(in.readString(), types);
				if (normalized == null)
					out.println("errtype");
				else {
					out.print("void");
					for (int j = 0; j < normalized; j++)
						out.print("*");
					out.println();
				}
			}
		}
	}

	private Integer nomalize(String s, Map<String, Integer> types) {
		int index1 = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != '&') {
				index1 = i;
				break;
			}
		}
		int index2 = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) != '*') {
				index2 = i + 1;
				break;
			}
		}
		Integer result = types.get(s.substring(index1, index2));
		if (result == null)
			return null;
		result += s.length() - index2 - index1;
		if (result < 0)
			return null;
		return result;
	}
}

