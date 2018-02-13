package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.util.Arrays.sort;

public class ChefAndHisCharacters {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        int answer = 0;
        for (int i = 0; i <= s.length() - 4; i++) {
            char[] part = s.substring(i, i + 4).toCharArray();
            sort(part);
            if (new String(part).equals("cefh")) {
                answer++;
            }
        }
        if (answer == 0) {
            out.printLine("normal");
        } else {
            out.printLine("lovely", answer);
        }
    }
}
