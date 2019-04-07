package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.math.BigInteger;

import static java.util.Arrays.binarySearch;
import static java.util.Arrays.copyOf;
import static java.util.Arrays.sort;

public class QualificationRound2019GoogleCodeJam2019 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        in.readString();
        int l = in.readInt();
        BigInteger[] cipher = new BigInteger[l];
        for (int i = 0; i < l; i++) {
            cipher[i] = in.readBigInteger();
        }
        BigInteger[] numbers = new BigInteger[l + 1];
        for (int i = 1; i < l; i++) {
            if (!cipher[i].equals(cipher[0])) {
                numbers[i - 1] = cipher[i - 1].divide(cipher[i - 1].gcd(cipher[i]));
                for (int j = i - 2; j >= 0; j--) {
                    numbers[j] = cipher[j].divide(numbers[j + 1]);
                }
                break;
            }
        }
        for (int i = 0; i < l; i++) {
            numbers[i + 1] = cipher[i].divide(numbers[i]);
        }
        BigInteger[] all = numbers.clone();
        sort(all);
        int at = 0;
        for (int i = 0; i <= l; i++) {
            if (i == 0 || !all[i].equals(all[i - 1])) {
                all[at++] = all[i];
            }
        }
        all = copyOf(all, at);
        char[] answer = new char[l + 1];
        for (int i = 0; i <= l; i++) {
            answer[i] = (char) ('A' + binarySearch(all, numbers[i]));
        }
        out.printLine("Case #" + testNumber + ":", new String(answer));
    }
}
