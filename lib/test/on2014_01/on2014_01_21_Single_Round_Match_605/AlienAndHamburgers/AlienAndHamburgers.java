package on2014_01.on2014_01_21_Single_Round_Match_605.AlienAndHamburgers;



import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.misc.MiscUtils;

public class AlienAndHamburgers {
    public int getNumber(int[] type, int[] taste) {
		MiscUtils.decreaseByOne(type);
		boolean[] qty = new boolean[100];
		int[] total = new int[100];
		for (int i = 0; i < type.length; i++) {
			if (!qty[type[i]]) {
				total[type[i]] = taste[i];
				qty[type[i]] = true;
			} else if (total[type[i]] >= 0)
				total[type[i]] += Math.max(0, taste[i]);
			else
				total[type[i]] = Math.max(total[type[i]], taste[i]);
		}
		IntList tastes = new IntArrayList();
		for (int i = 0; i < 100; i++) {
			if (qty[i])
				tastes.add(total[i]);
		}
		tastes.inPlaceSort(IntComparator.REVERSE);
		int answer = 0;
		int sum = 0;
		for (int i = 0; i < tastes.size(); i++) {
			sum += tastes.get(i);
			answer = Math.max(answer, sum * (i + 1));
		}
		return answer;
    }
}
