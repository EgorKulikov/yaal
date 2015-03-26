package net.egork;

public class CountryGroupHard {
    public String solve(int[] a) {
		int size = a.length;
		boolean[] can = new boolean[size + 1];
		boolean[] multiple = new boolean[size + 1];
		can[size] = true;
		for (int i = size - 1; i >= 0; i--) {
			for (int j = i + 1; j <= size; j++) {
				if (!can[j]) {
					continue;
				}
				boolean ok = true;
				for (int k = i; k < j; k++) {
					if (a[k] != 0 && a[k] != j - i) {
						ok = false;
						break;
					}
				}
				if (!ok) {
					continue;
				}
				if (can[i] || multiple[j]) {
					multiple[i] = true;
				}
				can[i] = true;
			}
		}
		if (multiple[0]) {
			return "Insufficient";
		}
		return "Sufficient";
    }
}
