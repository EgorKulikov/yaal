package on2012_12.on2012_12_27_Volume_9._1846___GCD_2010;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.collections.map.CPPMap;
import net.egork.misc.Factory;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class Task1846 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        Map<Integer, IntList> map = new CPPMap<Integer, IntList>(new Factory<IntList>() {
            public IntList create() {
                return new IntArrayList(1);
            }
        });
        LongIntervalTree tree = new LongIntervalTree(count) {
            @Override
            protected long joinValue(long left, long right) {
                return IntegerUtils.gcd(left, right);
            }

            @Override
            protected long joinDelta(long was, long delta) {
                return delta;
            }

            @Override
            protected long accumulate(long value, long delta, int length) {
                if (delta == -1)
                    return value;
                return delta;
            }

            @Override
            protected long neutralValue() {
                return 0;
            }

            @Override
            protected long neutralDelta() {
                return -1;
            }
        };
        int position = 0;
        for (int i = 0; i < count; i++) {
            char type = in.readCharacter();
            int number = in.readInt();
            if (type == '+') {
                tree.update(position, position, number);
                map.get(number).add(position++);
            } else {
                int current = map.get(number).popBack();
                tree.update(current, current, 0);
            }
            long result = tree.query(0, count - 1);
            if (result == 0)
                result = 1;
            out.printLine(result);
        }
	}
}
