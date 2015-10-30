package on2015_07.on2015_07_08_The_COJ_Progressive_Contest__9.BuyingCheese;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BuyingCheese {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int first = in.readInt();
        int second = in.readInt();
        int gcd = IntegerUtils.gcd(first, second);
        first /= gcd;
        second /= gcd;
        int answer = 0;
        while (first % 2 == 0) {
            first /= 2;
            answer++;
        }
        while (first % 3 == 0) {
            first /= 3;
            answer++;
        }
        while (first % 5 == 0) {
            first /= 5;
            answer++;
        }
        while (second % 2 == 0) {
            second /= 2;
            answer++;
        }
        while (second % 3 == 0) {
            second /= 3;
            answer++;
        }
        while (second % 5 == 0) {
            second /= 5;
            answer++;
        }
        if (first == 1 && second == 1) {
            out.printLine(answer);
        } else {
            out.printLine(-1);
        }
    }
}
