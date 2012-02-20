package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AlphabetSoup {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] count = new int[256];
        for (char c : in.readLine().toCharArray())
            count[c]++;
        int[] hackerCupCount = new int[256];
        hackerCupCount['H']++;
        hackerCupCount['A']++;
        hackerCupCount['C']++;
        hackerCupCount['K']++;
        hackerCupCount['E']++;
        hackerCupCount['R']++;
        hackerCupCount['C']++;
        hackerCupCount['U']++;
        hackerCupCount['P']++;
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 256; i++) {
            if (hackerCupCount[i] != 0)
                answer = Math.min(answer, count[i] / hackerCupCount[i]);
        }
        out.printLine("Case #" + testNumber + ":", answer);
	}
}
