import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Free implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int n = in.readInt();
		List<GoodString> list = new ArrayList<GoodString>();
		list.add(new GoodString("", new boolean[0]));
		for (int i = 1; i <= n; i++) {
			List<GoodString> newList = new ArrayList<GoodString>();
			for (GoodString string : list) {
				for (char c = '0'; c <= '1'; c++) {
					String s = string.s + c;
					boolean[] good = new boolean[s.length()];
					for (int j = 0; j < s.length(); j++) {
						if (j < s.length() - 1 && !string.good[j]) {
							good[j] = false;
						} else {
							int k = s.length();
							if ((k - j) % 2 == 0) {
								String s1 = s.substring(j, (j + k) / 2);
								String s2 = s.substring((j + k) / 2, k);
								if (s1.equals(s2)) {
									good[j] = false;
								} else {
									good[j] = true;
								}
							} else {
								good[j] = true;
							}
						}
					}
					int k = 0;
					boolean ok = true;
					for (int j = 0; j < s.length(); j++) {
						if (good[j]) k++;
						if (2 * k >= (j + 1)) {

						} else {
							ok = false;
							break;
						}
					}
					if (ok) {
						newList.add(new GoodString(s, good));
					}
				}
			}
			list = newList;
			//System.err.println(list.size());
		}
		out.println(list.size());
		for (GoodString string : list) {
			out.println(string.s);
		}

	}

	class GoodString {
		String s;
		boolean[] good;
		GoodString(String s, boolean[] good) {
			this.s = s;
			this.good = good;
		}
	}
}

