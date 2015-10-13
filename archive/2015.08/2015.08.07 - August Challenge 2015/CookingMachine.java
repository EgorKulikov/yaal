package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CookingMachine {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b = in.readInt();
        int answer = 0;
        while (b % a != 0) {
            a /= 2;
            answer++;
        }
        while (b != a) {
            a *= 2;
            answer++;
        }
        out.printLine(answer);
    }
}
