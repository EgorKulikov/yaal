package on2015_09.on2015_09_13_XVI_OpenCup__Grand_Prix_of_Ukraine.G___Game_of_Solitaire;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        out.printLine(IntegerUtils.gcd(n, k));
    }
}
