package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class YouCanGoYourOwnWay {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String p = in.readString();
        int lRow = 0;
        int lCol = 0;
        StringBuilder answer = new StringBuilder(2 * n - 2);
        for (int i = 0; i < n - 1; i++) {
            if (lRow == i) {
                if (p.charAt(2 * i) == 'S') {
                    answer.append("ES");
                } else {
                    answer.append("SE");
                }
            }
            if (p.charAt(2 * i) == 'S') {
                lRow++;
            } else {
                lCol++;
            }
            if (p.charAt(2 * i + 1) == 'S') {
                lRow++;
            } else {
                lCol++;
            }
            if (answer.length() > 2 * i) {
                continue;
            }
            if (lRow == i + 1) {
                if (p.charAt(2 * i + 1) == 'S') {
                    answer.append("SE");
                } else {
                    answer.append("ES");
                }
            } else {
                answer.append("SE");
            }
        }
        out.printLine("Case #" + testNumber + ":", answer);
    }
}
