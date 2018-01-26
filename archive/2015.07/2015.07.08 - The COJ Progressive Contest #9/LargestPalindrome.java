package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class LargestPalindrome {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int value = in.readInt();
        int lengthCost = in.readInt();
        if (value == -1 && lengthCost == -1) {
            throw new UnknownError();
        }
        for (int i = 1; ; i++) {
            int remaining = value - lengthCost * i;
            if (remaining > 25 * i || (i % 2 == 0 && remaining % 2 == 1)) {
                continue;
            }
            char[] answer = new char[i];
            for (int j = 0; 2 * j < i; j++) {
                int otherChars = Math.max(0, i - 2 * j - 2);
                if (2 * j + 1 == i) {
                    answer[j] = (char) ('a' + remaining);
                } else {
                    for (int k = 0; ; k++) {
                        if (2 * k + 25 * otherChars >= remaining) {
                            answer[i - j - 1] = answer[j] = (char) ('a' + k);
                            remaining -= 2 * k;
                            break;
                        }
                    }
                }
            }
            out.printLine(answer);
            return;
        }
    }
}
