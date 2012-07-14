package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dictionary {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[] words = new String[count];
		for (int i = 0; i < count; i++)
			words[i] = in.readString();
		Map<String, Integer> prefix = new HashMap<String, Integer>();
		Map<String, Integer> suffix = new HashMap<String, Integer>();
		for (String word : words) {
			for (int i = 1; i < word.length(); i++) {
				String current = word.substring(0, i);
				Integer value = prefix.get(current);
				if (value == null)
					value = 0;
				value |= 1 << (word.charAt(i) - 'a');
				prefix.put(current, value);
				current = word.substring(i);
				value = suffix.get(current);
				if (value == null)
					value = 0;
				value |= 1 << (word.charAt(i - 1) - 'a');
				suffix.put(current, value);
			}
			if (!prefix.containsKey(word))
				prefix.put(word, 0);
			if (!suffix.containsKey(word))
				suffix.put(word, 0);
		}
		int[] countPrefix = new int[26];
		int[] countSuffix = new int[26];
		for (int i : prefix.values()) {
			for (int j = 0; j < 26; j++) {
				if ((i >> j & 1) == 1)
					countPrefix[j]++;
			}
		}
		for (int i : suffix.values()) {
			for (int j = 0; j < 26; j++) {
				if ((i >> j & 1) == 1)
					countSuffix[j]++;
			}
		}
		long result = (long)prefix.size() * suffix.size();
		for (int i = 0; i < 26; i++)
			result -= (long)countPrefix[i] * countSuffix[i];
		Set<String> single = new HashSet<String>();
		for (String word : words) {
			if (word.length() == 1)
				single.add(word);
		}
		result += single.size();
		out.printLine(result);
	}
}
