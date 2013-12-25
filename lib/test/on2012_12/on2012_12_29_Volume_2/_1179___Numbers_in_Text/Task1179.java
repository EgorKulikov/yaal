package on2012_12.on2012_12_29_Volume_2._1179___Numbers_in_Text;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1179 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] answer = new int[37];
        int maxDigit = 1;
        while (!in.isExhausted()) {
            String word = in.readString();
            for (int i = 0; i < word.length(); i++) {
                if (Character.isDigit(word.charAt(i)))
                    maxDigit = Math.max(word.charAt(i) - '0', maxDigit);
            }
            for (int i = 1; i < word.length(); i++) {
                for (int j = Math.max(decode(word.charAt(i - 1)) + 1, 10); j <= decode(word.charAt(i)); j++)
                    answer[j]++;
            }
            for (int j = Math.max(decode(word.charAt(word.length() - 1)) + 1, 10); j <= 36; j++)
                answer[j]++;
        }
        int maxPosition = ArrayUtils.maxPosition(answer);
        if (answer[10] == answer[maxPosition])
            out.printLine(maxDigit + 1, answer[maxPosition]);
        else
            out.printLine(maxPosition, answer[maxPosition]);
    }

    private int decode(char c) {
        if (Character.isDigit(c))
            return c - '0';
        return c - 'A' + 10;
    }
}
