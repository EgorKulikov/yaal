package on2013_01.on2013_01_14_SnarkNews_Winter_Series_Round__2.CuBuilding;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CuBuilding {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x0 = in.readInt();
        int y0 = in.readInt();
        int z0 = in.readInt();
        int dx = Math.abs(in.readInt() - x0);
        int dy = Math.abs(in.readInt() - y0);
        int dz = Math.abs(in.readInt() - z0);
        long answer = IntegerUtils.binomialCoefficient(dx + dy + dz, dz, MOD) * IntegerUtils.binomialCoefficient(dx + dy, dx, MOD) % MOD;
        out.printLine(answer);
    }
}
