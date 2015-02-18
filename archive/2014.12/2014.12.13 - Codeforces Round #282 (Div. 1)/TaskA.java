package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                out.printLine(-1);
                return;
            }
        }
        int lastHash = s.lastIndexOf('#');
        IntList answer = new IntArrayList();
        int add = balance;
        balance = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                balance++;
            } else {
                balance--;
            }
            if (i == lastHash) {
                answer.add(add + 1);
                balance -= add;
            } else if (s.charAt(i) == '#') {
                answer.add(1);
            }
            if (balance < 0) {
                out.printLine(-1);
                return;
            }
        }
        for (int i : answer.toArray()) {
            out.printLine(i);
        }
    }
}
