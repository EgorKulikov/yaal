package net.egork.string;

import net.egork.collections.comparators.IntComparator;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class StringUtils {
	public static String reverse(String sample) {
		StringBuilder result = new StringBuilder(sample);
		result.reverse();
		return result.toString();
	}

	public static int count(String string, char c) {
		int count = 0;
		for (int i = string.length() - 1; i >= 0; i--) {
			if (string.charAt(i) == c) {
				count++;
			}
		}
		return count;
	}

	public static String unite(String[] array) {
		StringBuilder result = new StringBuilder();
		for (String s : array) {
			result.append(s);
		}
		return result.toString();
	}

	public static int differs(String first, String second) {
		int result = 0;
		for (int i = 0; i < first.length(); i++) {
			if (first.charAt(i) != second.charAt(i)) {
				result++;
			}
		}
		return result;
	}

	public static int[] zAlgorithm(CharSequence s) {
		int length = s.length();
		int[] z = new int[length];
		int left = 0, right = 0;
		for (int i = 1; i < length; i++) {
			if (i > right) {
				int j;
				//noinspection StatementWithEmptyBody
				for (j = 0; i + j < length && s.charAt(i + j) == s.charAt(j); j++);
				z[i] = j;
				left = i;
				right = i + j - 1;
			} else if (z[i - left] < right - i + 1)
				z[i] = z[i - left];
			else {
				int j;
				//noinspection StatementWithEmptyBody
				for (j = 1; right + j < length && s.charAt(right + j) == s.charAt(right - i + j); j++);
				z[i] = right - i + j;
				left = i;
				right = right + j - 1;
			}
		}
		return z;
	}

	public static int[] prefixFunction(CharSequence s) {
		int l = s.length();
		int[] p = new int[l];
		int k = 0;
		for (int i = 1; i < l; i++) {
			while ((k > 0) && (s.charAt(k) != s.charAt(i)))
				k = p[k - 1];
			if (s.charAt(k) == s.charAt(i))
				k++;
			p[i] = k;
		}
		return p;
	}

    public static int[] suffixArray(CharSequence s) {
        int length = s.length();
        int[] result = new int[length];
        for (int i = 0; i < length; i++)
            result[i] = length - i - 1;
        final long[] type = new long[length];
        for (int i = 0; i < length; i++)
            type[i] = s.charAt(i);
        final long[] nextType = new long[length];
        int curLength = 1;
        ArrayUtils.sort(result, new IntComparator() {
            public int compare(int first, int second) {
                return IntegerUtils.longCompare(type[first], type[second]);
            }
        });
        while (curLength < length) {
            for (int i = 0; i < length; i++)
                nextType[i] = (type[i] << 32) + (i + curLength < length ? type[i + curLength] : -1);
            ArrayUtils.sort(result, new IntComparator() {
                public int compare(int first, int second) {
                    return IntegerUtils.longCompare(nextType[first], nextType[second]);
                }
            });
            long currentType = nextType[result[0]];
            long currentIndex = 0;
            for (int i = 0; i < length; i++) {
                if (nextType[result[i]] != currentType) {
                    currentIndex++;
                    currentType = nextType[result[i]];
                }
                type[result[i]] = currentIndex;
            }
            curLength <<= 1;
        }
        return result;
    }

	public static int[][] buildPrefixAutomaton(CharSequence s) {
		int[] prefixFunction = prefixFunction(s);
		int[][] result = new int[s.length() + 1][26];
		result[0][s.charAt(0) - 'a'] = 1;
		for (int i = 1; i <= s.length(); i++) {
			System.arraycopy(result[prefixFunction[i]], 0, result[i], 0, 26);
			if (i != s.length())
				result[i][s.charAt(i) - 'a'] = i + 1;
		}
		return result;
	}
}
