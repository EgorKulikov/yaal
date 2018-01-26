package on2017_06.on2017_06_04_Round_3.B__________________;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

import static net.egork.misc.ArrayUtils.createArray;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String n = in.readString();
        int x = in.readInt();
        int y = in.readInt();
        char[] answer = new char[n.length()];
        boolean shouldBeLess = true;
        boolean good = true;
        int lastUsedLarge = -1;
        for (int i = 0; i < answer.length; i++) {
            if (!shouldBeLess) {
                answer[i] = (char) (y + '0');
                continue;
            }
            int digit = n.charAt(i) - '0';
            if (digit > y) {
                answer[i] = (char) (y + '0');
                shouldBeLess = false;
            } else if (digit == y) {
                answer[i] = (char) (y + '0');
                lastUsedLarge = i;
            } else if (digit > x) {
                answer[i] = (char) (x + '0');
                shouldBeLess = false;
            } else if (digit == x) {
                answer[i] = (char) (x + '0');
            } else {
                good = false;
                break;
            }
        }
        if (good) {
            if (answer[0] == '0') {
                if (n.length() == 1) {
                    out.printLine(-1);
                } else {
                    out.printLine(createArray(n.length() - 1, (char) (y + '0')));
                }
            } else {
                out.printLine(answer);
            }
            return;
        }
        if (lastUsedLarge == -1) {
            if (n.length() == 1) {
                out.printLine(-1);
            } else {
                out.printLine(createArray(n.length() - 1, (char) (y + '0')));
            }
        } else {
            answer[lastUsedLarge] = (char) (x + '0');
            Arrays.fill(answer, lastUsedLarge + 1, n.length(), (char) (y + '0'));
            out.printLine(answer);
        }
    }
}
