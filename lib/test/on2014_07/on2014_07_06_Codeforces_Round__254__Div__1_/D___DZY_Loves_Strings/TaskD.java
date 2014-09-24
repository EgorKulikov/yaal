package on2014_07.on2014_07_06_Codeforces_Round__254__Div__1_.D___DZY_Loves_Strings;



import net.egork.collections.Pair;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.map.CPPMap;
import net.egork.collections.map.EHashMap;
import net.egork.misc.Factory;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		Map<String, IntList> map = new CPPMap<>(new Factory<IntList>() {
			@Override
			public IntList create() {
				return new IntArrayList();
			}
		});
		for (int i = 0; i < s.length(); i++) {
			for (int j = 1; j <= 4 && i + j <= s.length(); j++) {
				map.get(s.substring(i, i + j)).add(i);
			}
		}
		Map<Pair<String, String>, Integer> answer = new EHashMap<>();
		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			String first = in.readString();
			String second = in.readString();
			if (!map.containsKey(first) || !map.containsKey(second)) {
				out.printLine(-1);
				continue;
			}
			if (first.compareTo(second) > 0) {
				String temp = first;
				first = second;
				second = temp;
			}
			Pair<String, String> key = Pair.makePair(first, second);
			if (answer.containsKey(key)) {
				out.printLine(answer.get(key));
				continue;
			}
			IntList left = map.get(first);
			IntList right = map.get(second);
			int result;
			if (left.size() > 10 * right.size()) {
				result = find(right, left, second.length(), first.length());
			} else if (right.size() > 10 * left.size()) {
				result = find(left, right, first.length(), second.length());
			} else {
				result = s.length();
				int j = 0;
				int k = 0;
				while (true) {
					int start = Math.min(left.get(j), right.get(k));
					int end = Math.max(left.get(j) + first.length(), right.get(k) + second.length());
					result = Math.min(result, end - start);
					if (j == left.size() - 1 && k == right.size() - 1) {
						break;
					}
					if (j == left.size() - 1 || k != right.size() - 1 && right.get(k + 1) < left.get(j + 1)) {
						k++;
					} else {
						j++;
					}
				}
			}
			out.printLine(result);
			answer.put(key, result);
		}
	}

	private int find(IntList first, IntList second, int firstLength, int secondLength) {
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < first.size(); i++) {
			int value = first.get(i);
			int left = 0;
			int right = second.size();
			while (left < right) {
				int middle = (left + right) >> 1;
				int current = second.get(middle);
				if (current >= value) {
					right = middle;
				} else {
					left = middle + 1;
				}
			}
			if (left > 0) {
				int start = Math.min(value, second.get(left - 1));
				int end = Math.max(value + firstLength, second.get(left - 1) + secondLength);
				result = Math.min(result, end - start);
			}
			if (left < second.size()) {
				int start = Math.min(value, second.get(left));
				int end = Math.max(value + firstLength, second.get(left) + secondLength);
				result = Math.min(result, end - start);
			}
		}
		return result;
	}
}
