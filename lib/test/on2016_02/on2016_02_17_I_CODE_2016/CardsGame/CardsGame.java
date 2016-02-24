package on2016_02.on2016_02_17_I_CODE_2016.CardsGame;



import net.egork.generated.collections.function.CharCharToIntFunction;
import net.egork.generated.collections.function.IntIntToIntFunction;
import net.egork.generated.collections.list.CharArray;
import net.egork.generated.collections.list.IntArray;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class CardsGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s1 = in.readString();
        String s2 = in.readString();
        int[] q1 = new CharArray(s1.toCharArray()).qty(128);
        int[] q2 = new CharArray(s2.toCharArray()).qty(128);
        int nScore = (int) new CharArray(s1.toCharArray()).join(new CharArray(s2.toCharArray()),
                (CharCharToIntFunction)(x, y) -> {
                    if (x == y) {
                        q1[x]--;
                        q2[x]--;
                        return 1;
                    }
                    return 0;
                }).sum();
        int jScore = (int) new IntArray(q1).join(new IntArray(q2), (IntIntToIntFunction) Math::min).sum();
        int sScore = s1.length() - nScore - jScore;
        out.printLine(nScore + "N" + jScore + "J" + sScore + "S");
    }
}
