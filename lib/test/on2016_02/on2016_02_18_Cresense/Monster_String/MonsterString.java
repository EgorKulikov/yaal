package on2016_02.on2016_02_18_Cresense.Monster_String;



import net.egork.generated.collections.function.CharToCharFunction;
import net.egork.generated.collections.list.CharArray;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class MonsterString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        int[] qty = new CharArray(s.toCharArray()).map((CharToCharFunction) Character::toLowerCase).qty(128);
        for (int i = 'a'; i <= 'z'; i++) {
            if (qty[i] != 0 && (((i - 'a') ^ qty[i]) & 1) == 1) {
                out.printLine("@0");
                return;
            }
        }
        out.printLine("@1");
    }
}
