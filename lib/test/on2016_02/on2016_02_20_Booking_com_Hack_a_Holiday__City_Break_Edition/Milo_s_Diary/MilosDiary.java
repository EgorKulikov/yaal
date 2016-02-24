package on2016_02.on2016_02_20_Booking_com_Hack_a_Holiday__City_Break_Edition.Milo_s_Diary;



import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class MilosDiary {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        if (in.isExhausted()) {
            throw new UnknownError();
        }
        int m = in.readInt();
        int[] record = readIntArray(in, m);
        int last = 0;
        IntSet was = new IntHashSet();
        NavigableSet<Integer> next = new TreeSet<>();
        for (int i : record) {
            if (was.contains(i)) {
                out.printLine("NO");
                return;
            }
            was.add(i);
            if (i > last) {
                last = i;
                if (!was.contains(i - 1)) {
                    next.add(i - 1);
                }
            } else {
                if (next.isEmpty() || i != next.pollLast()) {
                    out.printLine("NO");
                    return;
                }
                if (!was.contains(i - 1)) {
                    next.add(i - 1);
                }
            }
        }
        out.printLine("YES");
    }
}
