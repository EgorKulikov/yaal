package on2016_03.on2016_03_20_Grand_Prix_of_Baltics___2016.I___Stack_and_Subroutines_Machine;



import net.egork.generated.collections.list.LongArrayList;
import net.egork.generated.collections.list.LongList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskI {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        char[] program = in.readString().toCharArray();
        SubProgram result = process(program, 0, program.length - 1, new SubProgram[26]);
        out.printLine(result.additions.size());
        result.additions.inPlaceReverse();
        out.printLine(result.additions);
    }

    private SubProgram process(char[] program, int from, int to, SubProgram[] subs) {
        subs = subs.clone();
        SubProgram sub = new SubProgram();
        for (int i = from; i <= to; i++) {
            char c = program[i];
            if (Character.isDigit(c)) {
                sub.additions.add(c - '0');
                continue;
            }
            if (c == '+' || c == '-') {
                process(sub, c);
                continue;
            }
            if (Character.isLetter(c)) {
                c -= 'a';
                if (subs[c] == null) {
                    throw new RuntimeException();
                }
                for (boolean b : subs[c].changes) {
                    process(sub, b ? '+' : '-');
                }
                if (sub.additions.size() > 0) {
                    long x = sub.additions.last();
                    sub.additions.popLast();
                    sub.additions.add(x + subs[c].delta);
                } else {
                    sub.delta += subs[c].delta;
                }
                sub.additions.addAll(subs[c].additions);
                continue;
            }
            if (c != '[') {
                System.out.println("Jopa");
                System.exit(0);
            }
            boolean found = false;
            int level = 0;
            for (int j = i + 1; j < to; j++) {
                if (program[j] == '[') {
                    level++;
                }
                if (program[j] == ']') {
                    level--;
                }
                if (level == -1) {
                    subs[program[j + 1] - 'a'] = process(program, i + 1, j - 1, subs);
                    i = j + 1;
                    found = true;
                    break;
                }
            }
            if (!found) {
                while (true);
            }
        }
        return sub;
    }

    private void process(SubProgram sub, char c) {
        if (sub.additions.size() >= 2) {
            long x = sub.additions.last();
            sub.additions.popLast();
            long y = sub.additions.last();
            sub.additions.popLast();
            if (c == '+') {
                sub.additions.add(x + y);
            } else {
                sub.additions.add(y - x);
            }
        } else if (sub.additions.size() == 1) {
            long x = sub.additions.last();
            sub.additions.popLast();
            if (c == '+') {
                sub.delta += x;
            } else {
                sub.delta -= x;
            }
        } else {
            sub.changes.add(c == '+');
            if (c == '-') {
                sub.delta = -sub.delta;
            }
        }
    }

    static class SubProgram {
        List<Boolean> changes = new ArrayList<>();
        LongList additions = new LongArrayList();
        long delta;
    }
}
