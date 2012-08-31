package on2012_02.on2012_1_22.dengklekmakingchains;



import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class DengklekMakingChains {
	public int maxBeauty(String[] chains) {
		int middle = 0;
		List<Integer> starts = new ArrayList<Integer>();
		List<Integer> finishes = new ArrayList<Integer>();
		int best = 0;
		for (String chain : chains) {
			boolean isGood = true;
			for (char c : chain.toCharArray()) {
				if (c == '.')
					isGood = false;
			}
			int current = 0;
			for (char c : chain.toCharArray()) {
				if (c != '.')
					current += c - '0';
			}
			if (chain.charAt(1) != '.')
				best = Math.max(best, current);
			if (isGood) {
				for (char c : chain.toCharArray())
					middle += c - '0';
			} else {
				int start = 0;
				int finish = 0;
				for (char c : chain.toCharArray()) {
					if (c == '.') {
						break;
					}
					start += c - '0';
				}
				for (char c : chain.toCharArray()) {
					if (c == '.') {
						finish = 0;
					} else
						finish += c - '0';
				}
				starts.add(start);
				finishes.add(finish);
			}
		}
		if (starts.isEmpty())
			return Math.max(middle, best);
		int bestStartIndex = ListUtils.maxIndex(starts);
		int bestFinishIndex = ListUtils.maxIndex(finishes);
		int bestStart = starts.get(bestStartIndex);
		int bestFinish = finishes.get(bestFinishIndex);
		if (bestStartIndex != bestFinishIndex)
			return Math.max(middle + bestStart + bestFinish, best);
		if (starts.size() == 1)
			return Math.max(middle + Math.max(bestStart, bestFinish), best);
		starts.set(bestStartIndex, 0);
		finishes.set(bestFinishIndex, 0);
		return Math.max(middle + Math.max(bestStart + CollectionUtils.maxElement(finishes),
			bestFinish + CollectionUtils.maxElement(starts)), best);
	}


}

