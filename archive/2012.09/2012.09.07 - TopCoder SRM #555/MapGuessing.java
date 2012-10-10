package net.egork;

import net.egork.string.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MapGuessing {
	public long countPatterns(String goal, String[] code) {
		char[] program = StringUtils.unite(code).toCharArray();
		Set<String> variants = new HashSet<String>();
		char[] target = goal.toCharArray();
		for (int i = 0; i < target.length; i++) {
			String result = execute(target, program, i);
			if (result != null)
				variants.add(result);
		}
		if (variants.size() > target.length / 2) {
			long[] masks = new long[variants.size() * (1 << (target.length - variants.size() + 1))];
			int size = 0;
			for (String variant : variants) {
				long xMask = 0;
				long add = 0;
				char[] charArray = variant.toCharArray();
				for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
					char c = charArray[i];
					if (c == 'x')
						xMask += 1L << i;
					else if (c == '1')
						add += 1L << i;
				}
				long all = xMask;
				masks[size++] = add + xMask;
				do {
					xMask = (xMask - 1) & all;
					masks[size++] = add + xMask;
				} while (xMask != 0);
			}
			Arrays.sort(masks, 0, size);
			long answer = 0;
			if (size != 0)
				answer++;
			for (int i = 1; i < size; i++) {
				if (masks[i] != masks[i - 1])
					answer++;
			}
			return answer;
		}
		long answer = 0;
		String[] array = variants.toArray(new String[variants.size()]);
		int total = 1 << array.length;
		for (int i = 1; i < total; i++) {
			long current = 1;
			for (int j = 0; j < goal.length(); j++) {
				int digit = -1;
				for (int k = 0; k < array.length; k++) {
					if ((i >> k & 1) == 1) {
						if (array[k].charAt(j) == 'x')
							continue;
						if (digit == -1 || array[k].charAt(j) - '0' == digit)
							digit = array[k].charAt(j) - '0';
						else
							digit = -2;
					}
				}
				if (digit == -2)
					current = 0;
				if (digit == -1)
					current *= 2;
			}
			if (Integer.bitCount(i) % 2 == 1)
				answer += current;
			else
				answer -= current;
		}
		return answer;
	}

	private String execute(char[] target, char[] program, int position) {
		char[] mask = new char[target.length];
		char[] lastValidMask = new char[target.length];
		for (char c : program) {
			if (c == '<') {
				position--;
				if (position < 0)
					return null;
			} else if (c == '>') {
				position++;
				if (position >= target.length)
					return null;
			} else if (c == '0')
				mask[position] = '0';
			else
				mask[position] = '1';
			boolean good = true;
			for (int i = 0; i < target.length; i++) {
				if (mask[i] != 0 && target[i] != mask[i])
					good = false;
			}
			if (good)
				System.arraycopy(mask, 0, lastValidMask, 0, target.length);
		}
		char[] result = new char[target.length];
		for (int i = 0; i < target.length; i++) {
			if (lastValidMask[i] != 0) {
				if (target[i] != lastValidMask[i])
					throw new RuntimeException();
				result[i] = 'x';
			} else
				result[i] = target[i];
		}
		return new String(result);
	}
}
