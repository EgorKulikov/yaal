package net.egork;

import java.util.Arrays;

public class LittleElephantAndString {
    public int getNumber(String A, String B) {
		char[] a = A.toCharArray();
		char[] b = B.toCharArray();
		Arrays.sort(a);
		Arrays.sort(b);
		if (!Arrays.equals(a, b))
			return -1;
		for (int i = A.length() - 1; i >= 0; i--) {
			int at = i;
			for (int j = 0; j < A.length() && at < A.length(); j++) {
				if (B.charAt(at) == A.charAt(j))
					at++;
			}
			if (at != A.length())
				return i + 1;
		}
		return 0;
    }
}
