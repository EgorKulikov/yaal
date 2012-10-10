package net.egork;

import net.egork.collections.comparators.ReverseComparator;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;

import java.util.Collections;

public class StrIIRec {
	public String recovstr(int n, int minInv, String minStr) {
		boolean[] present = new boolean[n];
		char[] answer = minStr.toCharArray();
		for (char c : answer)
			present[c - 'a'] = true;
		for (int i = 0; i < n; i++) {
			if (!present[i])
				minStr += (char)('a' + i);
		}
		answer = minStr.toCharArray();
		for (int i = n - 1; i >= 0; i--) {
			Collections.sort(Array.wrap(answer).subList(i, n), new ReverseComparator<Character>());
			if (ListUtils.countUnorderedPairs(Array.wrap(answer)) >= minInv) {
				for (int j = i; j < n; j++) {
					for (int k = n; k > i; k--) {
						int kk = Math.min(k, n - 1);
						char temp = answer[kk];
						answer[kk] = answer[j];
						answer[j] = temp;
						Collections.sort(Array.wrap(answer).subList(j + 1, n), new ReverseComparator<Character>());
						if (ListUtils.countUnorderedPairs(Array.wrap(answer)) >= minInv)
							break;
					}
				}
				return new String(answer);
			}
		}
		return "";
	}


}

