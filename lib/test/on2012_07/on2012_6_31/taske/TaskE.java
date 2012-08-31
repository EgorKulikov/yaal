package on2012_07.on2012_6_31.taske;



import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.collections.set.TreapSet;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.NavigableSet;

public class TaskE {
    long[] power;
    private long multiplier;
    private int[] permutation;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int sampleLength = in.readInt();
        int permutationLength = in.readInt();
        int[] sample = IOUtils.readIntArray(in, sampleLength);
        permutation = IOUtils.readIntArray(in, permutationLength);
        MiscUtils.decreaseByOne(sample, permutation);
        power = new long[sampleLength];
        power[0] = 1;
        for (int i = 1; i < sampleLength; i++)
            power[i] = power[i - 1] * 43;
        long sumPower = 0;
        for (int i = 0; i < sampleLength; i++)
            sumPower += power[i];
        multiplier = BigInteger.valueOf(43).modInverse(BigInteger.valueOf(2).shiftLeft(64)).longValue();
        Tree tree = new Tree(permutationLength);
        tree.init();
        NavigableSet<Integer> set = new TreapSet<Integer>();
        for (int i = 0; i < permutationLength; i++) {
            if (permutation[i] < sampleLength) {
                tree.add(i, set.size());
                set.add(i);
            }
        }
        int[] reverse = new int[permutationLength];
        for (int i = 0; i < permutationLength; i++)
            reverse[permutation[i]] = i;
        long targetHash = 0;
        for (int i = 0; i < sampleLength; i++)
            targetHash += power[i] * sample[i];
        int answer = 0;
        if (targetHash == tree.query(0, permutationLength - 1))
            answer++;
        for (int i = 0; i < permutationLength - sampleLength; i++) {
            targetHash += sumPower;
            tree.remove(reverse[i], set.headSet(reverse[i], false).size());
            set.remove(reverse[i]);
            tree.add(reverse[i + sampleLength], set.headSet(reverse[i + sampleLength], false).size());
            set.add(reverse[i + sampleLength]);
            if (targetHash == tree.query(0, permutationLength - 1))
                answer++;
        }
        out.printLine(answer);
	}

    class Tree extends LongIntervalTree {
        protected Tree(int size) {
            super(size);
        }

        @Override
        protected long joinValue(long left, long right) {
            return left + right;
        }

        @Override
        protected long joinDelta(long was, long delta) {
            return was * delta;
        }

        @Override
        protected long accumulate(long value, long delta, int length) {
            return value * delta;
        }

        @Override
        protected long neutralValue() {
            return 0;
        }

        @Override
        protected long neutralDelta() {
            return 1;
        }

        public void remove(int position, int positionPower) {
            update(position + 1, size - 1, multiplier);
            add(0, 0, size - 1, position, position, -permutation[position] * power[positionPower]);
        }

        public void add(int position, int positionPower) {
            update(position + 1, size - 1, 43);
            add(0, 0, size - 1, position, position, permutation[position] * power[positionPower]);
        }

        private void add(int root, int left, int right, int from, int to, long delta) {
            if (left > to || right < from)
                return;
            if (left >= from && right <= to) {
                this.delta[root] = 1;
                value[root] += delta;
                return;
            }
            this.delta[2 * root + 1] = joinDelta(this.delta[2 * root + 1], this.delta[root]);
            this.delta[2 * root + 2] = joinDelta(this.delta[2 * root + 2], this.delta[root]);
            int middle = (left + right) >> 1;
            value[2 * root + 1] = accumulate(value[2 * root + 1], this.delta[root], middle - left + 1);
            value[2 * root + 2] = accumulate(value[2 * root + 2], this.delta[root], right - middle);
            this.delta[root] = neutralDelta();
            add(2 * root + 1, left, middle, from, to, delta);
            add(2 * root + 2, middle + 1, right, from, to, delta);
            value[root] = joinValue(value[2 * root + 1], value[2 * root + 2]);
        }

    }
}
