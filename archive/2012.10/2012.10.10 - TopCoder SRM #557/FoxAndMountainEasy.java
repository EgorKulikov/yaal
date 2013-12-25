package net.egork;

public class FoxAndMountainEasy {
	public String possible(int n, int h0, int hn, String history) {
		int min = 0;
		int cur = 0;
		for (int i = 0; i < history.length(); i++) {
			if (history.charAt(i) == 'U')
				cur++;
			else
				cur--;
			min = Math.min(min, cur);
		}
		if (h0 < -min) {
			int diff = -min - h0;
			n -= diff;
			h0 += diff;
		}
		n -= history.length();
		h0 += cur;
		if (Math.abs(h0 - hn) > n || Math.abs(h0 - hn) % 2 != n % 2)
			return "NO";
		return "YES";
	}
}
