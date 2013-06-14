package on2012_12.on2012_12_29_Volume_7._1635___Mnemonics_and_Palindromes;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1635 {
    short[][] result;
    short[][] position;
    char[] s;
    boolean[][] isPalindrome;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        s = in.readString().toCharArray();
        int count = s.length;
        isPalindrome = new boolean[count][];
        for (int i = 0; i < count; i++)
            isPalindrome[i] = new boolean[i + 1];
        for (int i = 0; i < count; i++) {
            isPalindrome[i][i] = true;
            for (int j = 1; j <= i && i + j < count; j++) {
                if (s[i - j] != s[i + j])
                    break;
                isPalindrome[i + j][i - j] = true;
            }
            for (int j = 0; j <= i && i + j + 1 < count; j++) {
                if (s[i - j] != s[i + j + 1])
                    break;
                isPalindrome[i + j + 1][i - j] = true;
            }
        }
        result = new short[count][];
        position = new short[count][];
        for (int i = 0; i < count; i++) {
            result[i] = new short[i + 1];
            position[i] = new short[i + 1];
        }
        ArrayUtils.fill(result, (short) -1);
        out.printLine(go(0, count - 1));
        printAnswer(0, count - 1, out);
        out.printLine();
//        int result = 0;
//        int start = 0;
//        while (start != count) {
//            for (int i = count - 1; i >= start; i--) {
//                if (isPalindrome[i][start]) {
//                    printAnswer(start, i, out);
//                    result++;
//                    start = i + 1;
//                    break;
//                }
//            }
//        }
//        out.printLine(result);
//        start = 0;
//        while (start != count) {
//            for (int i = count - 1; i >= start; i--) {
//                if (isPalindrome[i][start]) {
//                    printAnswer(start, i, out);
//                    result++;
//                    start = i + 1;
//                    break;
//                }
//            }
//        }
//        out.printLine();
    }

    private void printAnswer(int from, int to, OutputWriter out) {
        if (isPalindrome[to][from]) {
            if (from != 0)
                out.print(' ');
            for (int i = from; i <= to; i++)
                out.print(s[i]);
            return;
        }
        printAnswer(from, position[to][from], out);
        printAnswer(position[to][from] + 1, to, out);
    }

    private int go(int from, int to) {
        if (result[to][from] != -1)
            return result[to][from];
        if (isPalindrome[to][from])
            return result[to][from] = 1;
        result[to][from] = (short) (1 + go(from + 1, to));
        position[to][from] = (short) from;
        for (int i = from + 1; i < to; i++) {
            if (isPalindrome[i][from]) {
                short current = (short) (1 + go(i + 1, to));
                if (current < result[to][from]) {
                    result[to][from] = current;
                    position[to][from] = (short) i;
                }
            }
        }
        return result[to][from];
    }
}
