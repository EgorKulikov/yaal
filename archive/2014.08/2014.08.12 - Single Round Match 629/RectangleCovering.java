package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;

public class RectangleCovering {
    public int minimumNumber(int holeH, int holeW, int[] boardH, int[] boardW) {
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < 2; i++) {
			IntList lengths = new IntArrayList();
			for (int j = 0; j < boardH.length; j++) {
				if (Math.min(boardH[j], boardW[j]) > holeW) {
					lengths.add(Math.max(boardH[j], boardW[j]));
				} else if (Math.max(boardH[j], boardW[j]) > holeW) {
					lengths.add(Math.min(boardH[j], boardW[j]));
				}
			}
			lengths.inPlaceSort(IntComparator.REVERSE);
			int remaining = holeH;
			for (int j = 0; j < lengths.size(); j++) {
				remaining -= lengths.get(j);
				if (remaining <= 0) {
					answer = Math.min(answer, j + 1);
					break;
				}
			}
			int temp = holeH;
			holeH = holeW;
			holeW = temp;
		}
		if (answer == Integer.MAX_VALUE) {
			answer = -1;
		}
		return answer;
    }
}
