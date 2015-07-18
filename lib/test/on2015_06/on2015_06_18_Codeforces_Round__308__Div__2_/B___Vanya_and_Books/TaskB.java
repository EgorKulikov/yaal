package on2015_06.on2015_06_18_Codeforces_Round__308__Div__2_.B___Vanya_and_Books;



import net.egork.numbers.IntegerUtils;
import net.egork.numbers.NumberIterator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    long answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        NumberIterator iterator = new NumberIterator() {
            @Override
            protected void process(long prefix, int remainingDigits) {
                int additionalDigits = 0;
                while (prefix > 0) {
                    prefix /= 10;
                    additionalDigits++;
                }
                answer += IntegerUtils.power(10, remainingDigits) * (remainingDigits + additionalDigits);
            }
        };
        iterator.run(1, in.readInt());
        out.printLine(answer);
    }
}
