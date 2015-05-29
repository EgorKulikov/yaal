package on2015_05.on2015_05_24_Yandex_Algorithm_2015_Round_1.F_______________________;



import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int firstWidth = in.readInt();
        int firstHeight = in.readInt();
        int secondWidth = in.readInt();
        int secondHeight = in.readInt();
//        if (firstWidth < secondWidth) {
//            int temp = firstWidth;
//            firstWidth = secondWidth;
//            secondWidth = temp;
//            temp = firstHeight;
//            firstHeight = secondHeight;
//            secondHeight = temp;
//        }
        int shift = IntegerUtils.gcd(firstWidth, secondWidth);
        Rational answer = new Rational(shift, 1).multiply(MiscUtils.min(new Rational(firstHeight, firstWidth), new Rational(secondHeight, secondWidth)));
        out.printLine(answer);
    }
}
