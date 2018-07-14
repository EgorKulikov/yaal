package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.numbers.MultiplicativeFunction;

import static net.egork.numbers.IntegerUtils.gcd;

public class TaskB {
    long[] numDivisors = MultiplicativeFunction.DIVISOR_COUNT.calculateUpTo(100001);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b = in.readInt();
        int c = in.readInt();
        long answer = 0;
        int ab = gcd(a, b);
        int bc = gcd(b, c);
        int ac = gcd(a, c);
        int abc = gcd(ab, c);
        long exactA = numDivisors[a] - numDivisors[ab] - numDivisors[ac] + numDivisors[abc];
        answer += exactA *
                (numDivisors[b] * numDivisors[c] - numDivisors[bc] * (numDivisors[bc] - 1) / 2);
        long exactB = numDivisors[b] - numDivisors[ab] - numDivisors[bc] + numDivisors[abc];
        answer += exactB *
                (numDivisors[a] * numDivisors[c] - numDivisors[ac] * (numDivisors[ac] - 1) / 2);
        long exactC = numDivisors[c] - numDivisors[ac] - numDivisors[bc] + numDivisors[abc];
        answer += exactC *
                (numDivisors[b] * numDivisors[a] - numDivisors[ab] * (numDivisors[ab] - 1) / 2);
        answer -= exactA * exactB * numDivisors[c];
        answer -= exactB * exactC * numDivisors[a];
        answer -= exactA * exactC * numDivisors[b];
        answer += exactA * exactB * exactC;
        long exactAB = numDivisors[ab] - numDivisors[abc];
        long exactBC = numDivisors[bc] - numDivisors[abc];
        long exactAC = numDivisors[ac] - numDivisors[abc];
        answer += exactAB * exactBC * exactAC;
        answer += exactAB * (exactAB + 1) / 2 * (exactAC + exactBC);
        answer += exactBC * (exactBC + 1) / 2 * (exactAB + exactAC);
        answer += exactAC * (exactAC + 1) / 2 * (exactAB + exactBC);
        answer += numDivisors[abc] * exactBC * (exactBC + 1) / 2;
        answer += numDivisors[abc] * exactAB * (exactAB + 1) / 2;
        answer += numDivisors[abc] * exactAC * (exactAC + 1) / 2;
        answer += numDivisors[abc] * (exactAB * exactAC + exactAB * exactBC + exactAC * exactBC);
        answer += numDivisors[abc] * (numDivisors[abc] + 1) / 2 * (exactAB + exactAC + exactBC);
        answer += numDivisors[abc] * (numDivisors[abc] + 1) * (numDivisors[abc] + 2) / 6;
        out.printLine(answer);
    }
}
