package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int count;
            IntList shyness = new IntArrayList();
            int answer;

            @Override
            public void read(InputReader in) {
                count = in.readInt();
                for (int i = 0; i <= count; i++) {
                    char c = in.readCharacter();
                    for (int j = '0'; j < c; j++) {
                        shyness.add(i);
                    }
                }
            }

            @Override
            public void solve() {
                answer = 0;
                for (int i = 0; i < shyness.size(); i++) {
                    answer = Math.max(answer, shyness.get(i) - i);
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
