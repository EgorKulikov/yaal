package net.egork.string;

import java.util.Arrays;

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
                for (j = 0; i + j < length && s.charAt(i + j) == s.charAt(j); j++) {
                    ;
                }
                z[i] = j;
                left = i;
                right = i + j - 1;
            } else if (z[i - left] < right - i + 1) {
                z[i] = z[i - left];
            } else {
                int j;
                //noinspection StatementWithEmptyBody
                for (j = 1; right + j < length && s.charAt(right + j) == s.charAt(right - i + j); j++) {
                    ;
                }
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
            while ((k > 0) && (s.charAt(k) != s.charAt(i))) {
                k = p[k - 1];
            }
            if (s.charAt(k) == s.charAt(i)) {
                k++;
            }
            p[i] = k;
        }
        return p;
    }

    public static int[][] buildPrefixAutomaton(CharSequence s) {
        return buildPrefixAutomaton(s, 'a', 'z');
    }

    public static int[][] buildPrefixAutomaton(CharSequence s, char from, char to) {
        int[] prefixFunction = prefixFunction(s);
        int length = to - from + 1;
        int[][] result = new int[s.length() + 1][length];
        result[0][s.charAt(0) - from] = 1;
        for (int i = 1; i <= s.length(); i++) {
            System.arraycopy(result[prefixFunction[i - 1]], 0, result[i], 0, length);
            if (i != s.length()) {
                result[i][s.charAt(i) - from] = i + 1;
            }
        }
        return result;
    }

    public static boolean contains(final String s, final String target) {
        int[] z = zAlgorithm(new CharSequence() {
            public int length() {
                return s.length() + target.length();
            }

            public char charAt(int index) {
                if (index < target.length()) {
                    return target.charAt(index);
                }
                return s.charAt(index - target.length());
            }

            public CharSequence subSequence(int start, int end) {
                throw new UnsupportedOperationException();
            }
        });
        for (int i = target.length(); i < z.length; i++) {
            if (z[i] >= target.length()) {
                return true;
            }
        }
        return false;
    }

    public static int[] suffixArray(CharSequence s) {
        int length = s.length();
        int[] position = new int[length];
        int[] count = new int[Math.max(256, length)];
        int[] order = new int[length];
        for (int i = 0; i < length; ++i) {
            count[s.charAt(i)]++;
        }
        for (int i = 1; i < 256; ++i) {
            count[i] += count[i - 1];
        }
        for (int i = 0; i < length; ++i) {
            position[--count[s.charAt(i)]] = i;
        }
        order[position[0]] = 0;
        int currentClass = 0;
        for (int i = 1; i < length; ++i) {
            if (s.charAt(position[i]) != s.charAt(position[i - 1])) {
                currentClass++;
            }
            order[position[i]] = currentClass;
        }
        int[] nextPosition = new int[length];
        int[] nextOrder = new int[length];
        for (int h = 0; (1 << h) < length; h++) {
            for (int i = 0; i < length; ++i) {
                nextPosition[i] = position[i] - (1 << h);
                if (nextPosition[i] < 0) {
                    nextPosition[i] += length;
                }
            }
            Arrays.fill(count, 0);
            for (int i = 0; i < length; ++i) {
                count[order[nextPosition[i]]]++;
            }
            for (int i = 1; i <= currentClass; ++i) {
                count[i] += count[i - 1];
            }
            for (int i = length - 1; i >= 0; --i) {
                position[--count[order[nextPosition[i]]]] = nextPosition[i];
            }
            nextOrder[position[0]] = 0;
            currentClass = 0;
            for (int i = 1; i < length; ++i) {
                int mid1 = (position[i] + (1 << h));
                if (mid1 >= length) {
                    mid1 -= length;
                }
                int mid2 = (position[i - 1] + (1 << h));
                if (mid2 >= length) {
                    mid2 -= length;
                }
                if (order[position[i]] != order[position[i - 1]] || order[mid1] != order[mid2]) {
                    currentClass++;
                }
                nextOrder[position[i]] = currentClass;
            }
            System.arraycopy(nextOrder, 0, order, 0, length);
        }
        return order;
    }
}
