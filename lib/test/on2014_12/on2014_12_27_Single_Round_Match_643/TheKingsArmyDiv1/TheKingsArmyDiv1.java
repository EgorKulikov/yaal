package on2014_12.on2014_12_27_Single_Round_Match_643.TheKingsArmyDiv1;



import net.egork.collections.intcollection.IntList;
import net.egork.misc.ArrayUtils;

public class TheKingsArmyDiv1 {
    public int getNumber(String[] state) {
		int[] type = new int[state[0].length()];
		for (int i = 0; i < type.length; i++) {
			if (state[0].charAt(i) == 'H') {
				type[i]++;
			}
			if (state[1].charAt(i) == 'H') {
				type[i] += 2;
			}
		}
		if (ArrayUtils.count(type, 0) == type.length) {
			return -1;
		}
		if (ArrayUtils.count(type, 1) + ArrayUtils.count(type, 2) == 0) {
			return ArrayUtils.count(type, 0);
		}
		int last = -1;
		int qty = 0;
		for (int i : type) {
			if (i != last && (i == 1 || i == 2)) {
				last = i;
				qty++;
			}
		}
		return qty / 2 + 1 + ArrayUtils.count(type, 0);
    }
}
