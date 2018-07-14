package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class EthanSearchesForAString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            String a;

            @Override
            public void read(InputReader in) {
                a = in.readString();
            }

            String answer;

            @Override
            public void solve() {
                for (int i = 1; i < a.length(); i++) {
                    answer = a.substring(0, i) + a;
                    if (!find(a, answer)) {
                        return;
                    }
                }
                answer = "Impossible";
            }

            private boolean find(String a, String b) {
                int i = 0;
                int j = 0;
                while (true) {
                    if (i >= a.length()) {
                        return true;
                    }
                    if (j >= b.length()) {
                        return false;
                    }
                    if (a.charAt(i) == b.charAt(j)) {
                        i++;
                        j++;
                        continue;
                    }
                    if (i == 0) {
                        j++;
                        continue;
                    }
                    i = 0;
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
