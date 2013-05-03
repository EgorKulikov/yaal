package on2013_05.on2013_05_03_Single_Round_Match_578.GooseInZooDivOne;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.collections.map.Counter;

public class GooseInZooDivOne {
    public int count(String[] field, int dist) {
		int rowCount = field.length;
		int columnCount = field[0].length();
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(rowCount * columnCount);
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (field[i].charAt(j) != 'v')
					continue;
				for (int k = 0; k < rowCount; k++) {
					for (int l = 0; l < columnCount; l++) {
						if (field[k].charAt(l) != 'v')
							continue;
						int dx = Math.abs(i - k);
						int dy = Math.abs(j - l);
						if (dx + dy <= dist)
							setSystem.join(i * columnCount + j, k * columnCount + l);
					}
				}
			}
		}
		Counter<Integer> total = new Counter<Integer>();
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (field[i].charAt(j) == 'v')
					total.add(setSystem.get(i * columnCount + j));
			}
		}
		int odd = 0;
		int even = 1;
		int mod = (int) (1e9 + 7);
		for (long i : total.values()) {
			if (i % 2 == 0) {
				odd *= 2;
				if (odd >= mod)
					odd -= mod;
				even *= 2;
				if (even >= mod)
					even -= mod;
			} else {
				odd = even + odd;
				if (odd >= mod)
					odd -= mod;
				even = odd;
			}
		}
		even--;
		if (even < 0)
			even += mod;
		return even;
	}
}
