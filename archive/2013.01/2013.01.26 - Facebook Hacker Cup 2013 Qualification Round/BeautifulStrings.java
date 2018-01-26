package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class BeautifulStrings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readLine().toCharArray();
		int[] qty = new int[26];
		for (char c : s) {
			if (Character.isLetter(c))
				qty[Character.toLowerCase(c) - 'a']++;
		}
		Arrays.sort(qty);
		int answer = 0;
		for (int i = 0; i < 26; i++)
			answer += (i + 1) * qty[i];
    	out.printLine("Case #" + testNumber + ":", answer);
    }
}
