package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class StringPower {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		char[] permutation = in.readString().toCharArray();
		int[] power = new int[26];
		for (int i = 0; i < permutation.length; i++)
			power[permutation[i] - 'a'] = i;
		int[] qty = new int[26];
		for (int i = 0; i < count; i++) {
			Arrays.fill(qty, 0);
			for (char c : in.readString().toCharArray())
				qty[c - 'a']++;
			for (char c : in.readString().toCharArray())
				qty[c - 'a']--;
			int score = 0;
			for (int j = 0; j < 26; j++) {
				if (qty[j] > 0)
					score += power[j];
				else if (qty[j] < 0)
					score -= power[j];
			}
			if (score > 0)
				out.printLine("ALICE");
			else if (score == 0)
				out.printLine("TIE");
			else
				out.printLine("BOB");
		}
	}
}
