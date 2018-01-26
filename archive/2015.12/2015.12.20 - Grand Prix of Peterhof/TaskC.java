package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.TreeMap;

public class TaskC {
	String[] obvious = new String[]{"0101010101", "00", "111"};

	String removeObvious(String s) {
		main:
		for (;;) {
			for (String t : obvious) {
				int n = s.length();
				for (int i = 0; i < n; i++) {
					if (i + t.length() <= n && s.substring(i, i + t.length()).equals(t)) {
						s = s.substring(0, i) + s.substring(i + t.length());
						continue main;
					}
				}
			}
			return s;
		}
	}

	String canonizeSmall(String s) {
		s = removeObvious(s);
		String res = s;
		res = search(s, 3, res);
		return res;
	}

	String[] from = new String[]{"10101", "011011", "110110", "010110101101", "101101011010"};
	String[] to = new String[]{"0110110", "101010", "010101", from[4], from[3]};



	String search(String s, int depth, String best) {
		if (s.length() < best.length() || s.length() == best.length() && s.compareTo(best) < 0) {
			best = s;
		}
		if (depth == 0) {
			return best;
		}
		for (int j = 0; j < from.length; j++) {
			for (int i = 0; i + from[j].length() <= s.length(); i++) {
				if (s.substring(i, i + from[j].length()).equals(from[j])) {
					String t = s.substring(0, i) + to[j] + s.substring(i + from[j].length());
//					if (depth >= 2) {
//						best = search(t, depth - 2, best);
//					}
					t = removeObvious(t);
					best = search(t, depth - 1, best);
				}
			}
		}
		return best;
	}

	String canonize(String s) {
		int n = s.length();
		int m = 26;
/*		int x = Math.min(m, s.length());
		StringBuilder res = new StringBuilder();
		String t = s.substring(0, x);
		for (;;) {
			t = canonizeSmall(t);
			if (t.length() > 0) {
				res.append(t.charAt(0));
				t = t.substring(1);
			}
			int y = Math.min(s.length(), x + m - t.length());
			t += s.substring(x, y);
			x = y;
			if (t.length() == 0) {
				break;
			}
		}*/
		for (;;) {
			boolean changed = false;
			for (int i = 0; i < s.length(); i++) {
				int j = Math.min(i + m, s.length());
				String t = s.substring(i, j);
				String u = canonizeSmall(t);
				if (u.equals(t)) {
					continue;
				}
				changed = true;
				s = s.substring(0, i) + u + s.substring(j);
			}
			if (!changed) {
				break;
			}
		}
		return s.toString();
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		/*Random r = new Random(566);
		for (int z = 0;; z++) {
			if (z % 1024 == 0){
				System.out.println(z);
			}
			String s = "";
			int n = 30;
			for (int i = 0; i < n; i++) {
				s += r.nextInt(2);
			}
			String t = s;
			int i = r.nextInt(s.length() + 1);
			t = s.substring(0, i) + obvious[r.nextInt(3)] + s.substring(i);
			String ss = canonize(s);
			String tt = canonize(t);
			if (!ss.equals(tt)) {
				System.out.println("STRESS:");
				System.out.println(s);
				System.out.println(canonize(s));
				System.out.println(t);
				System.out.println(canonize(t));
				break;
			}
		}*/

		int n = in.readInt();
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		TreeMap<String, Integer> classNum = new TreeMap<>();
		for (int i = 0; i < n; i++) {
			String s = in.readString();
			s = canonize(s);
			if (!classNum.containsKey(s)) {
				classNum.put(s, ans.size());
				ans.add(new ArrayList<Integer>());
			}
			ans.get(classNum.get(s)).add(i);
		}
		out.printLine(ans.size());
		for (ArrayList<Integer> equiv : ans) {
			out.print(equiv.size());
			for (int i : equiv) {
				out.print(" " + (i + 1));
			}
			out.printLine();
		}
	}
}
