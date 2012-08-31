package on2012_01.on2012_0_15.kingsort;



import net.egork.misc.MiscUtils;

import java.util.Arrays;
import java.util.Comparator;

public class KingSort {
	public String[] getSortedList(String[] kings) {
		Arrays.sort(kings, new Comparator<String>() {
			public int compare(String o1, String o2) {
				String[] first = o1.split(" ");
				String[] second = o2.split(" ");
				if (first[0].compareTo(second[0]) != 0)
					return first[0].compareTo(second[0]);
				return MiscUtils.convertFromRoman(first[1]) - MiscUtils.convertFromRoman(second[1]);
			}
		});
		return kings;
	}


}

