package net.egork.string;

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
            if (string.charAt(i) == c)
                count++;
        }
        return count;
    }

	public static String unite(String[] array) {
		StringBuilder result = new StringBuilder();
		for (String s : array)
			result.append(s);
		return result.toString();
	}

	public static int differs(String first, String second) {
		int result = 0;
		for (int i = 0; i < first.length(); i++) {
			if (first.charAt(i) != second.charAt(i))
				result++;
		}
		return result;
	}
}
